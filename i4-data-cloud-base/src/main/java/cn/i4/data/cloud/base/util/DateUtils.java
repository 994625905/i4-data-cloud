package cn.i4.data.cloud.base.util;

import cn.hutool.core.convert.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 自定义的日期工具类，在Hutool的基础上做弥补
 * @author wangjc
 * @title: DateUtils
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/24-10:19
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 线程安全的格式化
     */
    private static final ThreadLocal<DateFormat> DF_M = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("MM");
        }
    };
    private static final ThreadLocal<DateFormat> DF_YMD = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };
    private static final ThreadLocal<DateFormat> DF_Y_M_D = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    private static final ThreadLocal<DateFormat> DF_TIME_STAMP = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    /**
     * 格式化字符串--日期
     * @param date
     * @return
     */
    public static Date parseYYYYMMDD(String date){
        try {
            return DF_YMD.get().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 当前日期的偏移（正前负后）
     * @param offset
     * @return
     */
    public static String offsetYYYYMMDD(int offset){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(calendar.DAY_OF_MONTH,offset);
        return DF_YMD.get().format(calendar.getTime());
    }

    /**
     * 目标日期的偏移（正前负后）
     * @param targetDate
     * @param offset
     * @return
     */
    public static String offsetYYYYMMDD(String targetDate,int offset){
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(DF_YMD.get().parse(targetDate));
        } catch (ParseException e) {
            logger.debug("目标日期的偏移报错，targetDate[{}],exception[{}]",targetDate,e.getMessage());
            e.printStackTrace();
        }
        calendar.add(calendar.DAY_OF_MONTH,offset);
        return DF_YMD.get().format(calendar.getTime());
    }

    /**
     * 当前月份的偏移（正前负后）
     * @param offset
     * @return
     */
    public static Integer offsetMM(int offset){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, offset);
        String res = DF_M.get().format(calendar.getTime());
        return Convert.toInt(res);
    }

    /**
     * 目标日期的月份偏移
     * @param targetDate：yyyyMMdd
     * @param offset
     * @return
     */
    public static Integer offsetMM(String targetDate,int offset){
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(DF_YMD.get().parse(targetDate));
        } catch (ParseException e) {
            logger.debug("目标日期的偏移报错，targetDate[{}],exception[{}]",targetDate,e.getMessage());
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH, offset);
        String res = DF_M.get().format(calendar.getTime());
        return Convert.toInt(res);
    };

    /**
     * 获取日期之间的天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> betweenYYYYMMDD(String start,String end){
        List<String> list = new ArrayList<>();
        list.add(start);

        if(!start.equals(end)){
            int index =1;
            int endInt = Convert.toInt(end);
            while (true){
                String day = offsetYYYYMMDD(start,index++);
                if(endInt >= Convert.toInt(day)){
                    list.add(day);
                }else{
                    break;
                }
            }
        }
        return list;
    }

    /**
     * 获取指定日期，指定小时对应的时间戳
     * @param targetDate
     * @param hour：hour:00:00
     * @return：精确到秒
     */
    public static Long tempYYYYMMDDHour(String targetDate,String hour){
        try {
            String format = DF_Y_M_D.get().format(DF_YMD.get().parse(targetDate));// yyyyMMDD-->yyyy-MM-DD
            long time = DF_TIME_STAMP.get().parse(format + " " + hour + ":00:00").getTime();
            return time/1000L;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定日期，指定小时分钟对应的时间戳
     * @param targetDate
     * @param hour：hour:minute:00
     * @param minute
     * @return：精确到秒
     */
    public static Long tempYYYYMMDDHour(String targetDate,String hour,String minute){
        try {
            String format = DF_Y_M_D.get().format(DF_YMD.get().parse(targetDate));// yyyyMMDD-->yyyy-MM-DD
            long time = DF_TIME_STAMP.get().parse(format + " " + hour + ":"+minute+":00").getTime();
            return time/1000L;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取两个时间戳之间的小时差
     * @param start
     * @param end
     * @return
     */
    public static float diffHour(Long startStamp,Long endStamp){
        if(startStamp >= endStamp){
            return 0F;
        }
        float res = endStamp - startStamp;
        return res/3600;
    }

    public static void main(String[] args) {
        System.out.println(offsetMM(-1));
        System.out.println(offsetMM("20200201",-1));
    }

}
