package com.device.controller;

import com.device.Helper.CommonHelper;
import com.device.Helper.MyClientMQTT;
import com.device.biz.DeviceService;
import com.device.entity.*;
import com.device.enums.ItemEnum;
import com.device.enums.ItemType;
import com.device.util.CheckUtil;
import com.device.util.DateUtil;
import com.device.util.ErrorWriterUtil;
import com.device.util.third.AESHelper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/interface")
public class ThirdController {
    private static final Gson gson = new Gson();
    private static final Logger logger = LoggerFactory.getLogger(ThirdController.class);
    private static final MyClientMQTT myClientMQTT = MyClientMQTT.getInstance();
    @Resource
    private DeviceService deviceService;

    @RequestMapping(value = "/getDeviceNewData")
    @ResponseBody
    public Result getDeviceNewData(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        try {
            List<String> items = new ArrayList();
            items.add(ItemEnum.COLD_STORE.getDesc());
            items.add(ItemEnum.SEWAGE.getDesc());
            items.add(ItemEnum.WATER_TANK.getDesc());
            items.add(ItemEnum.BOILER.getDesc());
            List<DeviceRunRecord> recordList = deviceService.selectNewDatas(items);
            for (DeviceRunRecord item : recordList) {
                item.setAvgValue(deviceService.selectItemAvgValue(item.getItem()));
            }
            result = Result.success(recordList);
        } catch (Exception e) {
            result = Result.failure(ResultCode.SERVER_INTERNAL_ERROR);
            logger.error("getDeviceNewData error|ex={}", ErrorWriterUtil.writeError(e));
        }
        return result;
    }

    @RequestMapping(value = "/getDeviceHistoryData")
    @ResponseBody
    public Result getDeviceHistoryData(String item, @RequestParam(value = "offset") Long offset,
                                       @RequestParam(value = "limit") Integer limit) {
        Result result = new Result();
        try {
            if (!CommonHelper.isAllowItem(item)) {
                return Result.failure(ResultCode.BAD_REQUEST);
            }
            Page<DeviceRunRecord> page = new Page<>();
            Map filter = new HashMap();
            page.setLimit(limit);
            page.setOffset(offset);
            filter.put("item", item);
            page.setFilter(filter);
            page = deviceService.findPage(page);
            logger.info("select getDeviceHistoryData={}", gson.toJson(page));
            result = Result.success(page);
        } catch (Exception e) {
            result = Result.failure(ResultCode.SERVER_INTERNAL_ERROR);
            logger.error("getDeviceHistoryData error|ex={}", ErrorWriterUtil.writeError(e));
        }
        return result;
    }

    @RequestMapping(value = "/getWarnRecordData")
    @ResponseBody
    public Result getWarnRecordData(String item, @RequestParam(value = "offset") Long offset,
                                    @RequestParam(value = "limit") Integer limit) {
        Result result = new Result();
        try {
            if (!CommonHelper.isAllowItem(item)) {
                return Result.failure(ResultCode.BAD_REQUEST);
            }
            Page<DeviceWarnRecord> page = new Page<>();
            Map filter = new HashMap();
            page.setLimit(limit);
            page.setOffset(offset);
            page.setFilter(filter);
            filter.put("item", item);
            page = deviceService.findWarnRecordPage(page);
            logger.info("select getWarnRecordData={}", gson.toJson(page));
            result = Result.success(page);
        } catch (Exception e) {
            result = Result.failure(ResultCode.SERVER_INTERNAL_ERROR);
            logger.error("getWarnRecordData error|ex={}", ErrorWriterUtil.writeError(e));
        }
        return result;
    }

    @RequestMapping(value = "/deviceSubscribe", method = RequestMethod.POST)
    @ResponseBody
    public Result deviceSubscribe(String deviceUid) {
        Result result = new Result();
        try {
            if (CheckUtil.isEmpty(deviceUid)) {
                return Result.failure(ResultCode.BAD_REQUEST);
            }
            myClientMQTT.subscribe(deviceUid);
            result = Result.success();
        } catch (Exception e) {
            result = Result.failure(ResultCode.SERVER_INTERNAL_ERROR);
            logger.error("deviceSubscribe error|ex={}", ErrorWriterUtil.writeError(e));
        }
        return result;
    }

    @RequestMapping(value = "/deviceUnSubscribe")
    @ResponseBody
    public Result deviceUnSubscribe(String deviceUid) {
        Result result = new Result();
        try {
            if (CheckUtil.isEmpty(deviceUid)) {
                return Result.failure(ResultCode.BAD_REQUEST);
            }
            myClientMQTT.unsubscribe(deviceUid);
            result = Result.success();
        } catch (Exception e) {
            result = Result.failure(ResultCode.SERVER_INTERNAL_ERROR);
            logger.error("deviceUnSubscribe error|ex={}", ErrorWriterUtil.writeError(e));
        }
        return result;
    }

    /**
     * 主动获取方式
     *
     * @param item      项目如：冷库，锅炉(deviceType设备类型 1.温湿度 2.水位)
     * @param deviceUID 设备UID
     * @return
     */
    @RequestMapping(value = "/addDeviceData", method = RequestMethod.POST)
    @ResponseBody
    public Result addDeviceData(String item, String deviceUID) {
        Result result = new Result();
        try {
            if (!CommonHelper.isAllowItem(item)) {
                return Result.failure(ResultCode.BAD_REQUEST);
            }
            int deviceType = CommonHelper.getItemType(item);
            /* 获取接口数据 */
            Result deviceResult = AESHelper.getDeviceStatusInfo(null, deviceType, deviceUID);
            if (deviceResult.getCode() == 10003) {
                String accessToken = AESHelper.getAccessToken();
                deviceResult = AESHelper.getDeviceStatusInfo(accessToken, deviceType, deviceUID);
            }
            logger.info("接口返回数据：" + gson.toJson(deviceResult));

            DeviceRunRecord record = new DeviceRunRecord();
            if (deviceType == ItemType.TEMPERATURE.getCode()) {
                Humiture interfaceData = (Humiture) deviceResult.getT();
                Humiture upDate = CommonHelper.upDataHandle(interfaceData.getUpData());

                record.setValue(upDate.getTemperature());
                record.setValue2(upDate.getHumidity());
                record.setDateTime(DateUtil.str2Date(interfaceData.getUploadTime()));
            } else if (deviceType == ItemType.WATER.getCode()) {
                Water interfaceData = (Water) deviceResult.getT();
                Water upDate = CommonHelper.upDataHandle2(interfaceData.getUpData());

                record.setValue(upDate.getWaterHeight());
                record.setValue2((double) upDate.getLevel());
                record.setDateTime(DateUtil.str2Date(interfaceData.getUploadTime()));
            }
            record.setItem(item);
            record.setType((byte) deviceType);
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            deviceService.insertSelective(record);
            result = Result.success();
        } catch (Exception e) {
            result = Result.failure(ResultCode.SERVER_INTERNAL_ERROR);
            logger.error("addDeviceData error|ex={}", ErrorWriterUtil.writeError(e));
        }
        return result;
    }


}
