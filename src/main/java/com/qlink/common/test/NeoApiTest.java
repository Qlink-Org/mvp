package com.qlink.common.test;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.qlink.common.utils.DigestUtils;
import com.qlink.common.utils.neo.http.HttpNeoSession;
import com.qlink.modules.sys.security.api.StatelessAuthcFilter;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import net.sf.json.JSONObject;

public class NeoApiTest {

	@Test
	public void saveSsid() throws UnsupportedEncodingException {
		HttpRequest request = HttpRequest.get("http://localhost/api/neo/ssId/save.json");

		JSONObject obj = new JSONObject();
		obj.put("appid", "MIFI");
		obj.put("timestamp", "1345678990");

		// params
		Map<String, String> recordMap = new HashMap<String, String>();
		recordMap.put("ssId", "2c23118e3e9a4b1f8fde5973a2db272b");
		recordMap.put("mac", "shuxin");
		recordMap.put("p2pId", "123123123123123123123123");
		JSONObject params = JSONObject.fromObject(recordMap);

		obj.put("params", params);

		//sign
		String sign = DigestUtils.getSignature(obj, "05cd19c64d5f4faabd27c74607fd1f51", "UTF-8");

		// params
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("appid", obj.get("appid"));
		jsonParam.put("params", params);
		jsonParam.put("timestamp", obj.get("timestamp"));
		jsonParam.put("sign", sign);

		String bodyStr = jsonParam.toString();
		bodyStr =  URLEncoder.encode(bodyStr, "UTF-8");

		request.method("post");
		request.queryEncoding("UTF-8");
		request.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");
		request.header("Content-Type", "application/json;charset=utf-8");

		request.body(bodyStr);
		HttpResponse response = request.send();
		String result = response.bodyText();

		System.out.println(result);

	}
	
	@Test
	public void querySsid() throws UnsupportedEncodingException {
		HttpRequest request = HttpRequest.get("http://localhost/api/neo/ssId/query.json");

		JSONObject obj = new JSONObject();
		obj.put("appid", "MIFI");
		obj.put("timestamp", "1345678990");

		// 数据参数
		Map<String, String> recordMap = new HashMap<String, String>();
		recordMap.put("ssId", "2c23118e3e9a4b1f8fde5973a2db272b");
		JSONObject params = JSONObject.fromObject(recordMap);
		obj.put("params", params);

		String sign = DigestUtils.getSignature(obj, "05cd19c64d5f4faabd27c74607fd1f51", "UTF-8");

		// 参数
		JSONObject jsonParam = new JSONObject();
		jsonParam.put(StatelessAuthcFilter.PARAM_APPID, obj.get("appid"));
		jsonParam.put(StatelessAuthcFilter.PARAM_NAME, params);
		jsonParam.put(StatelessAuthcFilter.PARAM_TIMESTAMP, obj.get("timestamp"));
		jsonParam.put(StatelessAuthcFilter.PARAM_SIGN, sign);

		String bodyStr = jsonParam.toString();
		bodyStr =  URLEncoder.encode(bodyStr, "UTF-8");

		request.method("post");
		request.queryEncoding("UTF-8");
		request.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");
		request.header("Content-Type", "application/json;charset=utf-8");

		request.body(bodyStr);
		HttpResponse response = request.send();
		String result = response.bodyText();

		System.out.println(result);

	}
	
