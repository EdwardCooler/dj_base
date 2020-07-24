package jzero.admin.common.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;



public class EduStringUtil extends StringUtils {

	public static boolean isValidLength(String string, int minLength, int maxLength) {
		if (null == string) {
			return false;
		}

		int length = string.length();
		if (minLength > length || maxLength < length) {
			return false;
		}

		return true;
	}

	/*public static boolean check_pwd(String pwd) {
		if (pwd == null)
			return false;
		if (pwd.length() > Preference.ULTRA_PASSWORD_MAX_LEN || pwd.length() < Preference.ULTRA_PASSWORD_MIN_LEN) {
			return false;
		}
		Pattern pattern = Pattern.compile("([\\x21-\\x7E])*");
		return pattern.matcher(pwd).matches();
	}*/

	/*
	 * 检查用户名是否是纯数字
	 * 
	 */
	public static boolean check_username(String username) {
		if (username == null)
			return false;

		return StringUtils.isNumeric(username);
	}

	public static boolean check_phone(String phone) {
		if (phone == null || phone.isEmpty()) {

			return false;
		}

		if (phone.length() > 30 || phone.length() < 11) {

			return false;
		}
		Pattern pattern = Pattern.compile("([0-9])*");
		return pattern.matcher(phone).matches();
	}

	/*public static boolean check_email(String mail) {
		if (mail == null || mail.isEmpty()) {
			return false;
		}
		if (mail.length() > Preference.ULTRA_EMAIL_LEN) {
			return false;
		}

		Pattern pattern = Pattern.compile(
				".*@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

		return pattern.matcher(mail).matches();
	}*/

	/**
	 * 提取字符串内所有的img标签下的src
	 * 
	 * @param content
	 * @return
	 */
	public static List<String> getImg(String content) {
		String regex;
		List<String> list = new ArrayList<String>();
		// 提取字符串中的img标签
		regex = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(content);
		while (ma.find()) {
			// 提取字符串中的src路径
			Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(ma.group());
			while (m.find()) {
				list.add(m.group(1));

			}
		}
		return list;
	}

