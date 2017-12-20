package com.qlink.modules.sys.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qlink.common.config.Global;
import com.qlink.common.mapper.JsonMapper;
import com.qlink.common.persistence.DataEntity;
import com.qlink.common.persistence.Page;
import com.qlink.common.utils.DateUtils;
import com.qlink.common.utils.IdGen;
import com.qlink.common.utils.StringUtils;
import com.qlink.modules.utils.ReturnCode;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 对外接口工具
 * @author yuxiaoyu
 */
public class ApiUtils {
	public static Logger logger = LoggerFactory.getLogger(ApiUtils.class);
	
	public static final String CODE = "code";
	public static final String MSG = "msg";
	public static final String DATA = "data";
	public static final String SIZE = "size";
	public static final String NEXT = "next";
	public static final String PREV = "prev";
	public static final String COOKIE = "cookie";
	public static final String SESSION_ID = "_SESSIONID";
	public static final String CN_LOG_ID = "日志标识=";
	public static final String ERROR_MSG = "接口异常！！";

	public static final String NOTIFY_REQUEST_FROM = "-----调用微信支付回调，请求来源";
	public static final String NOTIFY_REQUEST_PARAM = "-----调用微信支付回调，请求参数";
	public static final String NOTIFY_RESPONSE_PARAM = "-----调用微信支付回调，响应参数";

