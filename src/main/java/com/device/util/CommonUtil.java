package com.device.util;

import com.device.entity.Result;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @author liuwu
 *         公用工具类【new偏功能性】
 */
public class CommonUtil {
    private static final Gson gson = new Gson();

    /**
     * 验证ip是否合法
     */
    public static boolean ipCheck(String text) {
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        if (text.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 方向解析
     * 正北：0，正东：9，正南：18，正西：27，分辨率为10°,如：值为3，则表示北偏东30，若：值则为11，则表示东偏南20°
     *
     * @param value
     * @return
     */
    public static String fangxinagMethod(int value) {
        String str = "";
        if (value > 30) {
            str = "未知";
            return str;
        }
        if (value == 0) {
            str = "正北";
        } else if (value == 9) {
            str = "正东";
        } else if (value == 18) {
            str = "正南";
        } else if (value == 27) {
            str = "正西";
        }
        if (value > 0 && value < 9) {
            str = "北偏东" + value * 10 + "°";
        } else if (value > 9 && value < 18) {
            str = "东偏南" + (9 + value) + "°";
        } else if (value > 18 && value < 27) {
            str = "南偏西" + (18 + value) + "°";
        }
        return str;
    }

    /**
     * 在指定路径下生成当天文件夹
     *
     * @return 返回文件夹名称
     */
    public static String createFile(String pjoPath) {
        String nowFileName = DateUtil.nowTimeDate("yyyyMMdd");// 文件夹名称
        File file = new File(pjoPath + "/" + nowFileName);
        if (!file.exists()) {
            file.mkdir();
        }
        return nowFileName;
    }

    //遍历输出指定目录下的所有目录与文件名
    public static void fileList(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        if (files != null) {
            for (File f1 : files) {
                if (f1.isDirectory()) { //如果是目录
                    System.out.println(f1.getName() + "目录");

                } else {
                    System.out.println(f1.getName()); //获得当前文件或文件夹的名称
                }
            }
        }
    }

    /**
     * 将字节转换为kb
     */
    public static long getKB(long B) {
        if (B / 1024 < 1 && B % 1024 > 0) {
            return 1;
        } else {
            return B / 1024;
        }
    }

    /**
     * 读取properties文件信息
     * c:传入当前调用该方法的类名
     */
    @SuppressWarnings("unchecked")
    public static Properties getProperties(Class c) {
        Properties prop = new Properties();
        InputStream in = c.getResourceAsStream("/config.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static void responseAjax(int errorCode, String errorMsg, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        Result result = new Result();
        result.setCode(errorCode);
        result.setMsg(errorMsg);
        writer.write(gson.toJson(result));
        writer.flush();
        writer.close();
    }

}
