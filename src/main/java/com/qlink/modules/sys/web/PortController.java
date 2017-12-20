package com.qlink.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qlink.modules.sys.service.AbstractService;
import com.qlink.modules.sys.service.AreaService;

import io.swagger.annotations.Api;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value = "/itf", produces = "application/json")
@Api(value = "/malluser", description = "相关接口")
public class PortController {
	public static Logger logger = LoggerFactory.getLogger(PortController.class);
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = "/area.json", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<JSONObject> area(@RequestBody JSONObject requestObj, HttpServletRequest request, HttpServletResponse response) {
		class Service extends AbstractService {
			public void service(HttpServletRequest request, HttpServletResponse response, JSONObject requestObj, JSONObject responseObj, Object... objects) {
				areaService.findAndBuildJson(requestObj, responseObj);
			}
		}
		return new Service().invoke(requestObj, request, response);
	}
}
