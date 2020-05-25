package com.newly.vas.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static String formatDate(Date date, String patten) {
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        return sdf.format(date);
    }

    public static Date formatDate(String date, String patten) {
        SimpleDateFormat sdf = new SimpleDateFormat(patten);//yyyy-mm-dd, 会出现时间不对, 因为小写的mm是代表: 秒
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * 将时间转换为时间戳(秒)
     *
     * @param dateStr 格式：yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static long dateToStamp(String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(dateStr);
        return date.getTime();
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 获取两个日期之间的所有日期
     *
     * @param startTime 开始日期    2019-08-20
     * @param endTime   结束日期    2019-09-28
     * @return
     */
    public static List<String> getDays(String startTime, String endTime) {

        // 返回的日期集合
        List<String> days = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }

    /**
     * 获取日期的时间戳
     *
     * @return
     */
    public static long dateTimestamp(String dateStr) {
        //获取指定时间的时间戳，除以1000说明得到的是秒级别的时间戳（10位）
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(dateStr + " 00:00:00", new ParsePosition(0)).getTime();
    }

    /**
     * 前n天
     *
     * @param n
     * @return
     */
    public static String lastNDays(int n) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -n);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(calendar.getTime());
    }

    /**
     * 字符串转时间
     *
     * @param timeStr
     * @return
     */
    public static Date str2Date(String timeStr) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
        Date date = null;
        try {
            date = sDateFormat.parse(timeStr);

        } catch (ParseException px) {
            px.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {
        System.out.println(dateTimestamp("2019-10-16"));
        System.out.println(lastNDays(100));
    }

    /**
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author sunran   判断当前时间在时间区间内
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}
