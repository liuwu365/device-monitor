package com.device.Helper;

import com.device.entity.Humiture;
import com.device.entity.Water;
import com.device.enums.ItemEnum;
import com.device.enums.ItemType;
import com.device.util.third.DigitalTrans;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 数据解析
 * @Date: 2017-12-01 18:48
 */
public class CommonHelper {
    private static final Gson gson = new Gson();
    private static final Logger logger = LoggerFactory.getLogger(CommonHelper.class);

    public static boolean isAllowItem(String item) {
        List<String> items = new ArrayList();
        items.add(ItemEnum.COLD_STORE.getDesc());
        items.add(ItemEnum.SEWAGE.getDesc());
        items.add(ItemEnum.WATER_TANK.getDesc());
        items.add(ItemEnum.BOILER.getDesc());
        return items.contains(item);
    }

    public static int getItemType(String item) {
        int result = 0;
        if (item.equals(ItemEnum.COLD_STORE.getDesc()) || item.equals(ItemEnum.BOILER.getDesc())) {
            result = ItemType.TEMPERATURE.getCode();
        } else if ((item.equals(ItemEnum.SEWAGE.getDesc()) || item.equals(ItemEnum.WATER_TANK.getDesc()))) {
            result = ItemType.WATER.getCode();
        }
        return result;
    }


    /**
     * 温湿度
     *
     * @param updata 上行数据
     * @return
     * @throws Exception
     */
    public static Humiture upDataHandle(String updata) throws Exception {
        boolean isPositive = false;
        //十六进制-->二进制
        String two = DigitalTrans.hexStringToBinary(updata);
        //获取首位 0正数 1负数
        char first = two.charAt(0);
        if (first == 0) {
            isPositive = true;
        } else if (first == 1) {
            isPositive = false;
        }
        //拼接二进制 每隔8位添加一个空格
        char[] ca = two.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < ca.length; j++) {
            sb.append(ca[j]);
            if ((j + 1) % 8 == 0) {
                sb.append(" ");
            }
        }
        //二进制-->十进制
        System.out.println(sb);
        StringBuilder result = new StringBuilder();
        String[] split = sb.toString().split(" ");
        for (int i = 0; i < split.length; i++) {
            long btd;
            if (i == 0) { //去掉一个首位
                btd = DigitalTrans.binaryToAlgorism(split[i].substring(1).toString());
            } else {
                btd = DigitalTrans.binaryToAlgorism(split[i].toString());
            }
            result.append(btd + " ");
        }
        logger.info("最终解析数据：首位{},数值{}", isPositive ? "+" : "-", result.toString());
        String[] buildStr = result.toString().split(" ");

        Humiture obj = new Humiture();
        String temperature = isPositive ? "+" : "-" + buildStr[0] + "." + buildStr[1] + "";
        String humidity = buildStr[2];
        obj.setTemperature(Double.parseDouble(temperature));
        obj.setHumidity(Double.parseDouble(humidity));
        logger.info(gson.toJson(obj));
        return obj;
    }

    /**
     * 水位
     *
     * @param updata 上行数据
     * @return
     * @throws Exception
     */
    public static Water upDataHandle2(String updata) throws Exception {
        //0100c8,01是心跳类型，00c8是水位高度，200cm    00c8 16进制-->10进制
        Water water = new Water();
        //截取前2位(前2位是固定数据类型)
        String str = updata.substring(2);
        int waterHeight = DigitalTrans.hexStringToAlgorism(str);
        logger.info("water data==>updata:{}|str:{}|waterHeight={}", updata, str, waterHeight);
        water.setWaterHeight(waterHeight);
        return water;
    }
}
