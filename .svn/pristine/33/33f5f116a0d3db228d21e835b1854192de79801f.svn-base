/** 
 * @Package com.qlink.modules.sys.web 
 * @Description 
 * @author yifang.huang
 * @date 2016-3-8 下午5:14:12 
 * @version V1.0 
 */ 
package com.qlink.modules.sys.web;

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
import com.qlink.modules.sys.entity.PayConfig;
import com.qlink.modules.sys.service.PayConfigService;

/** 
 * @Description 在线支付 配置信息 对外服务包实现
 * @author yifang.huang
 * @date 2016-3-8 下午5:14:12 
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/pay")
public class PayConfigController extends BaseController {
	
	@Autowired
	private PayConfigService payConfigService;

	@ModelAttribute
	public PayConfig get(@RequestParam(required=false) String id) {
		
		if (StringUtils.isNotBlank(id))
			return payConfigService.get(id);
		
		return new PayConfig();
		
	}
	
	@RequiresPermissions("sys:payConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(PayConfig config, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<PayConfig> page = payConfigService.find(new Page<PayConfig>(request, response), config); 
        model.addAttribute("page", page);
		
		return "modules/sys/payConfigList";
		
	}
	
	@RequiresPermissions("sys:payConfig:view")
	@RequestMapping(value = "form")
	public String form(PayConfig bean, Model model) {
		
		model.addAttribute("payConfig", bean);
		
		return "modules/sys/payConfigForm";
		
	}

	@RequiresPermissions("sys:payConfig:edit")
	@RequestMapping(value = "save")//@Valid 
	public String save(PayConfig bean, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/sys/pay/?repage&payType="+bean.getPayType();
		}
		
		// 一个类型的支付方式配置只有一个记录
		PayConfig payConfig = payConfigService.findByParameter(bean.getPayType(), null);
		if (payConfig != null) {
			addMessage(model, "支付方式'" + bean.getName() +"'配置参数已存在，不能重复添加");
			return form(bean, model);
		}
		
		// 配置参数验证
		if (!beanValidator(model, bean)){
			return form(bean, model);
		}
		
		// 保存数据
		payConfigService.save(bean);
		addMessage(redirectAttributes, "保存支付方式'" + bean.getName() + "'配置信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/pay/?repage";
		
	}
	
	@RequiresPermissions("sys:payConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/sys/pay/?repage";
		}
		payConfigService.delete(id);
		addMessage(redirectAttributes, "删除支付方式配置信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/pay/?repage";
	}
	
}
