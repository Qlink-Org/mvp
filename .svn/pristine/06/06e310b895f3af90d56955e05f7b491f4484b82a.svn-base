/** 
 * @Package com.qlink.modules.yb.web.front 
 * @Description 
 * @author yifang.huang
 * @date 2016年10月27日 下午2:50:31 
 * @version V1.0 
 */ 
package com.qlink.modules.yb.web.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qlink.common.persistence.Page;
import com.qlink.common.web.BaseController;
import com.qlink.modules.yb.condition.CoverCondition;
import com.qlink.modules.yb.entity.Cover;
import com.qlink.modules.yb.service.CoverService;

/** 
 * @Description 封面 前台控制类
 * @author yifang.huang
 * @date 2016年10月27日 下午2:50:31 
 */
@Controller
@RequestMapping(value = "${frontPath}/cover")
public class CoverFrontController extends BaseController {

	@Autowired
	private CoverService coverService;

	/**
	 * 
	 * @Description 封面列表页
	 * @param paramMap
	 * @param request
	 * @param response
	 * @param model
	 * @return String  
	 * @author yifang.huang
	 * @date 2016年10月28日 下午3:17:49
	 */
	@RequestMapping(value = "coverList")
	public String coverList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		// 参数
		String type = ObjectUtils.toString(paramMap.get("type"));
		if (StringUtils.isNotBlank(type))
	        model.addAttribute("type", type);
		
		return "/WEB-INF/app/coverList.jsp";
        
	}
	
	/**
	 * 
	 * @Description 异步获取封面列表
	 * @param reqobj
	 * @param request
	 * @return Map<String,Object>  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午3:04:17
	 */
    @RequestMapping(value = "/ajaxList.json",produces="application/json;charset=UTF-8") 
	@ResponseBody  
	public Map<String, Object> ajaxList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			// 参数
			String type = ObjectUtils.toString(paramMap.get("type"));

			// 查询条件
			CoverCondition condition = new CoverCondition();
			if (StringUtils.isNotBlank(type)) {
				condition.setEqType(type);
			}
			
			// 取数据
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
	        Page<Cover> page  = coverService.findPage(new Page<Cover>(request, response), condition); 
			List<Cover> coverList = page.getList();
			if (coverList!=null && coverList.size()>0) 
				listMap = coverService.listBeanToListMap(coverList);
			
			result.put("result", listMap);
			result.put("status", "success");
			result.put("message", "异步获取封面列表成功！");
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			result.put("status", "error");
			result.put("message", "异步获取封面列表失败！");
			return result;
		}
		
	}
}
