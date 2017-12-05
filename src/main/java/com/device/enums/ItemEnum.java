package com.device.enums;

/**
 * @Description: 项目
 * @Date: 2017-11-30 20:02
 */
public enum ItemEnum {
    COLD_STORE(1, "冷库"),//温度
    SEWAGE(2, "污水"),    //水位
    WATER_TANK(3, "水箱"),//水位
    BOILER(4, "锅炉");    //温度

    private int code;
    private String desc;

    ItemEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
