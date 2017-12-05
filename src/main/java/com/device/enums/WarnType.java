package com.device.enums;

/**
 * Created by Administrator on 2017/12/1.
 */
public enum WarnType {
    LOW(1, "偏低"),
    HIGH(2, "偏高");

    private int code;
    private String desc;

    WarnType(int code, String desc) {
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
