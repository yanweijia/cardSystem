package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by weijia on 2017-03-03.
 * 时间日期工具类
 */
public class DateUtils {


    /**
     * 通过参数构造一个Date
     * @param strDate 格式为yyyy-MM-dd
     * @return
     */
    public static Date parseDate(String strDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }



    /**
     * 获取当前星期几
     * @return
     */
    public static String getDayOfWeek(){
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0 || w > 6)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取当前日期
     * @return 当前日期,格式为 yyyy-MM-dd
     */
    public static String getDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 获取当前日期时间
     * @return 日期时间 格式为 yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 获取当前年份
     * @return 年份 格式为yyyy
     */
    public static String getYear(){
        return new SimpleDateFormat("yyyy").format(new Date());
    }


    /**
     * 获取当前月份
     * @return 月份 格式为MM
     */
    public static String getMonth(){
        return new SimpleDateFormat("MM").format(new Date());
    }

    /**
     * 获取当前天数
     * @return 天数 格式为MM
     */
    public static String getDayOfMonth(){
        return new SimpleDateFormat("dd").format(new Date());
    }
    /**
     * 获取当前时间
     * @return 当前时间,格式为 HH:mm:ss.SSS
     */
    public static String getTime(){
        return new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
    }

        /**
     * 获取当前Unix时间戳(单位毫秒)
     * @return unix时间戳
     */
    public static int getUnixTimeStamp(){
        return (int)(System.currentTimeMillis() / 1000);
    }
}
