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
import com.qlink.modules.yb.condition.CoverCondition;
import com.qlink.modules.yb.dao.CoverDao;
import com.qlink.modules.yb.entity.Cover;

/** 
 * @Description 封面 业务处理
 * @author yifang.huang
 * @date 2016年10月27日 下午2:27:52 
 */
@Service
@Transactional(readOnly = true)
public class CoverService extends BaseService {
	
	@Autowired
	private CoverDao coverDao;
	
	/**
	 * 
	 * @Description 根据ID取数据
	 * @param id
	 * @return Cover  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午2:32:52
	 */
	public Cover get(String id) {
		Cover oldBean = coverDao.get(id);
		if (oldBean != null) {
			Cover newBean = new Cover();
			BeanUtils.copyProperties(oldBean, newBean);
			// 清除指定对象缓存
			coverDao.getSession().evict(oldBean);
			return newBean;
		}
		return null;
	}
	
	/**
	 * 
	 * @Description 根据查询参数取列表数据
	 * @param condition
	 * @return List<Cover>  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午2:35:00
	 */
	public List<Cover> findList(CoverCondition condition) {
		
		return coverDao.findList(condition);
		
	}
	
	/**
	 * 
	 * @Description 根据查询参数取分页数据
	 * @param page
	 * @param condition
	 * @return Page<Cover>  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午2:34:51
	 */
	public Page<Cover> findPage(Page<Cover> page, CoverCondition condition) {
		
		return coverDao.findPage(page, condition);
		
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
	public void save(Cover bean) {
		coverDao.save(bean);
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
		coverDao.deleteById(id);
	}

	/**
	 * 
	 * @Description list对象转listMap
	 * @param coverList
	 * @return List<Map<String,Object>>  
	 * @author yifang.huang
	 * @date 2016年10月27日 下午3:27:43
	 */
	public List<Map<String, Object>> listBeanToListMap(List<Cover> coverList){
		
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		
		try {
			for (Cover bean : coverList) {
				 map = new HashMap<String, Object>();
				 
				 map.put("imgId", bean.getImgId());
				 map.put("wordStyle", bean.getWordStyle());
				 
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
