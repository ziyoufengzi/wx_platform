package com.jfsoft.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * wanggang
 * 2017/7/19
 */
public class DateUtil {

    public static Date parse(String date ) {
        SimpleDateFormat ymdhmsFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = ymdhmsFormatter.parse(date);
        } catch (ParseException e) {
            return d;
        }
        return d;
    }

    public static String format(Date date, String pattern) {
        String s = null;
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        s = format.format(date);
        return s;
    }

    public static String format(Date date) {
        SimpleDateFormat ymdhmsFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = null;
        s =  ymdhmsFormatter.format(date);
        return s;
    }

    public static String formatday(Date date) {
        SimpleDateFormat ymdhmsFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = null;
        s =  ymdhmsFormatter.format(date);
        return s;
    }

}
