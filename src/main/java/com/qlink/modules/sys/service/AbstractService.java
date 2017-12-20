package com.qlink.modules.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qlink.common.utils.ToolUtil;
import com.qlink.modules.sys.utils.ApiUtils;
import com.qlink.modules.sys.utils.Constants;


/** 
 * 接口共用代码
 * @author yuxiaoyu
 */
public abstract class AbstractService {
	public static Logger logger = LoggerFactory.getLogger(AbstractService.class);

	/**
	 * 主方法
	 * @author yuxiaoyu
	 */
	public ResponseEntity<JSONObject> invoke(JSONObject requestObj, HttpServletRequest request, HttpServletResponse response, Object... objects) {
		// 打印带标识的日志
		String logId = (String) request.getAttribute(Constants.LOGID);// 日志ID，在ApiFilter中生成
		String requestUri = request.getRequestURI();
		logger.info(logId + requestUri + ToolUtil.getIpAddr(request));
		logger.info(logId + requestUri + request.getHeader(ApiUtils.COOKIE));
		if (!requestUri.contains(Constants.UPLOAD)) {// 文件上传接口请求太长不打印
			logger.info(logId + requestUri + requestObj);
		}

		// 业务处理
		JSONObject responseObj = ApiUtils.successResponse();
		try {
			service(request, response, requestObj, responseObj, objects);
		} catch (Exception e) {
			ApiUtils.sysErrResponse(e, responseObj, logger, logId);
		}
		// 响应消息日志
		Object data = responseObj.get(ApiUtils.DATA);
		if (data instanceof JSONArray && 1 == ((JSONArray) data).size()) {//长度为1的data数据，写入日志
			logger.info(logId + requestUri + responseObj);
		} else {// 长日志不打印data，节省空间
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(ApiUtils.CODE, responseObj.get(ApiUtils.CODE));
			jsonObject.put(ApiUtils.MSG, responseObj.get(ApiUtils.MSG));
			jsonObject.put(ApiUtils.SIZE, responseObj.get(ApiUtils.SIZE));
			jsonObject.put(ApiUtils.PREV, responseObj.get(ApiUtils.PREV));
			jsonObject.put(ApiUtils.NEXT, responseObj.get(ApiUtils.NEXT));
			logger.info(logId + requestUri + jsonObject);
		}
		return new ResponseEntity<JSONObject>(responseObj, HttpStatus.OK);
	}

	/**
	 * 业务实现
	 * @author yuxiaoyu
	 */
	public abstract void service(HttpServletRequest request, HttpServletResponse response, JSONObject requestObj, JSONObject responseObj,
			Object... objects) throws Exception;
}
