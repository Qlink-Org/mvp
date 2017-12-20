package com.qlink.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qlink.common.utils.StringUtils;


public class ToolUtil {
	private static final String X_Real_IP = "X-Real-IP";
	private static final String X_Forwarded_For = "X-Forwarded-For";
	private static final String Proxy_Client_IP = "Proxy-Client-IP";
	private static final String WL_Proxy_Client_IP = "WL-Proxy-Client-IP";
	
	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * @return String
	 */ 
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
	
	/**
	 * 取出一个指定长度大小的随机正整数
	 * @author yuxiaoyu
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
	
	/**
	 * 将JSONObject对象里的key都转换成小写
	 * @author yuxiaoyu
	 */
	public static JSONObject transObject(JSONObject o1){
        JSONObject o2=new JSONObject();
         Iterator it = o1.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                Object object = o1.get(key);
                if(object.getClass().toString().endsWith("String")){
                    o2.accumulate(key.toLowerCase(), object);
                }else if(object.getClass().toString().endsWith("JSONObject")){
                    o2.accumulate(key.toLowerCase(), ToolUtil.transObject((JSONObject)object));
                }else if(object.getClass().toString().endsWith("JSONArray")){
                    o2.accumulate(key.toLowerCase(), ToolUtil.transArray(o1.getJSONArray(key)));
                } else {
                	o2.accumulate(key.toLowerCase(), object);
                }
            }
            return o2;
    }
    public static JSONArray transArray(JSONArray o1){
        JSONArray o2 = new JSONArray();
        for (int i = 0; i < o1.size(); i++) {
            Object jArray=o1.getJSONObject(i);
            if(jArray.getClass().toString().endsWith("JSONObject")){
                o2.add((ToolUtil.transObject((JSONObject)jArray)));
            }else if(jArray.getClass().toString().endsWith("JSONArray")){
                o2.add(ToolUtil.transArray((JSONArray)jArray));
            }
        }
        return o2;
    }
	
    /**
     * 创建指定数量的随机字符串 
     * @author yuxiaoyu
     */
	 public static String createRandom(boolean numberFlag, int length){  
	  String retStr = "";  
	  String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";  
	  int len = strTable.length();  
	  boolean bDone = true;  
	  do {  
	   retStr = "";  
	   int count = 0;  
	   for (int i = 0; i < length; i++) {  
	    double dblR = Math.random() * len;  
	    int intR = (int) Math.floor(dblR);  
	    char c = strTable.charAt(intR);  
	    if (('0' <= c) && (c <= '9')) {  
	     count++;  
	    }  
	    retStr += strTable.charAt(intR);  
	   }  
	   if (count >= 2) {  
	    bDone = false;  
	   }  
	  } while (bDone);  
	  
	  return retStr;  
	 } 
	 
	/**
	 * 手机号验证
	 * @author yuxiaoyu
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}
	
	/**
	 * 获到客户端IP地址
	 * 像移动网关一样，iisforward这个ISAPI过滤器也会对request对象进行再包装，附加一些WLS要用的头信息。
	 * 这种情况下，直接用request.getRemoteAddr()是无法取到真正的客户IP的。实际的iisforward附加头如下
     * WL-Proxy-Client-IP=211.161.1.239  
     * Proxy-Client-IP=211.161.1.239  
     * X-Forwarded-For=211.161.1.239  
     * WL-Proxy-Client-Keysize=   
     * WL-Proxy-Client-Secretkeysize=   
     * X-WebLogic-Request-ClusterInfo=true  
     * X-WebLogic-KeepAliveSecs=30  
     * X-WebLogic-Force-JVMID=-327089098  
     * WL-Proxy-SSL=false  
	 * @author yuxiaoyu
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(X_Real_IP);//如果有，则越过所有代理反映真实客户端IP
		if (StringUtils.isEmpty(ip)) {
			ip = request.getHeader(X_Forwarded_For);//从客户端IP开始，反映各级代理IP，如1.1.1.1, 2.2.2.2, 3.3.3.3
		}
		if (StringUtils.isEmpty(ip)) {
			ip = request.getHeader(Proxy_Client_IP);
		}
		if (StringUtils.isEmpty(ip)) {
			ip = request.getHeader(WL_Proxy_Client_IP);
		}
		if (StringUtils.isEmpty(ip)) {
			ip = request.getRemoteAddr();
		}
		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		int index = ip.indexOf(StringUtils.COMMA);
		if (index > 0) {
			ip = ip.substring(0, index);
		}
		if (StringUtils.isEmpty(ip)) {
			return StringUtils.EMPTY;
		}
		return ip;
	}
	
	/**
	 * 获取随机字符串
	 * @author yuxiaoyu
	 */
	public static String getNonceStr() {
		String currTime = getCurrTime();// 随机数
		String strTime = currTime.substring(8, currTime.length());// 8位日期
		String strRandom = buildRandom(4) + "";// 四位随机数
		return strTime + strRandom;// 10位序列号,可以自行调整。
		//return SignUtil.createNonceStr();
	}
	
	/**
	 * 获取时间戳（秒）
	 * @author yuxiaoyu
	 */
	public static String getTimestamp() {
		long timeInMillis = Calendar.getInstance().getTimeInMillis();
		String timestamp = String.valueOf(timeInMillis);
		return timestamp.substring(0, 10);
	}

	/**
	 * SHA1加密
	 * @author yuxiaoyu
	 */
	public static String sha1(String decript) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return StringUtils.EMPTY;
	}
}
