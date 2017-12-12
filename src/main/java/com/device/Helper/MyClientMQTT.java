package com.device.Helper;

import com.device.dao.DeviceInfoMapper;
import com.device.entity.DeviceInfo;
import com.device.enums.DeviceStatus;
import com.device.util.CheckUtil;
import com.device.util.ErrorWriterUtil;
import com.device.util.third.Constant;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Date: 2017-11-30 12:53
 */
@Component
public class MyClientMQTT {
    private static final Gson gson = new Gson();
    private static final Logger logger = LoggerFactory.getLogger(MyClientMQTT.class);
    public static final String HOST = "tcp://" + Constant.MQTT_HOST_PORT;
    private static final String clientid = "MyClient1189";
    private MqttClient client;
    private MqttConnectOptions options;
    private String userName = Constant.API_KEY;
    private String passWord = Constant.SECRET_KEY;
    private ScheduledExecutorService scheduler;

    public static MyClientMQTT myClientMQTT;

    @Resource
    private DeviceInfoMapper deviceInfoMapper;


    //启动定时器
    @PostConstruct
    public void start() {
        myClientMQTT = this;
        //查询运行状态的所有设备
        /*List<DeviceInfo> list = deviceInfoMapper.selectDeviceList(DeviceStatus.RUN.getCode());
        String[] topicArray = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            topicArray[i] = Constant.API_KEY + "/v1/ms/" + list.get(i).getDeviceUid().toLowerCase() + "/reportuploadData"; //订阅主题 api_key/version/opType/uid/msgType
        }
        if (!CheckUtil.isEmpty(topicArray)) {
            startMqtt(topicArray);
            logger.info("启动 startMqtt !");
        } else {
            logger.info("没有设备开启mqtt监听");
        }*/

        startMqtt();
    }

    //重新链接
    public void startReconnect() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                if (!client.isConnected()) {
                    try {
                        client.connect(options);
                    } catch (MqttSecurityException e) {
                        e.printStackTrace();
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0 * 1000, 10 * 1000, TimeUnit.MILLISECONDS);
    }

    //断开链接
    public void disconnect() {
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    //退订(如果运行的设备被设置为禁止状态，则退订),暂废
    public void unsubscribe(String[] topicArray) {
        try {
            client.unsubscribe(topicArray);
        } catch (MqttException e) {
            logger.info("退订时异常:topicArray={}|error={}", gson.toJson(topicArray), ErrorWriterUtil.writeError(e));
        }
    }

    //启动
    public void startMqtt() {
        try {
            //查询运行状态的所有设备
            List<DeviceInfo> list = myClientMQTT.deviceInfoMapper.selectDeviceList(DeviceStatus.RUN.getCode());
            String[] topicArray = new String[list.size()];
            if (!CheckUtil.isEmpty(list)) {
                for (int i = 0; i < list.size(); i++) {
                    topicArray[i] = Constant.API_KEY + "/v1/ms/" + list.get(i).getDeviceUid().toLowerCase() + "/reportuploadData"; //订阅主题 api_key/version/opType/uid/msgType
                    logger.info("TOPIC:{}启动监听", topicArray[i]);
                }
            } else {
                logger.info("没有设备开启mqtt监听");
                return;
            }

            // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(HOST, clientid, new MemoryPersistence());

            // MQTT的连接设置
            options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
            options.setUserName(userName);
            // 设置连接的密码
            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            // 设置回调
            client.setCallback(new PushCallback());
            //MqttTopic topicA = client.getTopic(topicArray[0]);
            //MqttTopic topicB = client.getTopic(topicArray[1]);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
            //options.setWill(topicA, "close".getBytes(), 2, true);
            //options.setWill(topicB, "close".getBytes(), 2, true);

            int[] Qos = new int[topicArray.length]; //{1, 1};
            for (int i = 0; i < topicArray.length; i++) {
                Qos[i] = 1;
                options.setWill(client.getTopic(topicArray[i]), "close".getBytes(), 2, true);
            }
            client.connect(options);
            //订阅消息
            client.subscribe(topicArray, Qos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws MqttException {
        //MyClientMQTT client = new MyClientMQTT();
        //String deviceUid = "A000F3F3";
        //client.startMqtt(deviceUid);
    }


}