package com.qlink.common.utils.neo.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.qlink.common.config.Global;
import com.qlink.common.utils.neo.client.exceptions.NeoClientException;
import com.qlink.common.utils.neo.http.HttpNeoSession;

/**
 * 一个API来访问一个比特币服务器java
 * 
 * @Description
 * @author shuxin
 * @date 2017年7月11日 上午11:11:35
 */
public class NeoClient {

	public static Logger logger = LoggerFactory.getLogger(NeoClient.class);

	private HttpNeoSession session = null;

	/**
	 * 创建bitcoin客户端实例
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param host
	 * @param login
	 * @param password
	 * @param port
	 */
	public NeoClient(String host, String login, String password, int port) {
		try {
			Credentials credentials = new UsernamePasswordCredentials(login, password);
			URI uri = new URI("http", null, host, port, null, null, null);
			session = new HttpNeoSession(uri, credentials);
		} catch (URISyntaxException e) {
			throw new NeoClientException("无法找到正确的主机：" + host, e);
		}
	}

	/**
	 * 创建一个默认的8332端口号bitcoinclient
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param host
	 * @param login
	 * @param password
	 */
	public NeoClient() {
		this("127.0.0.1", Global.getConfig("rpcuser"), Global.getConfig("rpcpassword"), 20332);
	}

	/**
	 * storage.put 接口调用
	 * 
	 * @Description
	 * @param scriptHash
	 * @param method
	 * @param key
	 * @param val
	 * @return
	 * @return Map<String,String>
	 * @author shuxin
	 * @date 2017年12月7日 下午2:03:32
	 */
	public Map<String, String> insertVal(String scriptHash, String JsonRpcMethod, String contractMethod, String key,
			String val) {
		Map<String, String> resultMap = new HashMap<>();
		if (!(StringUtils.isNotBlank(scriptHash) && StringUtils.isNotBlank(JsonRpcMethod)
				&& StringUtils.isNotBlank(contractMethod) && StringUtils.isNotBlank(key)
				&& StringUtils.isNotBlank(val))) {
			resultMap.put("code", "0");
			resultMap.put("msg", "Parameter values can not be empty");
			return resultMap;
		}
		try {
			JSONArray parameters = new JSONArray();
			parameters.add(scriptHash);
			parameters.add(contractMethod);
			JSONArray pp = new JSONArray();
			JSONObject obj1 = new JSONObject();
			obj1.put("type", "String");
			obj1.put("value", key);
			pp.add(obj1);
			JSONObject obj2 = new JSONObject();
			obj2.put("type", "String");
			obj2.put("value", val);
			pp.add(obj2);
			parameters.add(pp);
			JSONObject request = createRequest(JsonRpcMethod, parameters);
			JSONObject response = session.sendAndReceive(request);
			if (response.containsKey("result") && response.getJSONObject("result").containsKey("state") && response.getJSONObject("result").getString("state").contains("HALT")) { // 执行成功有返回值
				JSONObject resultJson = response.getJSONObject("result");
				if(resultJson.containsKey("stack")){
					JSONArray stackArray = resultJson.getJSONArray("stack");
					for (int i = 0; i < stackArray.size(); i++) {
						JSONObject jsonObj = stackArray.getJSONObject(i);
						if ("Array".equals(jsonObj.getString("type"))) {
							JSONArray stringJson = jsonObj.getJSONArray("value");
							String code = hexStr2Str(stringJson.getJSONObject(0).getString("value"));
							String msg = hexStr2Str(stringJson.getJSONObject(1).getString("value"));
							String tx = "";
							if("1".equals(code)){ //put成功取tx
								 tx = resultJson.getString("tx");
							}
							resultMap.put("code", code);
							resultMap.put("msg", msg);
							resultMap.put("tx", tx);
							logger.info("通过jsonRPCAPI（" + JsonRpcMethod + "）调用合约" + contractMethod + "方法，正确返回，jsonRpc接口返回结果："
									+ jsonObj.toJSONString());
						} else {
							resultMap.put("code", "0");
							resultMap.put("msg", "Invocation of contract return value type error");
							logger.info("通过jsonRPCAPI（" + JsonRpcMethod + "）调用合约" + contractMethod + "方法，返回类型错误，jsonRpc接口返回结果："
									+ jsonObj.toJSONString());
						}
					}
				} else {
					logger.info("通过jsonRPCAPI（" + JsonRpcMethod + "）调用合约" + contractMethod + "方法，返回结果没有stack，jsonRpc接口返回结果："
							+ resultJson.toJSONString());
				}
			} else if (response.containsKey("error")) {
				resultMap.put("code", "0");
				resultMap.put("msg", "Return error prompt (request parameter formatting error)");
				logger.info("通过jsonRPCAPI（" + JsonRpcMethod + "）调用合约" + contractMethod + "方法，返回错误提示（请求参数格式错误导致），jsonRpc接口返回结果："
						+ response.toJSONString());
			} else {
				resultMap.put("code", "0");
				resultMap.put("msg", "The parameter problem leads to the failure of the contract call");
				logger.info("通过jsonRPCAPI（" + JsonRpcMethod + "）调用合约" + contractMethod + "方法，请求终止（请求参数问题导致）, jsonRpc接口返回结果："
						+ response.toJSONString());
			}
			return resultMap;
		} catch (Exception e) {
			resultMap.put("code", "0");
			resultMap.put("msg", "Calling the jsonRPC interface to report the error exception");
			logger.info("调用jsonRPCAPI（" + JsonRpcMethod + "）方法，错误异常信息：" + e.getMessage());
		}
		return resultMap;
	}

