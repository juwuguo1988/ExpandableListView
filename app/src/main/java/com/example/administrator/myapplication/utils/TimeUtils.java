package com.example.administrator.myapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class TimeUtils {

    public static final SimpleDateFormat DATE_SDF_ALL = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
    public static final SimpleDateFormat DATE_SDF = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat TIME_SDF = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat WEEK_SDF = new SimpleDateFormat("EEE");

    /**
     * 将时间戳转成固定格式的字符串
     */
    public static String getDateStampString(String time) {
        String values = "";
        try {
            long da = Long.valueOf(time);
            Date date = new Date(da);
            SimpleDateFormat sDate = new SimpleDateFormat("yyyy年MM月dd日");
            values = sDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }

    /**
     * 把分钟数转换为00:00
     */
    public static String secToHMXXXTime(int time) {
        String timeStr = null;
        double hour = 0;
        double minute = 0;

        if (time <= 0) {
            return "00:00";
        } else {
            hour = time / 60;
            minute = time % 60;

            timeStr = unitFormat((int) hour) + ":" + unitFormat((int) minute);

        }

        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = "0" + Integer.toString(i);
        } else {
            retStr = "" + i;
        }
        return retStr;
    }


    /**
     * 将服药历史记录的天数，月份*100+天数（自定义的）转换回如9月7日格式
     */
    public static String getIntegerToMMDD(int date) {
        int monthData = date % 10000;
        int month = monthData / 100;
        int day = date % 100;
        return month + "月" + day + "日";
    }

    /**
     * 星期几
     *
     * @param time long 系统时间的long类型
     * @return 星期一到星期日
     */
    public static String formatLongToWeek(long time) {

        Date date = new Date(time);
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }


}
