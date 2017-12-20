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

import com.qlink.modules.yb.entity.Cover;

/** 
 * @Description 封面 查询条件类
 * @author yifang.huang
 * @date 2016年10月27日 上午11:45:54 
 */
public class CoverCondition {
	
	private String eqType;							// 类型 eq 查询值
	
	private String eqWordStyle;						// 文字样式 eq 查询值
	
	public void build(DetachedCriteria dc) {
		
		if (null != eqType){
			dc.add(Restrictions.eq("type", eqType));
		}
		
		if (StringUtils.isNotBlank(eqWordStyle)){
			dc.add(Restrictions.eq("wordStyle", eqWordStyle));
		}

		// 查询标记为0（正常）的数据
		dc.add(Restrictions.eq(Cover.FIELD_DEL_FLAG, Cover.DEL_FLAG_NORMAL));
		
		// 按创建时间降序排序
		dc.addOrder(Order.asc("sequence"));

	}

	/** 
	 * @return eqType
	 */
	public String getEqType() {
		return eqType;
	}

	/** 
	 * @param eqType
	 */
	public void setEqType(String eqType) {
		this.eqType = eqType;
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
