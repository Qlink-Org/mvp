/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.qlink.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import com.qlink.modules.sys.utils.Constants;


/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author admin
 * @version 2013-05-22
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	public static final String COLON = ":";
	public static final String VERTICAL_LINE = "|";
	public static final String EQUALS_SIGN = "=";
	public static final String AND_SIGN = "&";
	public static final String POINT = ".";
	public static final String COMMA = ",";
	public static final String ASTERISK = "*";
	public static final String SINGLE_QUOTES = "'";
	public static final String DOUBLE_QUOTES = "\"";
	public static final String BRACKETS_MIDDLE_FRONT = "[";
	public static final String BRACKETS_MIDDLE_BACK = "]";
	public static final String SEPARATOR = "/";
	public static final String QUESTION_MARK = "?";
	public static final String PERCENT_SIGN = "%";
	public static final String LETTER_X = "X";
	public static final String DIGIT_0 = "0";
	public static final String DIGIT_1 = "1";
	
	public static String lowerFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toLowerCase() + str.substring(1);
		}
	}
	
	public static String upperFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toUpperCase() + str.substring(1);
		}
	}

	//***************
	//JSTL用方法 开始
	//***************
	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @author yuxiaoyu
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return EMPTY;
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes(Constants.GBK).length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return EMPTY;
	}

	/**
	 * 缩略字符串（替换html）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
	}
	
	/**
	 * 用竖线分割字符串
	 * @author yuxiaoyu
	 */
	public static List<String> splitByVerticalLine(String sourceStr) {
		if (isEmpty(sourceStr)) {
			return new ArrayList<String>();
		}
		String[] strArr = sourceStr.split(Constants.REG_VERTICAL_LINE);
		return Arrays.asList(strArr);
	}
	//***************
	//JSTL用方法 结束
	//***************
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	
	/**
	 * 获得i18n字符串
	 */
	public static String getMessage(String code, Object[] args) {
		LocaleResolver localLocaleResolver = SpringContextHolder.getBean(LocaleResolver.class);
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		Locale localLocale = localLocaleResolver.resolveLocale(request);
		return SpringContextHolder.getApplicationContext().getMessage(code, args, localLocale);
	}
	
	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
	
	/**
	 * 检查是不是邮箱
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		Pattern regex = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"); 
		Matcher matcher = regex.matcher(email);  
		return matcher.matches();  
	}
	
	/**
	 * 字符串为null时取默认值
	 * @Description 
	 * @author yuxiaoyu
	 * @date 2016年4月14日 下午4:36:24
	 */
	public static String defaultIfNull(String sourceStr, String defaultStr) {
		if (null == defaultStr) {
			return EMPTY;
		}
		if (null == sourceStr) {
			return defaultStr;
		}
		return sourceStr;
	}

	/**
	 * 字符串为null时取空字符串
	 * @Description 
	 * @author yuxiaoyu
	 * @date 2016年4月14日 下午4:36:24
	 */
	public static String emptyIfNull(Object sourceObj) {
		if (null == sourceObj) {
			return EMPTY;
		}
		return sourceObj.toString();
	}
	
	/**
	 * 对象判空(空字符敏感)
	 * @author yuxiaoyu
	 */
	public static boolean isEmpty(Object sourceObj) {
		if (null == sourceObj) {
			return true;
		}
		if(sourceObj instanceof String){
			String source = sourceObj.toString();
			return 0 == source.length();
		}
		if(sourceObj instanceof Collection){
			Collection<?> source = (Collection<?>)sourceObj;
			return 0 == source.size();
		}
		if(sourceObj instanceof Map){
			Map<?, ?> source = (Map<?, ?>)sourceObj;
			return 0 == source.size();
		}
		return false;
	}
	
	/**
	 * 用正则表达式截取字符串中匹配的片段
	 * @author yuxiaoyu
	 */
	public static String substrByRegex(String sourceStr, String regex){
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(sourceStr);
        m.find();
        return m.group();
	}
	
	/**
	 * 四舍五入保留两位小数
	 * @author yuxiaoyu
	 */
	public static Double round(Double price) {
		String result = String.format(Constants.DOUBLE_FORMAT, price);
		return Double.valueOf(result);
	}
	
	/**
	 * 补全Double成两位小数的字符串
	 * @author yuxiaoyu
	 */
	public static String fillUp(Double d) {
		if(isEmpty(d)){
			return null;
		}
		String result = String.valueOf(d);
		String[] strArr = result.split(Constants.REG_POINT);
		int digitSize = strArr[1].length();
		if(1 == digitSize){//一位小数直接补个0
			return result + DIGIT_0;
		}else if(2 < digitSize){//二位小数或以上四舍五入到二位
			d = round(d);
			return String.valueOf(d);
		}
		return result;
	}
	
	/**
	 * 补全Double成两位小数的字符串
	 * @author yuxiaoyu
	 */
	public static String fillUp(String s) {
		if(isEmpty(s)){
			return EMPTY;
		}
		try{
			Double d = Double.parseDouble(s);
			String result = fillUp(d);
			return result;
		}catch(Exception e){
			return EMPTY;
		}
	}
	
	/**
	 * 处理链接地址特殊字符（"&"）
	 * @athor shuxin
	 * @date 2016年7月4日下午6:10:51
	 * @param href
	 * @return
	 * String 
	 */
	public static String handlerHref(String href){
		String str = "&amp;";
		if(href.indexOf(str) != -1){
			href = href.replaceAll("&amp;", "&");
		}
		return href;
	}
	
	// 过滤emoji表情
	public static String filterEmoji(String source) {
		if (source != null) {
			Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE
					| Pattern.CASE_INSENSITIVE);
			Matcher emojiMatcher = emoji.matcher(source);
			if (emojiMatcher.find()) {
				source = emojiMatcher.replaceAll(EMPTY);
				return source;
			}
			return source;
		}
		return source;
	}
	
	private final static String mHexStr = "0123456789ABCDEF";  
	public static String hexStr2Str(String hexStr){  
        hexStr = hexStr.toString().trim().replace(" ", "").toUpperCase(Locale.US);
        char[] hexs = hexStr.toCharArray();  
        byte[] bytes = new byte[hexStr.length() / 2];  
        int iTmp = 0x00;;  

        for (int i = 0; i < bytes.length; i++){  
            iTmp = mHexStr.indexOf(hexs[2 * i]) << 4;  
            iTmp |= mHexStr.indexOf(hexs[2 * i + 1]);  
            bytes[i] = (byte) (iTmp & 0xFF);  
        }  
        return new String(bytes);  
    }
}
