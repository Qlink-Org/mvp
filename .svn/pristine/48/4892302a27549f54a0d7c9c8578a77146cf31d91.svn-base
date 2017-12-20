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
import com.qlink.modules.yb.condition.ThemeCondition;
import com.qlink.modules.yb.entity.Theme;
import com.qlink.modules.yb.service.ThemeService;

/** 
 * @Description 主题 后台控制类
 * @author yifang.huang
 * @date 2016年10月27日 下午2:42:30 
 */
@Controller
@RequestMapping(value = "${adminPath}/yb/theme")
public class ThemeController extends BaseController {
	
	@Autowired
	private ThemeService themeService;
	
	/**
	 * 
	 * @Description 初始化实体类
	 * @param id
	 * @return Theme  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午2:45:43
	 */
	@ModelAttribute
	public Theme get(@RequestParam(required=false) String id) {
		
		if (StringUtils.isNotBlank(id))
			return themeService.get(id);
		
		return new Theme();
		
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
	@RequiresPermissions("yb:theme:view")
	@RequestMapping(value = {"list", ""})
	public String list(ThemeCondition themeCondition, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<Theme> page = themeService.findPage(new Page<Theme>(request, response), themeCondition); 
        model.addAttribute("page", page);
		
		return "modules/yb/themeList";
		
	}

	/**
	 * 修改页
	 * @Description 
	 * @param theme
	 * @param model
	 * @return 
	 * @return String  
	 * @author yuxiaoyu
	 * @date 2016年10月31日 下午5:50:47
	 */
	@RequiresPermissions("yb:theme:view")
	@RequestMapping(value = "form")
	public String form(Theme theme, Model model) {
		model.addAttribute("theme", theme);
		return "modules/yb/themeForm";
	}

	/**
	 * 保存
	 * @Description 
	 * @param theme
	 * @param model
	 * @param redirectAttributes
	 * @return 
	 * @return String  
	 * @author yuxiaoyu
	 * @date 2016年10月31日 下午5:50:35
	 */
	@RequiresPermissions("yb:theme:edit")
	@RequestMapping(value = "save")
	public String save(Theme theme, Model model, RedirectAttributes redirectAttributes) {
		themeService.save(theme);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/yb/theme/?repage";
	}
	
	/**
	 * 删除
	 * @Description 
	 * @param id
	 * @param redirectAttributes
	 * @return 
	 * @return String  
	 * @author yuxiaoyu
	 * @date 2016年10月31日 下午5:50:25
	 */
	@RequiresPermissions("yb:theme:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		themeService.delete(id);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/yb/theme/?repage";
	}
}
