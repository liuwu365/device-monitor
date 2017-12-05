package com.device.util.third;

import com.device.entity.Humiture;
import com.device.entity.Result;
import com.device.entity.Water;
import com.device.enums.ItemType;
import com.device.util.CheckUtil;
import com.device.util.HttpClient;
import com.google.gson.Gson;
import com.xiaoleilu.hutool.json.JSONArray;
import com.xiaoleilu.hutool.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @Description:
 * @Date: 2017-11-28 21:25
 */
public class AESHelper {
    private static final Logger logger = LoggerFactory.getLogger(AESHelper.class);
    private static final Gson gson = new Gson();
    static String accessToken = "";

    static {
        try {
            accessToken = getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //String accessToken = getAccessToken();
        //System.out.println("token:" + accessToken);

        //String deviceUID = "A000F3F3";
        //Result deviceResult = getDeviceStatusInfo(accessToken, deviceUID);
        //if (deviceResult.getCode() == 10003) {
        //    String accessToken = getAccessToken();
        //    deviceResult = getDeviceStatusInfo(accessToken, deviceUID);
        //}
        //System.out.println("信息：" + gson.toJson(deviceResult));

        //String DeString = AESHelper.Decrypt("8f0549", Constant.SECRET_KEY.getBytes("utf8"));
        //System.out.println("解密后的字串是：" + DeString);

        //methodA();

        getDeviceStatusInfo("", 1, "4F006992");
    }

    /**
     * 获取加密后的签名
     *
     * @return
     * @throws Exception
     */
    public static String getEncyptSign() throws Exception {
        String enSign = HMACSHAHelper.getSHA1Signature(Constant.API_KEY.getBytes(), Constant.SECRET_KEY.getBytes());
        logger.info("加密后的签名:{}", enSign);
        return enSign;
    }

    public static String getAccessToken() throws Exception {
        String accessToken = "";
        String enSign = getEncyptSign();
        String url = Constant.INTERFACE_HOST + "/auth_v1/auth_token/query/getWanAccessToken?api_key=" + Constant.API_KEY + "&signal=" + enSign;
        String response = HttpClient.get(url);
        JSONObject jsonObj = new JSONObject(response);
        if (jsonObj.getInt("status") == 0) {
            JSONArray dataArray = jsonObj.getJSONArray("data");
            accessToken = dataArray.getJSONObject(0).getStr("access_token");
        }
        return accessToken;
    }

    public static Result getDeviceStatusInfo(String access_token, int deviceType, String deviceUID) throws Exception {
        access_token = CheckUtil.isEmpty(access_token) ? accessToken : access_token;
        String url = Constant.INTERFACE_HOST + "/zeta_v1/wan_ms/query/" + deviceUID + "/getMsStatus?access_token=" + access_token;
        String response = HttpClient.get(url);
        logger.info("接口响应结果：{}", response);
        if (CheckUtil.isEmpty(response)) {
            return new Result(400, "没有获取到响应结果");
        }
        JSONObject jsonObj = new JSONObject(response);
        int status = jsonObj.getInt("status");
        if (status != 0) {
            return new Result(status, "没有获取到有效数据,错误原因:" + jsonObj.getStr("errmsg"));
        }
        JSONArray dataArray = jsonObj.getJSONArray("data");
        JSONObject jsonObject = dataArray.getJSONObject(0);

        Object obj = null;
        if (deviceType == ItemType.TEMPERATURE.getCode()) {
            Humiture entity = new Humiture();
            entity.setUpData(jsonObject.getStr("updata"));
            entity.setHeartBeatTime(jsonObject.getStr("heartbeattime"));
            entity.setUploadTime(jsonObject.getStr("uploadtime"));
            obj = entity;
        } else if (deviceType == ItemType.WATER.getCode()) {
            Water entity = new Water();
            entity.setUpData(jsonObject.getStr("updata"));
            entity.setHeartBeatTime(jsonObject.getStr("heartbeattime"));
            entity.setUploadTime(jsonObject.getStr("uploadtime"));
            obj = entity;
        }
        return new Result(200, "success", obj);
    }


    public static void methodA() throws Exception {
        SecureRandom sr = new SecureRandom();
        SecureRandom.getInstance("SHA1PRNG");
        byte[] ss = sr.generateSeed(16);

        String key = "18fb5ff8ff604522911ef42fbdbb478d";  //820ebe7c2bfe4d529181063433ece0ef
        System.out.println("key: " + key);
        // 需要加密的字串
        String content = "{\"resp\": {\"state\": 1}, \"ts\": 1425457896, \"sid\": 3}";
        System.out.println("content: " + content);
        // 随机偏移向量
        String ivS = "426e26e82c704e59";
        System.out.println("ivS: " + ivS);
        // 加密
        String enString = AESHelper.Encrypt(content, key.getBytes("utf8"), ivS.getBytes("utf8"));
        System.out.println("加密后的字串是：" + enString);
        // 解密
        String DeString = AESHelper.Decrypt(enString, key.getBytes("utf8"));
        System.out.println("解密后的字串是：" + DeString);


        //Key：b2c7ae511dc747e1a8faa8dbcea254a6
        //签名：18fb5ff8ff604522911ef42fbdbb478d
        //http://118.178.95.186:25455/teamcms/ws/auth_v1/auth_token/query/getWanAccessToken?api_key=***&signal=***
        //String url = "/zeta_v1/wan_ms/query/{uid}/getMsStatus?access_token=ACCESS_TOKEN";

        //String url = "http://118.178.95.186:25455/teamcms/ws/zeta_v1/wan_ms/query/A000F3F3/getMsStatus?access_token=ACCESS_TOKEN";


    }


    /**
     * aes cfb 模式加密
     *
     * @param content 需要加密内容
     * @param key     密钥
     * @param ivS     偏移量
     * @return
     * @throws Exception
     */
    public static String Encrypt(String content, byte[] key, byte[] ivS) throws Exception {
        byte[] raw = key;
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        IvParameterSpec iv = new IvParameterSpec(ivS);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes("utf8"));
        byte[] newB = new byte[ivS.length + encrypted.length];
        System.arraycopy(ivS, 0, newB, 0, ivS.length);
        System.arraycopy(encrypted, 0, newB, ivS.length, encrypted.length);
        return Base64.encode(newB);
    }

    /**
     * aes cfb 模式解密
     *
     * @param content 需要解密内容
     * @param key     密钥
     * @return
     * @throws Exception
     */
    public static String Decrypt(String content, byte[] key) throws Exception {
        try {
            byte[] raw = key;
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            byte[] encrypted = Base64.decode(content);
            byte[] ivS = new byte[16];
            System.arraycopy(encrypted, 0, ivS, 0, 16);
            byte[] encrypted1 = new byte[encrypted.length - 16];
            System.arraycopy(encrypted, 16, encrypted1, 0, encrypted.length - 16);
            IvParameterSpec iv = new IvParameterSpec(ivS);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }


}
