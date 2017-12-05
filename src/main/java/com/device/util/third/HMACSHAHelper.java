package com.device.util.third;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:
 * @Date: 2017-11-28 21:24
 */
public class HMACSHAHelper {
    private static final String HMAC_SHA1 = "HmacSHA1";

    /**
     * 生成签名数据
     *
     * @param data 待加密的数据 这里为 api_key
     * @param key  加密使用的 key 这里为 secret_key
     *             boxGate:光交箱开关感应
     *             irrigate:浇灌
     *             厦门纵行信息科技有限公司
     *             46
     * @return 生成十六进制字符串
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    public static String getSHA1Signature(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data);
        return CommonUtil.bytesToHexString(rawHmac);
    }

    public static void main(String[] args) throws Exception {
        String api_key = "b2c7ae511dc747e1a8faa8dbcea254a6";
        String secret_key = "18fb5ff8ff604522911ef42fbdbb478d";
        String sign = getSHA1Signature(api_key.getBytes(), secret_key.getBytes());
        System.out.println(sign);

    }

}
