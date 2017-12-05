package com.device.enums;

/**
 * Created by Administrator on 2017/11/30.
 */
public enum ItemType {
    TEMPERATURE(1,"温度"),
    WATER(2,"水位");

    private int code;
    private String desc;

    ItemType(int code, String desc) {
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
