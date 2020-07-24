package gf.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
		private static final long ONE_MINUTE = 60;
	    private static final long ONE_HOUR = 3600;
	    private static final long ONE_DAY = 86400;
	    private static final long ONE_MONTH = 2592000;
	    private static final long ONE_YEAR = 31104000;

	    
	 /**
     * 距离今天多久
     * @param date
     * @return
     *
     */
    public static String fromToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
 
        long time = date.getTime() / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;
        if (ago < ONE_MINUTE) {
        	 return "刚刚";
        }
        if (ago <= ONE_HOUR)
            return ago / ONE_MINUTE + "分钟前";
        else if (ago <= ONE_DAY)
            return ago / ONE_HOUR + "小时前";
        else if (ago <= ONE_DAY * 2)
            return "昨天";
        else if (ago <= ONE_DAY * 3)
            return "前天";
        else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day + "天前" ;
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
           
            return month + "个月前";
        } else {
            long year = ago / ONE_YEAR;
            return year + "年前";
        }
 
    }

   public  static void main(String[] args) {
    	
    	   String deadline = "2019-06-28 16:25:00";
           Date date = new Date();
           SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           try {
			date = simple.parse(deadline);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           System.out.println(DateUtils.fromToday(date));

    }
}