	/**
	 * 回调结果：成功
	 */
	public static final String NOTIFY_SUCCESS_RESPONSE = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
	/**
	 * 回调结果：参数格式校验错误
	 */
	public static final String NOTIFY_PARAM_ERROR_RESPONSE = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[参数格式校验错误]]></return_msg></xml>";
	/**
	 * 回调结果：签名错误
	 */
	public static final String NOTIFY_SIGN_ERROR_RESPONSE = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名错误]]></return_msg></xml>";

	/**
	 * 查询类接口构建基类响应属性
	 * @author yuxiaoyu
	 */
	public static void buildCommonAttributes(JSONObject jsonObj, DataEntity<?> record) {
		if (null != record.getCreateBy() && StringUtils.isNotBlank(record.getCreateBy().getId())) {
			jsonObj.put(Constants.CREATEBY, record.getCreateBy().getId());
		}
		if (null != record.getCreateDate()) {
			jsonObj.put(Constants.CREATEDATE, DateUtils.formatDateTime(record.getCreateDate()));
		}

		if (null != record.getUpdateBy() && StringUtils.isNotBlank(record.getUpdateBy().getId())) {
			jsonObj.put(Constants.UPDATEBY, record.getUpdateBy().getId());
		}
		if (null != record.getUpdateDate()) {
			jsonObj.put(Constants.UPDATEDATE, DateUtils.formatDateTime(record.getUpdateDate()));
		}

		jsonObj.put(Constants.REMARKS, record.getRemarks());
	}

	/** 
	 * 创建日志ID并存入请求
	 * @author yuxiaoyu
	 */ 
	public static String createLogId(HttpServletRequest request) {
		String logId = IdGen.uuid();
		request.setAttribute(Constants.LOGID, logId);
		return logId;
	}
	
	/** 
	 * 响应对象加入返回数据
	 * @author yuxiaoyu
	 */
	public static void putData(JSONObject responseObj, JSONArray jsonArr) {
		responseObj.put(ApiUtils.SIZE, jsonArr.size());
		responseObj.put(ApiUtils.DATA, jsonArr);
	}

	/** 
	 * java对象转JSONObject
	 * @author yuxiaoyu
	 */
	public static JSONObject beanToJsonObject(DataEntity<?> sourceObj) {
		String jsonStr = JsonMapper.getInstance().toJson(sourceObj);
		JSONObject jsonObject =  JSONObject.fromObject(jsonStr);
		addDate(jsonObject);
		formatPrice(jsonObject);
		return jsonObject;
	}

	/** 
	 * java对象转JSONArray
	 * @author yuxiaoyu
	 */
	public static JSONArray beanToJsonArray(List<?> sourceObj) {
		String jsonStr = JsonMapper.getInstance().toJson(sourceObj);
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		JSONObject jsonObject;
		for (Object o : jsonArray) {
			if(!(o instanceof JSONObject)){
				continue;
			}
			jsonObject = (JSONObject) o;
			addDate(jsonObject);
			formatPrice(jsonObject);
		}
		return jsonArray;
	}
	
	/** 
	 * @author yuxiaoyu
	 */ 
	private static void addDate(JSONObject jsonObject) {
		Date date;
		if(jsonObject.containsKey(Constants.CREATEDATE)){
			date = DateUtils.parseDate(jsonObject.get(Constants.CREATEDATE));
			date = addDate(date);
			jsonObject.put(Constants.CREATEDATE, DateUtils.formatDate(date, DateUtils.YYYY_MM_DD_HH_MM_SS));
		}
		if(jsonObject.containsKey(Constants.UPDATEDATE)){
			date = DateUtils.parseDate(jsonObject.get(Constants.UPDATEDATE));
			date = addDate(date);
			jsonObject.put(Constants.UPDATEDATE, DateUtils.formatDate(date, DateUtils.YYYY_MM_DD_HH_MM_SS));
		}
		if(jsonObject.containsKey(Constants.EXPIRYDATE)){
			date = DateUtils.parseDate(jsonObject.get(Constants.EXPIRYDATE));
			date = addDate(date);
			jsonObject.put(Constants.EXPIRYDATE, DateUtils.formatDate(date, DateUtils.YYYY_MM_DD_HH_MM_SS));
		}
		if(jsonObject.containsKey(Constants.USEDATE)){
			date = DateUtils.parseDate(jsonObject.get(Constants.USEDATE));
			date = addDate(date);
			jsonObject.put(Constants.USEDATE, DateUtils.formatDate(date, DateUtils.YYYY_MM_DD_HH_MM_SS));
		}
	}
	/** 
	 * 设置时间
	 * @author yuxiaoyu
	 */
	public static Date addDate(Date sourceDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDate);
		calendar.add(Calendar.HOUR_OF_DAY, 8);
		return calendar.getTime();
	}
	/** 
	 * 价格补全两位
	 * @author yuxiaoyu
	 */ 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void formatPrice(JSONObject jsonObject) {
		Iterator<Entry> iterator = jsonObject.entrySet().iterator();
		Entry entry;
		String formateedPrice;
		while(iterator.hasNext()){
			entry = iterator.next();
			if(entry.getValue() instanceof Double){
				formateedPrice = StringUtils.fillUp((Double)entry.getValue());
				entry.setValue(formateedPrice);
			}
		}
	}

	/**
	 * 构建响应json，内容：调用成功
	 * @author yuxiaoyu
	 */
	public static JSONObject successResponse() {
		JSONObject responseObj = new JSONObject();
		responseObj.put(CODE, ReturnCode.CODE_0);
		responseObj.put(MSG, ReturnCode.ERR_0);
		return responseObj;
	}

	/**
	 * 构建响应json，内容：参数格式不正确
	 * @author yuxiaoyu
	 */
	public static void invalidParamResponse(String errMessage, JSONObject responseObj) {
		responseObj.put(CODE, ReturnCode.CODE_61451);
		responseObj.put(MSG, ReturnCode.ERR_61451 + errMessage);
	}

	/**
	 * 构建响应json，内容：系统异常
	 * @author yuxiaoyu
	 */
	public static void sysErrResponse(Throwable e, JSONObject responseObj, Logger logger, String logId) {
		logger.error(ERROR_MSG, e);
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(CN_LOG_ID).append(logId).append(StringUtils.AND_SIGN);
		for(StackTraceElement s: e.getStackTrace()){
			stringBuffer.append(s.toString()).append(StringUtils.AND_SIGN);
		}
		logger.error(stringBuffer.toString());
		responseObj.put(CODE, ReturnCode.CODE_61450);
		responseObj.put(MSG, ReturnCode.ERR_61450 + StringUtils.COLON + e.getMessage());
	}

	/**
	 * 构建响应json，内容：登录状态异常
	 * @author yuxiaoyu
	 */
	public static void loginErrResponse(JSONObject responseObj) {
		responseObj.put(CODE, ReturnCode.CODE_46000);
		responseObj.put(MSG, ReturnCode.ERR_46000);
	}

	/**
	 * Map转xml DOM4J
	 * @author yuxiaoyu
	 */
	@SuppressWarnings("rawtypes")
	public static String mapToXml(Map<String, String> sourceMap, String rootElementName) {
		Document doc = DocumentHelper.createDocument();
		if (StringUtils.isEmpty(sourceMap)) {
			return doc.asXML();
		}
		Iterator<Entry<String, String>> iterator = sourceMap.entrySet().iterator();
		Entry entry;
		Element element = doc.addElement(rootElementName);
		while (iterator.hasNext()) {
			entry = iterator.next();
			element.addElement(entry.getKey().toString()).setText(entry.getValue().toString());
		}
		return doc.asXML();
	}

	/**
	 * xml转Map DOM4J
	 * @author yuxiaoyu
	 */
	@SuppressWarnings("rawtypes")
	public static Map xmlToMap(String xmlStr) {
		Map resultMap = new HashMap();
		if (StringUtils.isEmpty(xmlStr)) {
			return resultMap;
		}
		try {
			Document doc = DocumentHelper.parseText(xmlStr);
			Element root = doc.getRootElement();
			resultMap = (Map) parseElement(root);
		} catch (DocumentException documentException) {
			return resultMap;
		} catch (Exception e) {
			return new HashMap();
		}
		return resultMap;
	}

	/**
	 * 解析element，若为文本，则返回String，若为树枝节点，则返回map
	 * @author yuxiaoyu
	 */
	private static Object parseElement(Element element) {
		if (element.isTextOnly()) {
			return element.getTextTrim();
		}
		Iterator<?> iterator = element.elementIterator();
		Object obj;
		Element e;
		Map<String, Object> childMap = new HashMap<String, Object>();
		while (iterator.hasNext()) {
			obj = iterator.next();
			e = (Element) obj;
			childMap.put(e.getName(), parseElement(e));
		}
		return childMap;
	}

	/**
	 * 判断是否登陆并获取登陆用户信息
	 * @author yuxiaoyu
	 
	public static MallUser getSessionMallUser(HttpServletRequest request) {
		String sessionId = StringUtils.EMPTY;
		if (null == request.getCookies()) {
			return null;
		}
		for (Cookie cookie : request.getCookies()) {
			if (ApiUtils.SESSION_ID.equals(cookie.getName())) {
				sessionId = cookie.getValue();
				break;
			}
		}
		if (StringUtils.isEmpty(sessionId)) {
			return null;
		}
		MallUser sessionMallUser = (MallUser) CacheUtils.getSession(sessionId);
		if (null == sessionMallUser || StringUtils.isBlank(sessionMallUser.getUserId())) {
			return null;
		}
		return sessionMallUser;
	}
	 	*/
	/** 
	 * 校验国家编码
	 * @author yuxiaoyu
	 */
	public static boolean isCountryCodeValid(JSONObject responseObj, Object countryCode) {
		if (!(countryCode instanceof String)) {
			invalidParamResponse("|[countryCode]格式错误!", responseObj);
			return false;
		}
		String dictVal = DictUtils.getDictLabel(countryCode.toString(), "bg_base_country", StringUtils.EMPTY);
		if (StringUtils.isEmpty(dictVal)) {
			invalidParamResponse("|[countryCode]不存在对应国家!", responseObj);
			return false;
		}
		return true;
	}
	
	/**
	 * 发送http请求（jodd http）
	 * @author yuxiaoyu
	 */
	public static String sendHttp(String url, String method, String bodyText) {
		HttpRequest request = HttpRequest.get(url);
		request.queryEncoding(Constants.UTF8);
		if(StringUtils.isEmpty(method)){
			request.method(Constants.POST);
		}else{
			request.method(method);
		}
		if(!StringUtils.isEmpty(bodyText)){
			request.body(bodyText);
		}
		HttpResponse response = request.send();
		String result = response.bodyText();
		return result;
	}
	
	/**
	 * 从微信侧获取accessToken
	 * @author yuxiaoyu
	 */
	public static String requestAccessToken(Logger logger, String logId) {
		String appId = Global.getConfig(Constants.CONF_WX_JSAPI_APPID);
		String sdkKey = Global.getConfig(Constants.CONF_WX_JSSDK_KEY);
		String tokenUrl = Global.getConfig(Constants.CONF_WX_JS_TOKENURL);
		tokenUrl += StringUtils.QUESTION_MARK + "grant_type=client_credential&appid=" + appId + "&secret=" + sdkKey;
		if(null != logger){
			logger.info(logId + tokenUrl);
		}
		String httpBody = sendHttp(tokenUrl, Constants.GET, null);
		if(null != logger){
			logger.info(logId + httpBody);
		}
		JSONObject jsonObj = JSONObject.fromObject(httpBody);
		return jsonObj.getString(Constants.ACCESS_TOKEN);
	}
	
	/**
	 * 从微信侧获取ticket
	 * @author yuxiaoyu
	 */
	public static Object requestTicket(String accessToken, Logger logger, String logId) {
		String ticketUrl = Global.getConfig(Constants.CONF_WX_JS_TICKETURL);
		ticketUrl += StringUtils.QUESTION_MARK + "access_token=" + accessToken + "&type=jsapi";
		logger.info(logId + ticketUrl);
		String httpBody = sendHttp(ticketUrl, Constants.GET, null);
		logger.info(logId + httpBody);
		//{"errcode":42001,"errmsg":"access_token expired hint: [OnnHWa0699vr22]"}
		//{"errcode":40001,"errmsg":"invalid credential, access_token is invalid or not latest hint: [z5f650471vr21]"}
		//{"errcode":0,"errmsg":"ok","ticket":"sM4AOVdWfPE4DxkXGEs8VOz8bBcc31HHJNfpZcvqZoz-hxw6_PVCzUnEU0rIXBcquFfjaKjjPs1_iQnro1Nf-A","expires_in":7200}
		JSONObject jsonObj = JSONObject.fromObject(httpBody);
		return jsonObj.get(Constants.TICKET);
	}
	
	/** 
	 * 接口分页参数初始化
	 * @author yuxiaoyu
	 * @param <T>
	 */ 
	public static <T> Page<T> initialPage(JSONObject requestObj, boolean useDefault) {
		int pageNo = 1;// 当前页码
		int pageSize = Integer.valueOf(Global.getConfig(Constants.CONF_PAGE_SIZE)); // 页面大小，设置为“-1”表示不进行分页（分页无效）
		if (requestObj.containsKey(Constants.PAGENO) && StringUtils.isNumeric(requestObj.getString(Constants.PAGENO))) {
			pageNo = Integer.valueOf(requestObj.getString(Constants.PAGENO));
		}else if(!useDefault){
			return new Page<T>();
		}
		if (requestObj.containsKey(Constants.PAGESIZE) && StringUtils.isNumeric(requestObj.getString(Constants.PAGESIZE))) {
			pageSize = Integer.valueOf(requestObj.getString(Constants.PAGESIZE));
		}else if(!useDefault){
			return new Page<T>();
		}
		Page<T> page = new Page<T>(pageNo, pageSize);
		return page;
	}
}
