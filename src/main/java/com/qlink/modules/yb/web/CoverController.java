/** 
 * @Package com.qlink.modules.yb.web 
 * @Description 
 * @author yifang.huang
 * @date 2016年10月27日 下午2:42:30 
 * @version V1.0 
 */ 
package com.qlink.modules.yb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qlink.common.config.Global;
import com.qlink.common.persistence.Page;
import com.qlink.common.web.BaseController;
import com.qlink.modules.yb.condition.CoverCondition;
import com.qlink.modules.yb.entity.Cover;
import com.qlink.modules.yb.service.CoverService;

/** 
 * @Description 封面 后台控制类
 * @author yifang.huang
 * @date 2016年10月27日 下午2:42:30 
 */
@Controller
@RequestMapping(value = "${adminPath}/yb/cover")
public class CoverController extends BaseController {
	
	@Autowired
	private CoverService coverService;
	
	/**
	 * 
	 * @Description 初始化实体类
	 * @param id
	 * @return Cover  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午2:45:43
	 */
	@ModelAttribute
	public Cover get(@RequestParam(required=false) String id) {
		
		if (StringUtils.isNotBlank(id))
			return coverService.get(id);
		
		return new Cover();
		
	}
	
	/**
	 * 
	 * @Description 分页查询
	 * @param condition	查询条件
	 * @param request
	 * @param response
	 * @param model
	 * @return String  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午2:45:18
	 */
	@RequiresPermissions("yb:cover:view")
	@RequestMapping(value = {"list", ""})
	public String list(CoverCondition coverCondition, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<Cover> page = coverService.findPage(new Page<Cover>(request, response), coverCondition); 
        model.addAttribute("page", page);
		
		return "modules/yb/coverList";
		
	}

	/**
	 * 修改页
	 * @Description 
	 * @param cover
	 * @param model
	 * @return 
	 * @return String  
	 * @author yuxiaoyu
	 * @date 2016年10月31日 下午5:50:04
	 */
	@RequiresPermissions("yb:cover:view")
	@RequestMapping(value = "form")
	public String form(Cover cover, Model model) {
		model.addAttribute("cover", cover);
		return "modules/yb/coverForm";
	}

	/**
	 * 保存
	 * @Description 
	 * @param cover
	 * @param model
	 * @param redirectAttributes
	 * @return 
	 * @return String  
	 * @author yuxiaoyu
	 * @date 2016年10月31日 下午5:45:59
	 */
	@RequiresPermissions("yb:cover:edit")
	@RequestMapping(value = "save")
	public String save(Cover cover, Model model, RedirectAttributes redirectAttributes) {
		coverService.save(cover);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/yb/cover/?repage";
	}
	
	/**
	 * 删除
	 * @Description 
	 * @param id
	 * @param redirectAttributes
	 * @return 
	 * @return String  
	 * @author yuxiaoyu
	 * @date 2016年10月31日 下午5:57:16
	 */
	@RequiresPermissions("yb:cover:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		coverService.delete(id);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/yb/cover/?repage";
	}
}
