package cn.chenjy.pmg.mysql.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenjy chenjy@chenjy.cn 2017/11/30 下午4:04
 * @Description:
 */
public class DateUtils {
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String SHORT_PATTERN = "yyyy-MM-dd";

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getNowDate() {
        return new Date();
    }

    public static Long getNowDateLong() {
        return System.currentTimeMillis();
    }

    /**
     * 毫秒转时间
     *
     * @param time
     * @return
     */
    public static Date long2Date(Long time) {
        return new Date(time);
    }

    /**
     * 时间转毫秒
     *
     * @param date
     * @return
     */
    public static Long date2Long(Date date) {
        return date.getTime();
    }

    /**
     * 时间转字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String date2Str(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串转时间
     *
     * @param time
     * @param pattern
     * @return
     */
    public static Date str2Date(String time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前时间字符串
     *
     * @param patten
     * @return
     */
    public static String getNowDateStr(String patten) {
        return date2Str(new Date(), patten);
    }

    /**
     * 获取当前时间字符串（yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static String getNowDateStrDefault() {
        return date2Str(new Date(), DEFAULT_PATTERN);
    }

    /**
     * 获取当前时间字符串（yyyy-MM-dd)
     *
     * @return
     */
    public static String getNowDateStrShort() {
        return date2Str(new Date(), SHORT_PATTERN);
    }

    /**
     * 时间转字符串（yyyy-MM-dd HH:mm:ss)
     *
     * @param time
     * @return
     */
    public static String date2StrDefault(Date time) {
        return date2Str(time, DEFAULT_PATTERN);
    }

    /**
     * 时间转字符串（yyyy-MM-dd)
     *
     * @param time
     * @return
     */
    public static String date2StrShort(Date time) {
        return date2Str(time, SHORT_PATTERN);
    }

    /**
     * 字符串转时间（yyyy-MM-dd HH:mm:ss)
     *
     * @param time
     * @return
     */
    public static Date str2DateDefault(String time) {
        return str2Date(time, DEFAULT_PATTERN);
    }

    /**
     * 字符串转时间（yyyy-MM-dd)
     *
     * @param time
     * @return
     */
    public static Date str2DateShort(String time) {
        return str2Date(time, SHORT_PATTERN);
    }
}
