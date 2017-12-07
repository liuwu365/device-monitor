package com.device.enums;

/**
 * Created by Administrator on 2017/12/1.
 */
public enum UserStatus {
    CLOSE(1,"禁用"),
    OPEN(2,"启用");

    private int code;
    private String desc;

    UserStatus(int code, String desc) {
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
