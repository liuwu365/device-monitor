package com.device.Helper;

import com.device.dao.DeviceInfoMapper;
import com.device.dao.DeviceRunRecordMapper;
import com.device.dao.DeviceWarnRecordMapper;
import com.device.dao.DeviceWarnRuleMapper;
import com.device.entity.*;
import com.device.enums.ItemType;
import com.device.enums.WarnRecordStatus;
import com.device.enums.WarnRuleStatus;
import com.device.enums.WarnType;
import com.device.util.CheckUtil;
import com.device.util.DateUtil;
import com.device.util.ErrorWriterUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description: 发布消息的回调类
 * 必须实现MqttCallback的接口并实现对应的相关接口方法CallBack 类将实现 MqttCallBack。
 * 每个客户机标识都需要一个回调实例。在此示例中，构造函数传递客户机标识以另存为实例数据。
 * 在回调中，将它用来标识已经启动了该回调的哪个实例。
 * @Date: 2017-11-30 12:54
 */
@Component
public class PushCallback implements MqttCallback {
    private static final Logger logger = LoggerFactory.getLogger(PushCallback.class);

    @Resource
    private DeviceInfoMapper deviceInfoMapper;
    @Resource
    private DeviceRunRecordMapper deviceRunRecordMapper;
    @Resource
    private DeviceWarnRuleMapper deviceWarnRuleMapper;
    @Resource
    private DeviceWarnRecordMapper deviceWarnRecordMapper;

    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开");
        //new MyClientMQTT().start(); //重连
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        //logger.info("接收消息主题 : " + topic);
        //logger.info("接收消息Qos : " + message.getQos());
        logger.info("接收消息内容 : " + new String(message.getPayload()));
        dataHandle(new String(message.getPayload()));
    }

    //@PostConstruct
    //private void aa() {
    //    String dataJson = "{    \"msgDirect\": \"report\",    \"msgPriority\": \"normal\",    \"msgType\": \"real\",    \"msgId\": \"0\",    \"apTime\": \"1512121574\",    \"msgEncrypt\": \"none\",    \"msgCmd\": \"ms\",    \"msgUid\": \"ffff009d\",    \"apUid\": \"700001fa\",    \"msgParam\": {        \"subCmd\": \"report\",        \"subType\": \"uploadData\",        \"msUid\": \"a000f3f3\",        \"data\": \"18093d\"    }}";
    //    dataHandle(dataJson);
    //}

    private void dataHandle(String dataJson) {
        try {
            if (!dataJson.equals("close")) {
                JSONObject object = new JSONObject(dataJson);
                JSONObject msgParam = object.getJSONObject("msgParam");
                String deviceUid = msgParam.getStr("msUid"); //小写的
                String uploadData = msgParam.getStr("data"); //原始上行数据
                String uploadTime = object.getStr("apTime");
                if (!CheckUtil.isEmpty(uploadData)) {
                    DeviceInfo deviceInfo = deviceInfoMapper.selectByDeviceUid(deviceUid.toUpperCase());
                    int deviceType = CommonHelper.getItemType(deviceInfo.getItem());
                    if (deviceType == ItemType.TEMPERATURE.getCode()) {
                        Humiture entity = CommonHelper.upDataHandle(uploadData);
                        addDeviceData(deviceInfo.getItem(), entity.getTemperature(), entity.getHumidity(), uploadTime);
                    } else if (deviceType == ItemType.WATER.getCode()) {
                        Water entity = CommonHelper.upDataHandle2(uploadData);
                        addDeviceData(deviceInfo.getItem(), entity.getWaterHeight(), entity.getLevel(), uploadTime);
                    }
                }
            }
        } catch (Exception e) {
            logger.info("解析mqtt数据时异常,error:{}", ErrorWriterUtil.writeError(e));
        }
    }

    private void addDeviceData(String item, double value, double value2, String uploadTime) {
        try {
            int deviceType = CommonHelper.getItemType(item);
            DeviceRunRecord record = new DeviceRunRecord();
            record.setValue(value);
            record.setValue2(value2);
            record.setDateTime(DateUtil.getIntTime2Date(Integer.parseInt(uploadTime)));
            record.setItem(item);
            record.setType((byte) deviceType);
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            deviceRunRecordMapper.insertSelective(record);
            //判断是否超过阀值,超过阀值添加报警记录,报警级别查询从高到低来比较
            List<DeviceWarnRule> deviceWarnRuleList = deviceWarnRuleMapper.selectWarnRule(item, WarnRuleStatus.OPEN.getCode());
            if (!CheckUtil.isEmpty(deviceWarnRuleList)) {
                for (DeviceWarnRule warnRule : deviceWarnRuleList) {
                    if (value <= warnRule.getMinValue() || value >= warnRule.getMaxValue()) {
                        DeviceWarnRecord warnRecord = new DeviceWarnRecord();
                        warnRecord.setItem(item);
                        warnRecord.setValue(value);
                        warnRecord.setLevel(warnRule.getLevel());
                        warnRecord.setWarnType((byte) (value < warnRule.getMinValue() ? WarnType.LOW.getCode() : WarnType.HIGH.getCode()));
                        warnRecord.setStatus((byte) WarnRecordStatus.WAIT.getCode());
                        warnRecord.setCreateTime(new Date());
                        warnRecord.setUpdateTime(new Date());
                        deviceWarnRecordMapper.insertSelective(warnRecord);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            logger.info("add device run record data error", ErrorWriterUtil.writeError(e));
        }
    }


}