	public Map<String, String> queryByKey(String scriptHash, String JsonRpcMethod, String contractMethod, String key) {
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("code", "0");
		resultMap.put("result", "");
		if (!(StringUtils.isNotBlank(scriptHash) && StringUtils.isNotBlank(JsonRpcMethod)
				&& StringUtils.isNotBlank(contractMethod))) {
			resultMap.put("code", "0");
			resultMap.put("msg", "Parameter values can not be empty");
			return resultMap;
		}
		try {
			JSONArray parameters = new JSONArray();
			parameters.add(scriptHash);
			parameters.add(contractMethod);
			JSONArray pp = new JSONArray();
			if(StringUtils.isNotBlank(key)){
				JSONObject obj1 = new JSONObject();
				obj1.put("type", "String");
				obj1.put("value", key);
				pp.add(obj1);
			}
			parameters.add(pp);
			JSONObject request = createRequest(JsonRpcMethod, parameters);
			JSONObject response = session.sendAndReceive(request);
			if (response.containsKey("result") && response.getJSONObject("result").containsKey("state") && response.getJSONObject("result").getString("state").contains("HALT")) { // 执行成功有返回值
				JSONObject resultJson = response.getJSONObject("result");
				if(resultJson.containsKey("stack")) {
					JSONArray stackArray = resultJson.getJSONArray("stack");
					for (int i = 0; i < stackArray.size(); i++) {
						JSONObject jsonObj = stackArray.getJSONObject(i);
						if ("ByteArray".equals(jsonObj.getString("type"))) {
							String str = hexStr2Str(jsonObj.getString("value"));
							if(StringUtils.isNotBlank(str)){
								resultMap.put("code", "1");
								resultMap.put("result", str);
							}
							logger.info("通过jsonRPCAPI（" + JsonRpcMethod + "）调用合约" + contractMethod + "方法，正确返回，jsonRpc接口返回结果："
									+ response.toJSONString());
						}
					}
				} else {
					logger.info("通过jsonRPCAPI（" + JsonRpcMethod + "）调用合约" + contractMethod + "方法，返回结果没有stack，jsonRpc接口返回结果："
							+ response.toJSONString());
				}
			} else if (response.containsKey("error")) {
				logger.info("通过jsonRPCAPI（" + JsonRpcMethod + "）调用合约" + contractMethod + "方法，返回错误提示（请求参数格式错误导致），jsonRpc接口返回结果："
						+ response.toJSONString());
			} else {
				logger.info("通过jsonRPCAPI（" + JsonRpcMethod + "）调用合约" + contractMethod + "方法，请求终止（请求参数问题导致）, jsonRpc接口返回结果："
						+ response.toJSONString());
			}
			return resultMap;
		} catch (Exception e) {
			resultMap.put("code", "0");
			resultMap.put("result", "Calling the jsonRPC interface to report the error exception");
			logger.info("调用jsonRPCAPI（" + JsonRpcMethod + "）方法，错误异常信息：" + e.getMessage());
		}
		return resultMap;
	}
	
