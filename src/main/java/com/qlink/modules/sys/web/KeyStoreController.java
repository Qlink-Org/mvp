/**
 * The code generation
 */
package com.qlink.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlink.modules.sys.service.YYKeyStoreService;
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
import com.qlink.common.utils.StringUtils;
import com.qlink.modules.sys.entity.User;
import com.qlink.modules.sys.utils.UserUtils;
import com.qlink.modules.sys.entity.YYKeyStore;

/**
 * 密钥Controller
 * @author liaowu
 * @version 2016-03-04
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/keyStore")
public class KeyStoreController extends BaseController {

	@Autowired
	private YYKeyStoreService keyStoreService;
	
	@ModelAttribute
	public YYKeyStore get(@RequestParam(required=false) String keyId) {
		if (StringUtils.isNotBlank(keyId)){
			return keyStoreService.get(keyId);
		}else{
			return new YYKeyStore();
		}
	}
	
	@RequiresPermissions("sys:keyStore:view")
	@RequestMapping(value = {"list", ""})
	public String list(YYKeyStore keyStore, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			keyStore.setCreateBy(user);
		}
        Page<YYKeyStore> page = keyStoreService.find(new Page<YYKeyStore>(request, response), keyStore);
        model.addAttribute("page", page);
		return "modules/" + "sys/keyStoreList";
	}

	@RequiresPermissions("sys:keyStore:view")
	@RequestMapping(value = "form")
	public String form(YYKeyStore keyStore, Model model) {
		model.addAttribute("keyStore", keyStore);
		return "modules/" + "sys/keyStoreForm";
	}

	@RequiresPermissions("sys:keyStore:edit")
	@RequestMapping(value = "save")
	public String save(YYKeyStore keyStore, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, keyStore)){
			return form(keyStore, model);
		}
		keyStoreService.save(keyStore);
		addMessage(redirectAttributes, "保存密钥'" + keyStore.getKeyDesc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/sys/keyStore/?repage";
	}
	
	@RequiresPermissions("sys:keyStore:edit")
	@RequestMapping(value = "delete")
	public String delete(String keyId, RedirectAttributes redirectAttributes) {
		keyStoreService.delete(keyId);
		addMessage(redirectAttributes, "删除密钥成功");
		return "redirect:"+Global.getAdminPath()+"/sys/keyStore/?repage";
	}

}
