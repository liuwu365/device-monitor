package com.device.Helper;

import com.device.dao.DeviceInfoMapper;
import com.device.entity.DeviceInfo;
import com.device.enums.DeviceStatus;
import com.device.util.third.Constant;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Date: 2017-11-30 12:53
 */
@Component
public class MyClientMQTT {
    private static final Logger logger = LoggerFactory.getLogger(MyClientMQTT.class);
    public static final String HOST = "tcp://" + Constant.MQTT_HOST_PORT;
    private static final String clientid = "MyClient124";
    private MqttClient client;
    private MqttConnectOptions options;
    private String userName = Constant.API_KEY;
    private String passWord = Constant.SECRET_KEY;

    @Resource
    private DeviceInfoMapper deviceInfoMapper;

    //启动定时器
    //@PostConstruct
    //public void start() {
    //    //查询运行状态的所有设备
    //    List<DeviceInfo> list = deviceInfoMapper.selectDeviceList(DeviceStatus.RUN.getCode());
    //    for (DeviceInfo device : list) {
    //        startMqtt(device.getDeviceUid());
    //    }
    //    System.out.println("启动 startMqtt !");
    //}

    public void startMqtt(String deviceUID) {
        try {
            //String deviceUID = "A000F3F3";
            String TOPIC = Constant.API_KEY + "/v1/ms/" + deviceUID.toLowerCase() + "/reportuploadData"; //订阅主题 api_key/version/opType/uid/msgType
            logger.info("设备:{}|TOPIC:{}已启动监听", deviceUID, TOPIC);

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
            MqttTopic topic = client.getTopic(TOPIC);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
            options.setWill(topic, "close".getBytes(), 2, true);

            client.connect(options);
            //订阅消息
            int[] Qos = {1};
            String[] topic1 = {TOPIC};
            client.subscribe(topic1, Qos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws MqttException {
        MyClientMQTT client = new MyClientMQTT();
        String deviceUid = "A000F3F3";
        client.startMqtt(deviceUid);
    }


}