	/**
	 * 从新闻内容中得到第一张图片的地址
	 * 
	 * @Description
	 * @author hmilysean
	 * @date 2015-10-14 下午03:42:11
	 * @param content
	 * @return
	 */
	public static String getOnlyOneImg(String content) {
		String regex;
		// 提取字符串中的img标签
		regex = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(content);
		while (ma.find()) {
			// 提取字符串中的src路径
			Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(ma.group());
			while (m.find()) {
				return m.group(1);
			}

		}

		return null;
	}

	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		String str = sdf.format(new Date(System.currentTimeMillis()));
		return str;
	}
	
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
	public static String getDateYMDHM(long l) {
		String str = sdf2.format(new Date(l));
		return str;
	}

	public static String getDate(long l) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		String str = sdf.format(new Date(l));
		return str;
	}

	public static long getDateLong(String str) {
		if (EduStringUtil.isEmpty(str))
			return 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		Date date = new Date();
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			try {
				date = sdf3.parse(str);
			} catch (ParseException e1) {
				try {
					date = sdf2.parse(str);
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
			}
		}
		return date.getTime();
	}

	/**
	 * 去掉秒
	 * 
	 * @Description:
	 * @date:2016年7月27日 下午4:59:01
	 * @author 瞿子朋
	 * @vesrsion: 1.0
	 *
	 * @param str
	 * @return
	 */
	public static String dateChange(String str) {
		if (!EduStringUtil.isEmpty(str)) {
			Date date = new Date(getDateLong(str));
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
			return sdf3.format(date);
		} else
			return "";
	}

	public static String dateChange_2(String str) {
		if (!EduStringUtil.isEmpty(str)) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
			Date d = null;
			try {
				d = sdf2.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (d != null)
				return sdf1.format(d);
		}
		return "";
	}

	public static boolean isEmpty(String str) {
		if (str == null)
			return true;
		if (str.length() <= 0 || str == "" || str.equals("") || str.equals("null"))
			return true;
		return false;
	}

	/**
	 * 获取格式为yyyy-MM-dd HH:mm:ss的时间
	 * 
	 * @Description:
	 * @date:2016年8月24日 下午3:56:05
	 * @author 瞿子朋
	 * @vesrsion: 1.0
	 *
	 * @param str
	 * @return
	 */
	public static String getDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date(getDateLong(str));
		return sdf.format(date);
	}

	public static String getYearMonth() {
		Calendar calendar = Calendar.getInstance();

		StringBuffer buffer = new StringBuffer();
		buffer.append(calendar.get(Calendar.YEAR) - 1);
		buffer.append("年");
		buffer.append(calendar.get(Calendar.MONTH) + 1);
		buffer.append("月");
		buffer.append("-");
		buffer.append(calendar.get(Calendar.YEAR));
		buffer.append("年");
		buffer.append(calendar.get(Calendar.MONTH) + 1);
		buffer.append("月");
		return buffer.toString();
	}

	/**
	 * 将yyyy-MM-dd HH:mm:ss格式转换为yyyy年MM月dd日
	 * 
	 * @Description:
	 * @date:2016年8月24日 下午2:32:22
	 * @author 瞿子朋
	 * @vesrsion: 1.0
	 *
	 * @param str
	 * @return
	 */
	public static String getDateYMD(String str) {
		SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			Date date = sdf.parse(str);
			return sdf4.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * float保留两位小数
	 * 
	 * @Description:
	 * @date:2016年8月24日 上午10:15:18
	 * @author 瞿子朋
	 * @vesrsion: 1.0
	 *
	 * @param f
	 * @return
	 */
	public static String floatToString(float f) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
		String str = decimalFormat.format(f);// format 返回的是字符串
		return str;
	}

	/**
	 * 用0补齐两位
	 * 
	 * @Description:
	 * @date:2016年10月9日 下午4:20:05
	 * @author 瞿子朋
	 * @vesrsion: 1.0
	 *
	 * @param i
	 * @return
	 */
	public static String zeroFill(int i) {
		if (i < 10)
			return "0" + i;
		return i + "";
	}

	/**
	 * 用于月份充值时满一年，年份加1 判断
	 * 传入2017年9月,4 返回 2018年1月
	 * @Description 
	 * @author GuoMing
	 * @date 2017年6月15日 下午1:50:39 
	 * @param now
	 * @param apiTime
	 */
	public static Date getAddMonthDate(Date now, int monthNum) {
		Date newDate=now;
		int year=now.getYear();
		int month=now.getMonth();
		if(monthNum+month>=12){
			year+=1;
			month=monthNum+month-12;
		}else{
			month=monthNum+month;
		}
		newDate.setMonth(month);
		newDate.setYear(year);
		return newDate;	
	}
	/**
	 * 
	 * @Title: addTime   
	 * @Description: 高级功能充值时间
	 * @param: @param now
	 * @param: @param monthNum
	 * @param: @return  
	 * @author: yhq    
	 * @return: Date      
	 * @throws
	 */
	public static Date addTime(Date now, int monthNum){
		long mills=now.getTime()+monthNum*30*24*60*60*1000L;	
		return new Date(mills);
	}
	
	/**
	 * URL spell 组合
	 * @Description URL拼接 支持 "\" 和 "/" 的拼接
	 * 两个只要有一个使用的 / 就全部为 /
	 * @author 随风丶小柏
	 * @date 2018年03月15日 09:10:20
	 * @version 1.0
	 * @return
	 */
	public static String SpellURI(String prefix ,String suffix) {
		//空判断操作
		if (StringUtils.isBlank(prefix) && StringUtils.isBlank(suffix)) {
			return "";
		}else if (StringUtils.isBlank(prefix) && StringUtils.isNotBlank(suffix)) {
			return suffix;
		}else if (StringUtils.isNotBlank(prefix) && StringUtils.isBlank(suffix)) {
			return prefix;
		}
		//符号判断操作   两个只要有一个使用的 / 就全部为 /
		String symbol = "/";
		if (prefix.contains("/") || suffix.contains("/")) {
			prefix = prefix.replace("\\", "/");
			suffix = suffix.replace("\\", "/");
		}
		if (prefix.contains("\\") && suffix.contains("\\")) {
			symbol = "\\";
		}
		
		if (prefix.endsWith(symbol)) {
			prefix = prefix.substring(0, prefix.length()-1);
		}
		if (suffix.startsWith(symbol)) {
			suffix = suffix.substring(1, suffix.length());
		}
		return prefix+symbol+suffix;
		
	}
	/**
	 * 
	 * @Description:去除HTMl标签
	 * @author MC
	 * @date 2018年4月26日 下午3:09:04
	 * @param str
	 * @return
	 */
	public static String delHtmlTag(String str){ 
	    String newstr = ""; 
	    newstr = str.replaceAll("<[.[^>]]*>","");
	    newstr = newstr.replaceAll(" ", ""); 
	    return newstr;
	}
}