	/** 
	 * 广播交易
	 * @Description 
	 * @param JsonRpcMethod
	 * @param tx
	 * @return 
	 * @return Map<String,String>  
	 * @author shuxin
	 * @date 2017年12月15日 上午11:48:23 
	 */ 
	public Map<String, String> sendrawtransaction(String JsonRpcMethod, String tx) {
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("code", "0");
		resultMap.put("result", "");
		if (!(StringUtils.isNotBlank(tx))) {
			resultMap.put("code", "0");
			resultMap.put("msg", "Tx Parameter values can not be empty");
			return resultMap;
		}
		try {
			JSONArray parameters = new JSONArray();
			parameters.add(tx);
			JSONObject request = createRequest(JsonRpcMethod, parameters);
			JSONObject response = session.sendAndReceive(request);
			if (response.containsKey("result")) { // 执行成功有返回值
				if(response.getBooleanValue("result")) {
					resultMap.put("code", "1");
					resultMap.put("result", "success");
					logger.info("通过jsonRPCAPI（" + JsonRpcMethod + "）方法，广播交易成功，jsonRpc接口返回结果："
								+ response.toJSONString());
				} else {
					logger.info("通过jsonRPCAPI（" + JsonRpcMethod + "）方法，广播交易失败，jsonRpc接口返回结果："
							+ response.toJSONString());
				}
			} else if (response.containsKey("error")) {
				logger.info("通过jsonRPCAPI（" + JsonRpcMethod + "） "+" 方法，返回错误提示（请求参数格式错误导致），jsonRpc接口返回结果："
						+ response.toJSONString());
			} else {
				logger.info("通过jsonRPCAPI（" + JsonRpcMethod + "）方法，请求终止（请求参数问题导致）, jsonRpc接口返回结果："
						+ response.toJSONString());
			}
			return resultMap;
		} catch (Exception e) {
			resultMap.put("code", "0");
			resultMap.put("result", "Calling the jsonRPC interface to report the error exception");
			logger.info("调用jsonRPCAPI（" + JsonRpcMethod + "）方法，错误异常信息：" + e.getMessage());
		}
		return resultMap;
	}

	private JSONObject createRequest(String functionName, JSONArray parameters) throws JSONException {
		JSONObject request = new JSONObject();
		request.put("jsonrpc", "2.0");
		request.put("id", UUID.randomUUID().toString());
		request.put("method", functionName);
		request.put("params", parameters);

		return request;
	}

	private JSONObject createRequest(String functionName) throws JSONException {
		return createRequest(functionName, new JSONArray());
	}

	private final static String mHexStr = "0123456789ABCDEF";

	private static String hexStr2Str(String hexStr) {
		hexStr = hexStr.toString().trim().replace(" ", "").toUpperCase(Locale.US);
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int iTmp = 0x00;
		for (int i = 0; i < bytes.length; i++) {
			iTmp = mHexStr.indexOf(hexs[2 * i]) << 4;
			iTmp |= mHexStr.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (iTmp & 0xFF);
		}
		return new String(bytes);
	}
}
