package com.qlink.modules.neo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlink.common.persistence.Page;
import com.qlink.modules.neo.condition.NeoRecordCondition;
import com.qlink.modules.neo.entity.NeoRecord;
import com.qlink.modules.neo.service.NeoService;

@Controller
@RequestMapping(value = "${adminPath}/neo/record")
public class NeoRecordController {
	
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
	@RequiresPermissions("neo:record:view")
	@RequestMapping(value = {"list", ""})
	public String list(NeoRecordCondition neoRecordCondition, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<NeoRecord> page = neoService.findRecordPage(new Page<NeoRecord>(request, response), neoRecordCondition); 
        model.addAttribute("page", page);
		return "modules/neo/recordList";
		
	}

}
