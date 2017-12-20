/** 
 * @Package com.qlink.modules.sys.service 
 * @Description 
 * @author yifang.huang
 * @date 2016-3-8 下午4:59:35 
 * @version V1.0 
 */ 
package com.qlink.modules.sys.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlink.common.persistence.Page;
import com.qlink.common.service.BaseService;
import com.qlink.modules.sys.dao.PayConfigDao;
import com.qlink.modules.sys.entity.PayConfig;

/** 
 * @Description 在线支付 配置信息 业务处理
 * @author yifang.huang
 * @date 2016-3-8 下午4:59:35 
 */
@Service
@Transactional(readOnly = true)
public class PayConfigService extends BaseService {
	
	@Autowired
	private PayConfigDao payConfigDao;
	
	public PayConfig get(String id) {
		PayConfig oldBean = payConfigDao.get(id);
		if (oldBean != null) {
			PayConfig newBean = new PayConfig();
			BeanUtils.copyProperties(oldBean, newBean);
			// 清除指定对象缓存
			payConfigDao.getSession().evict(oldBean);
			return newBean;
		}
		return null;
	}
	
	public Page<PayConfig> find(Page<PayConfig> page, PayConfig bean) {
		DetachedCriteria dc = payConfigDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(bean.getPayType())){
			dc.add(Restrictions.eq("payType", bean.getPayType()));
		}
		dc.add(Restrictions.eq(PayConfig.FIELD_DEL_FLAG, PayConfig.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("createDate"));
		return payConfigDao.find(page, dc);
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void save(PayConfig bean) {
		payConfigDao.save(bean);
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(String id) {
		payConfigDao.deleteById(id);
	}
	
	/**
	 * 
	 * @Description 根据查询参数取记录
	 * @param type 支付类型
	 * @param isEnabled 是否启用（1启用，0禁用）
	 * @return PayConfig  
	 * @author yifang.huang
	 * @date 2016-3-10 上午10:52:27
	 */
	public PayConfig findByParameter(String type, String isEnabled) {
		return payConfigDao.findByParameter(type, isEnabled);
	}

}
