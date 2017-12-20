package com.qlink.modules.neo.web;

import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qlink.common.utils.DateUtils;
import com.qlink.common.web.BaseController;
import com.qlink.modules.neo.service.NeoService;
import com.qlink.modules.sys.service.LogService;
import com.qlink.modules.utils.ReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/api/neo", produces = "application/json")
@Api(value = "/neoApi", description = "neoRPC调用接口")
public class NeoAPIController extends BaseController {
	public static Logger logger = LoggerFactory.getLogger(NeoAPIController.class);
	
	@Autowired
	private NeoService neoService;
	
	@Autowired
	private LogService logService;
	
	@RequestMapping(value = "/ssId/save.json", method = { RequestMethod.POST })
	public @ResponseBody ResponseEntity<JSONObject> saveSsid(@ApiParam(value = "请求入参为JSON格式", required = true) @RequestBody String reqStr, HttpServletRequest request, HttpServletResponse response){
		JSONObject res = new JSONObject();
		res.put("msg", ReturnCode.ERR__1);
		res.put("code", "-1");
		JSONArray params = new JSONArray();
		try {
			JSONObject reqobj = JSONObject.fromObject(URLDecoder.decode(reqStr, "UTF-8"));
			String appId = reqobj.getString("appid");
			Object _params = reqobj.get("params");
			if (_params instanceof JSONObject) {
				params.add((JSONObject) _params);
			} else if (_params instanceof JSONArray) {
				params = (JSONArray) _params;
			}
			long currentTime1 = System.currentTimeMillis();
			logger.info("ssId保存接口，发送请求时间为："+DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "===请求的参数为：" + params.getJSONObject(0).toString());
			JSONObject json = neoService.saveSsi(params.getJSONObject(0));
			logger.info("ssId保存接口，请求完成时间为："+DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "===请求返回结果为：" + json.toString());
			logger.info("总耗时======="+(System.currentTimeMillis() - currentTime1)/1000 +"秒");
			return new ResponseEntity(json, HttpStatus.OK);
		}catch (Exception e) {
			logService.saveOperateLog(request, "调用/ssId/save.json 接口出现异常：[{}],接口参数：[{}]",e.getMessage(), params.getJSONObject(0).toString());
			res.put("code", "61450");
			res.put("msg", ReturnCode.ERR_61450 + ":" + e.getMessage());
		}
		logger.info("推送资源列表接口，请求完成时间为："+DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "===请求返回结果为：" + res.toString());
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	/** 
	 * query ssid
	 * @Description 
	 * @param reqStr
	 * @param request
	 * @param response
	 * @return 
	 * @return ResponseEntity<JSONObject>  
	 * @author shuxin
	 * @date 2017年12月11日 上午10:52:37 
	 */ 
	@RequestMapping(value = "/ssId/query.json", method = { RequestMethod.POST })
	public @ResponseBody ResponseEntity<JSONObject> querySsid(@ApiParam(value = "请求入参为JSON格式", required = true) @RequestBody String reqStr, HttpServletRequest request, HttpServletResponse response){
		JSONObject res = new JSONObject();
		res.put("msg", ReturnCode.ERR__1);
		res.put("code", "-1");
		JSONArray params = new JSONArray();
		try {
			JSONObject reqobj = JSONObject.fromObject(URLDecoder.decode(reqStr, "UTF-8"));
			Object _params = reqobj.get("params");
			if (_params instanceof JSONObject) {
				params.add((JSONObject) _params);
			} else if (_params instanceof JSONArray) {
				params = (JSONArray) _params;
			}
			logger.info(" ssId查询接口，发送请求时间为："+DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "===请求的参数为：" + params.getJSONObject(0).toString());
			JSONObject json =neoService.queryBySsid(params.getJSONObject(0));
			logger.info(" ssId查询接口，请求完成时间为："+DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "===请求返回结果为：" + json.toString());
			return new ResponseEntity(json,HttpStatus.OK);
		}catch (Exception e) {
			logService.saveOperateLog(request, "调用/ssId/query.json 接口出现异常：[{}],接口参数：[{}]",e.getMessage(), params.getJSONObject(0).toString());
			res.put("code", "61450");
			res.put("msg", ReturnCode.ERR_61450 + ":" + e.getMessage());
		}
		logger.info("ssId查询接口，请求完成时间为："+DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "===请求返回结果为：" + res.toString());
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/record/save.json", method = { RequestMethod.POST })
	public @ResponseBody ResponseEntity<JSONObject> saveRecord(@ApiParam(value = "请求入参为JSON格式", required = true) @RequestBody String reqStr, HttpServletRequest request, HttpServletResponse response){
		JSONObject res = new JSONObject();
		res.put("msg", ReturnCode.ERR__1);
		res.put("code", "-1");
		JSONArray params = new JSONArray();
		try {
			JSONObject reqobj = JSONObject.fromObject(URLDecoder.decode(reqStr, "UTF-8"));
			String appId = reqobj.getString("appid");
			Object _params = reqobj.get("params");
			if (_params instanceof JSONObject) {
				params.add((JSONObject) _params);
			} else if (_params instanceof JSONArray) {
				params = (JSONArray) _params;
			}
			long currentTime1 = System.currentTimeMillis();
			logger.info("record保存接口，发送请求时间为："+DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "===请求的参数为：" + params.getJSONObject(0).toString());
			JSONObject json = neoService.saveRecord(params.getJSONObject(0));
			logger.info("record保存接口，请求完成时间为："+DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "===请求返回结果为：" + json.toString());
			logger.info("总耗时======="+(System.currentTimeMillis() - currentTime1)/1000 +"秒");
			return new ResponseEntity(json, HttpStatus.OK);
		} catch (Exception e) {
			logService.saveOperateLog(request, "调用/record/save.json 接口出现异常：[{}],接口参数：[{}]",e.getMessage(), params.getJSONObject(0).toString());
			res.put("code", "61450");
			res.put("msg", ReturnCode.ERR_61450 + ":" + e.getMessage());
		}
		logger.info("record保存接口，请求完成时间为："+DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "===请求返回结果为：" + res.toString());
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	
	/** 
	 * 查询record接口
	 * @Description 
	 * @param reqStr
	 * @param request
	 * @param response
	 * @return 
	 * @return ResponseEntity<JSONObject>  
	 * @author shuxin
	 * @date 2017年12月11日 下午1:31:48 
	 */ 
	@RequestMapping(value = "/record/query.json", method = { RequestMethod.POST })
	public @ResponseBody ResponseEntity<JSONObject> queryRecord(@ApiParam(value = "请求入参为JSON格式", required = true) @RequestBody String reqStr, HttpServletRequest request, HttpServletResponse response){
		JSONObject res = new JSONObject();
		res.put("msg", ReturnCode.ERR__1);
		res.put("code", "-1");
		JSONArray params = new JSONArray();
		try {
			JSONObject reqobj = JSONObject.fromObject(URLDecoder.decode(reqStr, "UTF-8"));
			Object _params = reqobj.get("params");
			if (_params instanceof JSONObject) {
				params.add((JSONObject) _params);
			} else if (_params instanceof JSONArray) {
				params = (JSONArray) _params;
			}
			logger.info(" record查询接口，发送请求时间为："+DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "===请求的参数为：" + params.getJSONObject(0).toString());
			JSONObject json =neoService.queryByRid(params.getJSONObject(0));
			logger.info(" record查询接口，请求完成时间为："+DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "===请求返回结果为：" + json.toString());
			return new ResponseEntity(json,HttpStatus.OK);
		} catch (Exception e) {
			logService.saveOperateLog(request, "调用/record/query.json 接口出现异常：[{}],接口参数：[{}]",e.getMessage(), params.getJSONObject(0).toString());
			res.put("code", "61450");
			res.put("msg", ReturnCode.ERR_61450 + ":" + e.getMessage());
		}
		logger.info("record查询接口，请求完成时间为："+DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + "===请求返回结果为：" + res.toString());
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
}