	@Test
	public void saveRecord() throws UnsupportedEncodingException {
		HttpRequest request = HttpRequest.get("http://47.90.50.172/api/neo/record/save.json");

		JSONObject obj = new JSONObject();
		obj.put("appid", "MIFI123");
		obj.put("timestamp", "1345678990");

		// 数据参数
		Map<String, String> recordMap = new HashMap<String, String>();
		recordMap.put("recordId", "33333fffffffffff2222222222222");
		recordMap.put("addressFrom", "00-00-00-00-00-00-00-E0");
		recordMap.put("formP2pId", "857fe98f2dc84e15bc10376a5d470e11");
		recordMap.put("addressTo", "857fe98f2dc84e15bc10376a5d470e11");
		recordMap.put("toP2pId", "857fe98f2dc84e15bc10376a5d470e11");
		recordMap.put("qlc", "6521.5");
		JSONObject params = JSONObject.fromObject(recordMap);

		obj.put("params", params);

		String sign = DigestUtils.getSignature(obj, "05cd19c64d5f4faabd27c74607fd1f51", "UTF-8");

		// 参数
		JSONObject jsonParam = new JSONObject();
		jsonParam.put(StatelessAuthcFilter.PARAM_APPID, obj.get("appid"));
		jsonParam.put(StatelessAuthcFilter.PARAM_NAME, params);
		jsonParam.put(StatelessAuthcFilter.PARAM_TIMESTAMP, obj.get("timestamp"));
		jsonParam.put(StatelessAuthcFilter.PARAM_SIGN, sign);

		String bodyStr = jsonParam.toString();
		bodyStr =  URLEncoder.encode(bodyStr, "UTF-8");

		request.method("post");
		request.queryEncoding("UTF-8");
		request.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");
		request.header("Content-Type", "application/json;charset=utf-8");

		request.body(bodyStr);
		HttpResponse response = request.send();
		String result = response.bodyText();

		System.out.println(result);

	}

	@Test
	public void queryRid() throws UnsupportedEncodingException {
		HttpRequest request = HttpRequest.get("http://47.90.50.172/api/neo/record/query.json");

		JSONObject obj = new JSONObject();
		obj.put("appid", "MIFI");
		obj.put("timestamp", "1345678990");

		// 数据参数
		Map<String, String> recordMap = new HashMap<String, String>();
		recordMap.put("recordId", "123");
		JSONObject params = JSONObject.fromObject(recordMap);
		obj.put("params", params);

		String sign = DigestUtils.getSignature(obj, "05cd19c64d5f4faabd27c74607fd1f51", "UTF-8");

		// 参数
		JSONObject jsonParam = new JSONObject();
		jsonParam.put(StatelessAuthcFilter.PARAM_APPID, obj.get("appid"));
		jsonParam.put(StatelessAuthcFilter.PARAM_NAME, params);
		jsonParam.put(StatelessAuthcFilter.PARAM_TIMESTAMP, obj.get("timestamp"));
		jsonParam.put(StatelessAuthcFilter.PARAM_SIGN, sign);

		String bodyStr = jsonParam.toString();
		bodyStr =  URLEncoder.encode(bodyStr, "UTF-8");

		request.method("post");
		request.queryEncoding("UTF-8");
		request.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");
		request.header("Content-Type", "application/json;charset=utf-8");

		request.body(bodyStr);
		HttpResponse response = request.send();
		String result = response.bodyText();

		System.out.println(result);

	}
	
	@Test
	public void testJsonRpc(){
		try {
    		JSONArray parameters = new JSONArray();
    		parameters.add("0xecc6b20d3ccac1ee9ef109af5a7cdb85706b1df9");
    		parameters.add("746f74616c537570706c79");
    		Credentials credentials = new UsernamePasswordCredentials("", "");
			URI uri = new URI("http", null, "seed5.neo.org", 10332, null, null, null);
			HttpNeoSession session  = new HttpNeoSession(uri, credentials);
        	com.alibaba.fastjson.JSONObject request = createRequest("getstorage", parameters);
        	com.alibaba.fastjson.JSONObject response = session.sendAndReceive(request);
        	System.out.println(response.toJSONString());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	private com.alibaba.fastjson.JSONObject createRequest(String functionName) throws JSONException {
		return createRequest(functionName, new JSONArray());
	}
	
	private com.alibaba.fastjson.JSONObject createRequest(String functionName, JSONArray parameters) throws JSONException {
		com.alibaba.fastjson.JSONObject request = new com.alibaba.fastjson.JSONObject();
		request.put("jsonrpc", "2.0");
		request.put("id", UUID.randomUUID().toString());
		request.put("method", functionName);
		request.put("params", parameters);

		return request;
	}

}
