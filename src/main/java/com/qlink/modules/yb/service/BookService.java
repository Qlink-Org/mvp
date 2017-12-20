/** 
 * @Package com.qlink.modules.yb.service 
 * @Description 
 * @author yifang.huang
 * @date 2016年10月27日 下午2:27:52 
 * @version V1.0 
 */ 
package com.qlink.modules.yb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlink.common.persistence.Page;
import com.qlink.common.service.BaseService;
import com.qlink.modules.yb.condition.BookCondition;
import com.qlink.modules.yb.dao.BookDao;
import com.qlink.modules.yb.entity.Book;

/** 
 * @Description 封面 业务处理
 * @author yifang.huang
 * @date 2016年10月27日 下午2:27:52 
 */
@Service
@Transactional(readOnly = true)
public class BookService extends BaseService {
	
	@Autowired
	private BookDao bookDao;
	
	/**
	 * 
	 * @Description 根据ID取数据
	 * @param id
	 * @return Book  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午2:32:52
	 */
	public Book get(String id) {
		Book oldBean = bookDao.get(id);
		if (oldBean != null) {
			Book newBean = new Book();
			BeanUtils.copyProperties(oldBean, newBean);
			// 清除指定对象缓存
			bookDao.getSession().evict(oldBean);
			return newBean;
		}
		return null;
	}
	
	/**
	 * 
	 * @Description 根据查询参数取列表数据
	 * @param condition
	 * @return List<Book>  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午2:35:00
	 */
	public List<Book> findList(BookCondition condition) {
		
		return bookDao.findList(condition);
		
	}
	
	/**
	 * 
	 * @Description 根据查询参数取分页数据
	 * @param page
	 * @param condition
	 * @return Page<Book>  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午2:34:51
	 */
	public Page<Book> findPage(Page<Book> page, BookCondition condition) {
		
		return bookDao.findPage(page, condition);
		
	}

	/**
	 * 
	 * @Description 保存数据
	 * @param bean 
	 * @return void  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午2:34:42
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void save(Book bean) {
		bookDao.save(bean);
	}
	
	/**
	 * 
	 * @Description 根据ID删除数据
	 * @param id 
	 * @return void  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午2:34:33
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(String id) {
		bookDao.deleteById(id);
	}

	/**
	 * 
	 * @Description list对象转listMap
	 * @param BookList
	 * @return List<Map<String,Object>>  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午3:27:43
	 */
	public List<Map<String, Object>> listBeanToListMap(List<Book> bookList){
		
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		
		try {
			for (Book bean : bookList) {
				 map = new HashMap<String, Object>();
				 
				 map.put("imgId", bean.getCover().getImgId());
				 map.put("wordStyle", bean.getCover().getWordStyle());
				 map.put("name", bean.getName());
				 map.put("author", bean.getAuthor());
				 map.put("timeYm", bean.getTimeYm());
				 map.put("timeYmd", bean.getTimeYmd());
				 
				 items.add(map);
				 map = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("封面列表对象转列表Map出错！");
		}
		
		return items;
	}

}
