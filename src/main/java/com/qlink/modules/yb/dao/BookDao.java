/** 
 * @Package com.qlink.modules.yb.dao 
 * @Description 
 * @author yifang.huang
 * @date 2016年10月27日 下午2:23:47 
 * @version V1.0 
 */ 
package com.qlink.modules.yb.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.qlink.common.persistence.BaseDao;
import com.qlink.common.persistence.Page;
import com.qlink.modules.yb.condition.BookCondition;
import com.qlink.modules.yb.entity.Book;

/** 
 * @Description 书 DAO接口
 * @author yifang.huang
 * @date 2016年10月27日 下午2:23:47 
 */
@Repository
public class BookDao extends BaseDao<Book> {

	/**
	 * 
	 * @Description 根据查询参数取列表数据
	 * @param condition
	 * @return List<Book>  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午2:26:28
	 */
	public List<Book> findList(BookCondition condition) {
		
		DetachedCriteria dc = createDetachedCriteria();
		
		// build 查询条件
		condition.build(dc);
		
		return find(dc);
	}
	
	/**
	 * 
	 * @Description 根据查询参数取分页数据
	 * @param page
	 * @param condition
	 * @return Page<Book>  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午2:26:17
	 */
	public Page<Book> findPage(Page<Book> page, BookCondition condition) {
		
		DetachedCriteria dc = createDetachedCriteria();
		
		// build 查询条件
		condition.build(dc);
		
		return find(page, dc);
	}
	
}
