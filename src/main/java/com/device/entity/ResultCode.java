package com.device.entity;

public enum ResultCode {
    OK(200, "成功"),
    FAIL(404, "失败"),
    ERROR(500, "服务器异常!"),
    BAD_REQUEST(400, "参数错误!"),
    TOKEN_IS_NULL(555, "token is null"),
    SERVER_INTERNAL_ERROR(20000, "服务器内部错误！"),;

    private int code;

    private String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getErrorDesc(int code) {
        for (ResultCode rc : ResultCode.values()) {
            if (rc.getCode() == code) {
                return rc.getDesc();
            }
        }
        return "";
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
