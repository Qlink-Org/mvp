/** 
 * @Package com.qlink.modules.yb.condition 
 * @Description 
 * @author yifang.huang
 * @date 2016年10月27日 上午11:45:54 
 * @version V1.0 
 */ 
package com.qlink.modules.yb.condition;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.qlink.modules.yb.entity.Theme;

/** 
 * @Description 主题  查询条件类
 * @author yifang.huang
 * @date 2016年10月27日 上午11:45:54 
 */
public class ThemeCondition {
	
	private String eqWordStyle;						// 文字样式 eq 查询值
	
	public void build(DetachedCriteria dc) {
		
		if (StringUtils.isNotBlank(eqWordStyle)){
			dc.add(Restrictions.eq("wordStyle", eqWordStyle));
		}

		
		// 查询标记为0（正常）的数据
		dc.add(Restrictions.eq(Theme.FIELD_DEL_FLAG, Theme.DEL_FLAG_NORMAL));
		
		// 按创建时间降序排序
		dc.addOrder(Order.asc("sequence"));

	}

	/** 
	 * @return eqWordStyle
	 */
	public String getEqWordStyle() {
		return eqWordStyle;
	}

	/** 
	 * @param eqWordStyle
	 */
	public void setEqWordStyle(String eqWordStyle) {
		this.eqWordStyle = eqWordStyle;
	}

}
