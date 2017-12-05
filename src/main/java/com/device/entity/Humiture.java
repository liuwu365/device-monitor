package com.device.entity;

import java.io.Serializable;

/**
 * @Description: 温湿度
 * @Date: 2017-11-30 18:08
 */
public class Humiture implements Serializable {

    private double temperature;
    private double humidity;
    private String upData; //原始上行数据
    private String uploadTime; //原始上行最新数据时间
    private String heartBeatTime; //最新心跳时间

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
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
