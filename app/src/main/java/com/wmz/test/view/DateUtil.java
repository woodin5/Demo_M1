package com.wmz.test.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {


    /**
     * 获取周开始的 Calendar
     * 以星期一为开始天
     */
    public static Calendar getWeekStart(Calendar calendar) {
        int cur = calendar.get(Calendar.DAY_OF_WEEK);
        int cutDay;
        if (cur == Calendar.SUNDAY) {  // 1
            // 星期天
            cutDay = 6;
        } else {
            // 其他日期
            cutDay = (cur - 2);
        }
        calendar.add(Calendar.DAY_OF_YEAR, -cutDay);
        return calendar;
    }

    /**
     * 获取星期的索引
     */
    public static int getWeekIndex(Calendar calendar) {
        int cur = calendar.get(Calendar.DAY_OF_WEEK);
        if (cur == Calendar.SUNDAY) {
            return 6;
        } else {
            return cur - 2;
        }
    }

    /**
     * 获取星期的索引
     */
    public static int getWeekIndex(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        int cur = calendar.get(Calendar.DAY_OF_WEEK);
        if (cur == Calendar.SUNDAY) {
            return 6;
        } else {
            return cur - 2;
        }
    }

    /**
     * 获取月份的天数
     */
    public static int getMonthDays(Calendar calendar) {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取月份的天数
     *
     * @param month 1-12 取值范围
     */
    public static int getMonthDays(int year, int month) {
        if (month == 0) {
            return 31;
        }
        // 是否是闰年
        boolean isLeapYear = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if (isLeapYear) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 30;
        }
    }


    /**
     * 转换日期
     */
    public static String transDate(int year, int month, int day) {
        return String.format("%d-%02d-%02d", year, month, day);
    }

    /**
     * 根据格式， 转换日期
     */
    public static String transDate(Date date, String pattern) {
        if (date == null || pattern == null || pattern.length() == 0) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        return format.format(date);
    }

    /**
     * 根据格式，获取当前的时间
     */
    public static String getCurrent(String format) {
        return transDate(new Date(), format);
    }

    /**
     * 获取Calendar上面的日期表示  2015-12-17
     */
    public static String getCalendarDate(Calendar calendar) {
        return String.format("%04d-%02d-%02d",
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 获取当前时间 年月日
     */
    public static String getCurrentDate() {
        return transDate(new Date(), "yyyyMMdd");
    }

    /**
     * 获取当前的时 年月日，时分秒
     */
    public static String getCurrentTime() {
        return transDate(new Date(), "yyyyMMdd_HHmmss");
    }


    /**
     * 字符串转换成统时间.
     *
     * @param formatStr 协议中的时间串.
     * @param format    : 字符格式
     * @return ： 时间
     */
    public static Date getDateByFormatStr(String formatStr, String format) {
        Date dt = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            dt = sdf.parse(formatStr);
        } catch (ParseException e) {

        }
        return dt;
    }


    public static Calendar getCalenderFromString(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calendar = null;
        try {
            Date date = sdf.parse(time);
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return calendar;
    }

    private DateUtil() {
    }
}