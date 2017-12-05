package com.device.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具类
 */
public class DateUtil {
    public static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 验证是否是日期
     *
     * @param str
     * @return
     */
    public static boolean isValidDate(String str) {
        try {
            Date date = formatter.parse(str);
            return str.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 返回当前的时间
     * yyyy-MM-dd HH:mm:ss
     */
    public static String nowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 将String日期的字符串数据转为日期格式
     *
     * @param strTime
     * @return
     */
    public static Date str2Date(String strTime) {
        if (strTime == null || strTime.length() <= 0) {
            return null;
        }
        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = pattern.parse(strTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将String日期的字符串数据转为日期格式
     *
     * @param strTime
     * @return
     */
    public static Date str2Date(String strTime, String format) {
        if (strTime == null || strTime.length() <= 0) {
            return null;
        }
        SimpleDateFormat pattern = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = pattern.parse(strTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 返回当前的日期
     * patten 格式
     */
    public static String nowTimeDate(String patten) {
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        return sdf.format(new Date());
    }

    /**
     * 时间标准化
     * yyyy-MM-dd HH:mm:ss
     */
    public static String dateFormat(String strDate) {
        String newDate = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(strDate);
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newDate = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 时间格式化
     * yyyy-MM-dd HH:mm:ss
     */
    public static String dateFormat(Date date) {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return time.format(date);
    }


    /**
     * 时间格式化
     * yyyy-MM-dd HH:mm:ss
     */
    public static String dateFormat(Date date, String format) {
        SimpleDateFormat time = new SimpleDateFormat(format);
        return time.format(date);
    }

    /**
     * 将Date时间转换为时间戳
     *
     * @param date
     * @return
     */
    public static int getIntTimeFromDate(Date date) {
        return getIntTime(dateFormat(date));
    }

    /**
     * 时间格式转换为时间戳
     * 格式类型 yyyy-MM-dd HH:mm:ss
     */
    public static int getIntTime(String time) {
        if ("".equals(time) || null == time) {
            return 0;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateS = null;
            try {
                dateS = format.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int intTime = new Long(dateS.getTime() / 1000).intValue();
            return intTime;
        }
    }

    /**
     * 返回当前时间的时间戳
     *
     * @return 如：1441509773
     */
    public static Long nowIntTime() {
        // 获取系统当前时间，截取出年月日/时分秒
        //return Calendar.getInstance().getTimeInMillis() / 1000;
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 时间格式转换为时间戳
     * 格式类型 HH:mm
     */
    public static int getIntTime2(String time) {
        if ("".equals(time) || null == time) {
            return 0;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date dateS = null;
            try {
                dateS = format.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int intTime = new Long(dateS.getTime() / 1000).intValue();
            return intTime;
        }
    }

    /**
     * @param intTime 将时间戳转换为时间格式[10位]
     */
    public static String getDateTime(int intTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(new Date(intTime * 1000L));
        return dateTime;
    }

    /**
     * @param intTime 将时间戳转换为时间格式[13位]
     */
    public static String getDateTime13(long intTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(new Date(intTime));
        return dateTime;
    }

    /**
     * 将时间戳转为data类型
     *
     * @param intTime (10位时间戳)
     * @return
     */
    public static Date getIntTime2Date(long intTime) {
        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long time = new Long(intTime);
            String d = format.format(time * 1000);
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 忽略秒的时间
     *
     * @return
     */
    public static String getIgnoreSecDate(Date date) {
        return dateFormat(date, "yyyy-MM-dd HH:mm") + ":00";
    }

    /**
     * 取得年
     *
     * @param date
     */
    public static String getYear(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            return sdf.format(date);
        }
        return "";
    }

    /**
     * 取得月
     *
     * @param date
     */
    public static String getMonth(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM");
            return sdf.format(date);
        }
        return "";
    }

    /**
     * 取得年月
     *
     * @param date
     */
    public static String getYearMonth(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            return sdf.format(date);
        }
        return "";
    }

    public static String getYearMonthDay(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        }
        return "";
    }

    /**
     * 取得日
     *
     * @param date
     */
    public static String getDay(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd");
            return sdf.format(date);
        }
        return "";
    }

    /**
     * 取得指定时间的前n天或后n天
     * 前n天 -1
     * 后n天 1
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getNextDay(Date date, int n) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, n);
            date = calendar.getTime();
        }
        return date;
    }

    /**
     * 取得指定时间的前n小时或后n小时
     * 前n小时 -1
     * 后n小时 1
     *
     * @param date
     * @param format hour、minute、second
     * @param n
     * @return
     */
    public static Date getNextTime(Date date, String format, int n) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if ("h".equals(format)) {//小时
                calendar.add(Calendar.HOUR, n);
            } else if ("m".equals(format)) {//分钟
                calendar.add(Calendar.MINUTE, n);
            } else if ("s".equals(format)) {//秒
                calendar.add(Calendar.SECOND, n);
            } else {
                return null;
            }
            date = calendar.getTime();
        }
        return date;
    }


    /**
     * @param s 要转换的秒数
     * @return 该毫\秒数转换为 * days * hours * minutes * seconds 后的格式
     */
    public static String formatDuring(long s) {
        long days = s / (60 * 60 * 24);
        long hours = (s % (60 * 60 * 24)) / (60 * 60);
        long minutes = (s % (60 * 60)) / 60;
        long seconds = s % 60;
        String time = "";
        if (days != 0) {
            time = days + "天" + hours + "小时" + minutes + "分钟" + seconds + "秒";
        } else if (days == 0 && hours != 0) {
            time = hours + "小时" + minutes + "分钟" + seconds + "秒";
        } else if (days == 0 && hours == 0 && minutes != 0) {
            time = minutes + "分钟" + seconds + "秒";
        } else if (days == 0 && hours == 0 && minutes == 0 && seconds != 0) {
            time = seconds + "秒";
        } else {
            time = "";
        }
        return time;
    }

    /**
     * 简介：用户在线计时
     * 时间：2010-12-24
     */
    public static String sumTime(long ms) {
        int ss = 1000;
        long mi = ss * 60;
        long hh = mi * 60;
        long dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day + "天" : "" + day + "天";
        String strHour = hour < 10 ? "0" + hour + "小时" : "" + hour + "小时";
        String strMinute = minute < 10 ? "0" + minute + "分" : "" + minute + "分";
        String strSecond = second < 10 ? "0" + second + "秒" : "" + second + "秒";
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond + "毫秒" : "" + strMilliSecond + " 毫秒";
        return strDay + " " + strHour + ":" + strMinute + ":" + strSecond;
    }

    /**
     * 判断时间是否在2个时间范围内
     * 返回true就是在工作时间内，反之不在
     *
     * @param startTime
     * @param entTime
     * @param time
     */
    public static boolean isWork(int startTime, int entTime, int time) {
        if (startTime == entTime) {
            return true;
        } else if (startTime > entTime) {
            if (time >= startTime || time <= entTime) { //隔夜
                return true;
            }
        } else {
            if (time >= startTime && time <= entTime) { //当天
                return true;
            }
        }
        return false;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec};
        return times;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx:xx:xx
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String result = "";
        if (sec > 0) {
            result = sec < 9 ? ("0" + sec) : sec + "";
        }
        if (min > 0) {
            result = (min < 9 ? ("0" + min) : min) + ":" + result;
        }
        if (hour > 0) {
            result = (hour < 9 ? ("0" + hour) : hour) + ":" + result;
        }
        if (day > 0) {
            result = day + "天" + result;
        }
        return result;
    }

    /**
     * 将秒转换成时间,如：1200==>20:00
     *
     * @param s
     * @return
     */
    public static String formatSecond(Integer s) {
        String result = "00";
        if (s != null) {
            Integer hours = (int) (s / (60 * 60));
            Integer minutes = (int) (s / 60 - hours * 60);
            Integer seconds = (int) (s - minutes * 60 - hours * 60 * 60);

            if (seconds > 0) {
                result = seconds < 9 ? ("0" + seconds) : seconds.toString();
            }
            if (minutes > 0) {
                result = (minutes < 9 ? ("0" + minutes) : minutes.toString()) + ":" + result;
            } else if (minutes <= 0 && hours > 0) {
                result = "00 :" + result;
            }
            if (hours > 0) {
                result = (hours < 9 ? ("0" + hours) : hours.toString()) + " " + result;
            }
        }
        return result;

    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 当前时间-指定时间=相差天数
     * 如：2017-1-1 - 2016-12-30=3天
     *
     * @param beginDate
     * @return
     * @throws Exception
     */
    public int manage(String beginDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currdate = sdf.parse(beginDate);
        long currentTime = dateToLong(currdate);
        long currentNowTime = dateToLong(new Date());
        long a = currentNowTime - currentTime;
        long d = a / (24 * 60 * 60 * 1000);
        int b = (int) d;
        return b;
    }


    /**
     * 分钟转换为小时
     *
     * @param minute
     * @return
     */
    public static Float getMinute2Hour(Integer minute) {
        float hour = 0;
        if (minute != null && minute > 0) {
            hour = minute / 60f;
        }
        return hour;
    }

    /**
     * 整小时转换为分钟
     *
     * @param hour
     * @return
     */
    public static Integer getHour2Minute(Integer hour) {
        Integer minute = 0;
        if (hour != null && hour > 0) {
            minute = hour * 60;
        }
        return minute;
    }

    /**
     * 在指定时间上加多少分钟
     *
     * @param minute
     * @return
     */
    public static String addMinute(Date date, Integer minute) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date.setTime(date.getTime() + minute * 60 * 1000);
        return sdf.format(date);
    }


    /**
     * 在指定时间上添加几天
     *
     * @param date
     * @param num
     * @return
     */
    public static String add(Date date, int num, String format) {
        long time = (long) num * 24 * 60 * 60 * 1000;
        SimpleDateFormat df = new SimpleDateFormat(format);
        long l = date.getTime() + time;
        return df.format(new Date(l));
    }


    /**
     * 在指定时间上减去几天
     *
     * @param date
     * @param num
     * @return
     */
    public static String subtract(Date date, int num, String format) {
        long time = (long) num * 24 * 60 * 60 * 1000;
        SimpleDateFormat df = new SimpleDateFormat(format);
        long l = date.getTime() - time;
        return df.format(new Date(l));
    }

    /**
     * 指定时间前几分钟的时间
     */
    public static Date getTimeByMinute(Date date, int minute) {
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.setTime(date);
        beforeTime.add(Calendar.MINUTE, minute);
        return beforeTime.getTime();
    }

    /**
     * 年月日时分秒(无下划线) yyyyMMddHHmmss
     */
    public static final String dtLong = "yyyyMMddHHmmss";

    /**
     * 完整时间 yyyy-MM-dd HH:mm:ss
     */
    public static final String simple = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年月日(无下划线) yyyyMMdd
     */
    public static final String dtShort = "yyyyMMdd";


    /**
     * 时间转换成字符串
     *
     * @param date       时间
     * @param formatType 格式化类型
     * @return String
     */
    public static String date2String(Date date, String formatType) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        return sdf.format(date);
    }


    /**
     * 获取当前时间 yyyyMMddHHmmssSSS
     *
     * @return String
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String s = outFormat.format(now);
        return s;
    }

    /**
     * 获取当前日期 yyyyMMdd
     *
     * @param date
     * @return String
     */
    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String strDate = formatter.format(date);
        return strDate;
    }


    /**
     * 获取两个时间的时间差
     */
    public static long getDatePoor(Date endDate, Date bigenDate) {

        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - bigenDate.getTime();

        long min = diff / nm;

        return min;
    }


    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     *
     * @return 以yyyyMMddHHmmss为格式的当前系统时间
     */
    public static String getOrderNum() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dtLong);
        return df.format(date);
    }

    /**
     * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getDateFormatter() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(simple);
        return df.format(date);
    }

    /**
     * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
     *
     * @return
     */
    public static String getDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dtShort);
        return df.format(date);
    }

    /**
     * 获取当前时间是一年中的第几周
     */
    public static int getWeek() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取昨天是一年中的第几周
     */
    public static int getYesterdayWeek() {
        Calendar c = Calendar.getInstance();
        c.setTime(getYesterdaymorning());
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当前时间是一周中的第几天
     */
    public static int getDayOfWeek() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取unix时间，从1970-01-01 00:00:00开始的秒数
     *
     * @param date
     * @return long
     */
    public static long getUnixTime(Date date) {
        if (null == date) {
            return 0;
        }

        return date.getTime() / 1000;
    }


    public static Date stringCoverDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");//小写的mm表示的是分钟
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }


    //获取半个小时后的时间
    public static Date getTimesThirtyMs() {
        long da = System.currentTimeMillis() + 1800000L;
        Date date = new Date(da);
        return date;
    }


    // 获得当天0点时间
    public static Date getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();


    }

    //获取当天23:59:59,
    public static Date getTimesLast() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        Date endOfDate = calendar.getTime();
        return endOfDate;
    }


    // 获得昨天0点时间
    public static Date getYesterdaymorning() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesmorning().getTime() - 3600 * 24 * 1000);
        return cal.getTime();
    }

    // 获得当天近7天时间
    public static Date getWeekFromNow() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesmorning().getTime() - 3600 * 24 * 1000 * 7);
        return cal.getTime();
    }

    //获得随后3天时间
    public static Date getFutureThreeDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesmorning().getTime() + 3600 * 24 * 1000 * 3);
        return cal.getTime();
    }

    //获得随后一周时间
    public static Date getFutureWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesmorning().getTime() + 3600 * 24 * 1000 * 3);
        return cal.getTime();
    }


    // 获得当天24点时间
    public static Date getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获得本周一0点时间
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    // 获得本周日24点时间
    public static Date getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekmorning());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }

    // 获得本月第一天0点时间
    public static Date getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    //获取特点月份的第一天的0点
    public static Date getOneTimesorning(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    // 获得本月最后一天24点时间
    public static Date getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }

    // 获得特点月最后一天24点时间
    public static Date getOneTimesnight(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }

    //获取上个月第一天
    public static Date getLastMonthmirning() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, -1);
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        return calendar1.getTime();
    }

    //获取上个月最后一天
    public static Date getLastMonthnight() {
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);//设置为1号,当前日期既为本月第一天
        cale.set(Calendar.HOUR_OF_DAY, 23);
        cale.set(Calendar.MINUTE, 59);
        cale.set(Calendar.SECOND, 59);
        cale.set(Calendar.MILLISECOND, 999);
        return cale.getTime();
    }

    //获取指定时间上个月第一天
    public static Date getOneLastMonthmirning(Date date) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        calendar1.add(Calendar.MONTH, -1);
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        return calendar1.getTime();
    }

    //获取指定时间上个月最后一天
    public static Date getOneLastMonthnight(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.set(Calendar.DAY_OF_MONTH, 0);//设置为1号,当前日期既为本月第一天
        cale.set(Calendar.HOUR_OF_DAY, 23);
        cale.set(Calendar.MINUTE, 59);
        cale.set(Calendar.SECOND, 59);
        cale.set(Calendar.MILLISECOND, 999);
        return cale.getTime();
    }

    public static Date getLastMonthStartMorning() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesMonthmorning());
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    // 获取某天前几天零点的时间
    public static Date getBeforeDate(Date nowDate, Integer beforeNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.DAY_OF_MONTH, -beforeNum);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    // 获取某天末点时间
    public static Date getEndTimeOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     *
     * @return
     */
    public static Date getCurrentQuarterEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentQuarterStartTime());
        cal.add(Calendar.MONTH, 3);
        return cal.getTime();
    }


    public static Date getCurrentYearStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.YEAR));
        return cal.getTime();
    }

    public static Date getCurrentYearEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentYearStartTime());
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }

    public static Date getLastYearStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentYearStartTime());
        cal.add(Calendar.YEAR, -1);
        return cal.getTime();
    }

    /**
     * 返回date1-dat2相差的秒数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int subSecond(Date date1, Date date2) {
        long d1 = date1.getTime();
        long d2 = date2.getTime();
        int sub = (int) ((d1 - d2) / 1000);
        return sub;
    }

    /**
     * 返回当天还剩多少秒
     *
     * @return
     */
    public static int endDaySubSecond() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return subSecond(cal.getTime(), new Date());
    }

    //判断时间戳是否是当天时间
    public static boolean isToday(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        if (fmt.format(date).toString().equals(fmt.format(new Date()).toString())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取指定时间的前一个小时
     *
     * @param date
     * @return
     */
    public static Date anHourAgo(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        Date date1 = calendar.getTime();
        return date1;
    }

    /**
     * 返回一组日期格式中最大的日期
     *
     * @param dateArray
     * @return
     */
    public static String maxDate(Object[] dateArray) {
        Map<String, Integer> dateMap = new TreeMap<String, Integer>();
        int i, arrayLen;
        arrayLen = dateArray.length;
        for (i = 0; i < arrayLen; i++) {
            String dateKey = dateArray[i].toString();
            if (dateMap.containsKey(dateKey)) {
                int value = dateMap.get(dateKey) + 1;
                dateMap.put(dateKey, value);
            } else {
                dateMap.put(dateKey, 1);
            }
        }
        Set<String> keySet = dateMap.keySet();
        String[] sorttedArray = new String[keySet.size()];
        Iterator<String> iter = keySet.iterator();
        int index = 0;
        while (iter.hasNext()) {
            String key = iter.next();
            sorttedArray[index++] = key;
        }
        int sorttedArrayLen = sorttedArray.length;
        //System.out.println("最小日期是：" + sorttedArray[0] + "," + " 天数为" + dateMap.get(sorttedArray[0]));
        //System.out.println("最大日期是：" + sorttedArray[sorttedArrayLen - 1] + "," + " 天数为" + dateMap.get(sorttedArray[sorttedArrayLen - 1]));
        return sorttedArray[sorttedArrayLen - 1];
    }

}
