package com.device.entity;

import java.io.Serializable;

/**
 * @Description: 水位
 * @Date: 2017-11-30 18:19
 */
public class Water implements Serializable {

    private int level;  //等级
    private double waterHeight; //单位cm
    private String upData; //原始上行数据
    private String uploadTime; //原始上行最新数据时间
    private String heartBeatTime; //最新心跳时间

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getWaterHeight() {
        return waterHeight;
    }

    public void setWaterHeight(double waterHeight) {
        this.waterHeight = waterHeight;
    }

    public String getUpData() {
        return upData;
    }

    public void setUpData(String upData) {
        this.upData = upData;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getHeartBeatTime() {
        return heartBeatTime;
    }

    public void setHeartBeatTime(String heartBeatTime) {
        this.heartBeatTime = heartBeatTime;
    }
}
