package com.qlink.modules.neo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlink.common.persistence.Page;
import com.qlink.modules.neo.condition.NeoSsidCondition;
import com.qlink.modules.neo.entity.NeoSsid;
import com.qlink.modules.neo.service.NeoService;

@Controller
@RequestMapping(value = "${adminPath}/neo/ssid")
public class NeoSsidController {
	
	@Autowired
	private NeoService neoService;
	
	/** 
	 * 分页列表
	 * @Description 
	 * @param neoSsidCondition
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @return String  
	 * @author shuxin
	 * @date 2017年12月14日 下午1:51:02 
	 */ 
	@RequiresPermissions("neo:ssid:view")
	@RequestMapping(value = {"list", ""})
	public String list(NeoSsidCondition neoSsidCondition, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<NeoSsid> page = neoService.findSsidPage(new Page<NeoSsid>(request, response), neoSsidCondition); 
        model.addAttribute("page", page);
		return "modules/neo/ssidList";
		
	}

}
