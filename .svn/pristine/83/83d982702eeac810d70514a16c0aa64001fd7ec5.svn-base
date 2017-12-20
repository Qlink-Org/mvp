package com.qlink.modules.neo.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.qlink.common.persistence.BaseDao;
import com.qlink.common.persistence.Page;
import com.qlink.modules.neo.condition.NeoSsidCondition;
import com.qlink.modules.neo.entity.NeoSsid;

@Repository
public class NeoSsidDao extends BaseDao<NeoSsid> {

	/** 
	 * 根据ssid获取对象
	 * @Description 
	 * @param sid
	 * @return 
	 * @return NeoSsid  
	 * @author shuxin
	 * @date 2017年12月9日 下午9:43:18 
	 */ 
	public NeoSsid getNeoSsidBySid(String sid) {
		String hql = "From NeoSsid n where n.ssid = '" + sid+"'";
		List<NeoSsid> list = find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/** 
	 * 按条件分页查询
	 * @Description 
	 * @param page
	 * @param condition
	 * @return 
	 * @return Page<NeoSsid>  
	 * @author shuxin
	 * @date 2017年12月14日 下午1:47:07 
	 */ 
	public Page<NeoSsid> findPage(Page<NeoSsid> page, NeoSsidCondition condition) {
		DetachedCriteria dc = createDetachedCriteria();
		// build 查询条件
		condition.build(dc);
		return find(page, dc);
	}

}
