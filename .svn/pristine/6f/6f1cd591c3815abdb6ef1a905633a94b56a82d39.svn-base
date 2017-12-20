package com.qlink.modules.neo.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.qlink.common.persistence.BaseDao;
import com.qlink.common.persistence.Page;
import com.qlink.modules.neo.condition.NeoRecordCondition;
import com.qlink.modules.neo.entity.NeoRecord;

@Repository
public class NeoRecordDao extends BaseDao<NeoRecord>{
	
	/** 
	 * 根据rid获取对象
	 * @Description 
	 * @param sid
	 * @return 
	 * @return NeoSsid  
	 * @author shuxin
	 * @date 2017年12月9日 下午9:43:18 
	 */ 
	public NeoRecord getNeoRecordByRid(String rid) {
		String hql = "From NeoRecord n where n.recordKey = '" + rid+"'";
		List<NeoRecord> list = find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/** 
	 * 按条件分页查询列表
	 * @Description 
	 * @param page
	 * @param condition
	 * @return 
	 * @return Page<NeoRecord>  
	 * @author shuxin
	 * @date 2017年12月14日 下午1:54:42 
	 */ 
	public Page<NeoRecord> findPage(Page<NeoRecord> page, NeoRecordCondition condition) {
		DetachedCriteria dc = createDetachedCriteria();
		// build 查询条件
		condition.build(dc);
		return find(page, dc);
	}
}
