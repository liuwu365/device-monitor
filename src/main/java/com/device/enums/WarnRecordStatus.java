package com.device.enums;

/**
 * Created by Administrator on 2017/12/1.
 */
public enum WarnRecordStatus {
    WAIT(1, "等待处理"),
    PROCESS(2, "处理中"),
    FINISH(3, "已处理");

    private int code;
    private String desc;

    WarnRecordStatus(int code, String desc) {
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
