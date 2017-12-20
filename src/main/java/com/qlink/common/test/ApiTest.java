package com.qlink.common.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.qlink.common.utils.DigestUtils;
import com.qlink.modules.sys.security.api.StatelessAuthcFilter;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ApiTest {

	@Test
	public void getGoodsTest() {
		HttpRequest request = HttpRequest.get("http://192.168.0.56:8080/mifi/api/order/goods.json");

		JSONObject obj = new JSONObject();
		obj.put("appid", "5194c22e6c724672854583faaf1b54fc");
		obj.put("timestamp", "1345678990");

		// 数据参数
		Map<String, String> recordMap = new HashMap<String, String>();
		recordMap.put("channelId", "26086c4338d34089899ac33d92f690fb");
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
	public void deliveryOrderTest() {

		HttpRequest request = HttpRequest.get("http://192.168.0.56:8080/mifi/api/order/deliveryOrder.json");

    	JSONObject obj = new JSONObject();
    	obj.put("appid", "5194c22e6c724672854583faaf1b54fc");
    	obj.put("timestamp", "1345678990");
		
		// 数据参数
		Map<String, String> recordMap = new HashMap<String, String>();
		JSONArray dsnArr = new JSONArray();
		dsnArr.add("863789021823897");
		dsnArr.add("863789021915818");
		recordMap.put("osn", "FC23583485552505");
		recordMap.put("start_date", "2016-05-25 00:00:00");
		recordMap.put("end_date", "2016-05-27 00:00:00");
		recordMap.put("dsn", dsnArr.toString());
		recordMap.put("mccs", "310,311,312,313,314,315,316,460,461");
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
		
		request.method("post");
		request.queryEncoding("UTF-8");
		request.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");
		request.header("Content-Type", "application/json;charset=utf-8");

		request.body(bodyStr);
		HttpResponse response = request.send();
		String result = response.bodyText();
		
		System.out.println(result);
	}

}
