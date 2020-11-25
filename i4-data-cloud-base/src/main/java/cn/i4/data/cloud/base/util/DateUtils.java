package cn.i4.data.cloud.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    private static final ThreadLocal<DateFormat> DF_YMD = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

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

}
