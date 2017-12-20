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

import com.qlink.modules.yb.entity.Book;

/** 
 * @Description 书 查询条件类
 * @author yifang.huang
 * @date 2016年10月27日 上午11:45:54 
 */
public class BookCondition {
	
	private String eqCoverId;						// 封面ID eq 查询值
	
	public void build(DetachedCriteria dc) {
		
		if (StringUtils.isNotBlank(eqCoverId)){
			dc.add(Restrictions.eq("cover.id", eqCoverId));
		}

		// 查询标记为0（正常）的数据
		dc.add(Restrictions.eq(Book.FIELD_DEL_FLAG, Book.DEL_FLAG_NORMAL));
		
		// 按创建时间降序排序
		dc.addOrder(Order.asc("createDate"));

	}

	/** 
	 * @return eqCoverId
	 */
	public String getEqCoverId() {
		return eqCoverId;
	}

	/** 
	 * @param eqCoverId
	 */
	public void setEqCoverId(String eqCoverId) {
		this.eqCoverId = eqCoverId;
	}

}
