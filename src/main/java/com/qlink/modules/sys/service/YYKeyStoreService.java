package com.qlink.modules.sys.service;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlink.common.persistence.Page;
import com.qlink.common.service.BaseService;
import com.qlink.common.utils.CacheUtils;
import com.qlink.common.utils.StringUtils;
import com.qlink.modules.sys.dao.YYKeyStoreDao;
import com.qlink.modules.sys.entity.YYKeyStore;
import com.qlink.modules.sys.utils.YYKeyStoreUtils;


/**
 * @author liaowu
 *
 */
@Service
@Transactional
public class YYKeyStoreService extends BaseService {
	@Autowired
	private YYKeyStoreDao keyStoreDao;
	
	public YYKeyStore get(String keyId) {
		return keyStoreDao.get(keyId);
	}

	public Page<YYKeyStore> find(Page<YYKeyStore> page, YYKeyStore keyStore) {
		DetachedCriteria dc = keyStoreDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(keyStore.getKeyDesc())){
			dc.add(Restrictions.like("keyDesc", "%"+keyStore.getKeyDesc()+"%"));
		}
		if (StringUtils.isNotEmpty(keyStore.getKeyType())){
			dc.add(Restrictions.eq("keyType", keyStore.getKeyType()));
		}
		if (StringUtils.isNotEmpty(keyStore.getSourceType())){
			dc.add(Restrictions.eq("sourceType", keyStore.getSourceType()));
		}
		dc.add(Restrictions.eq(YYKeyStore.FIELD_DEL_FLAG, YYKeyStore.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("createDate"));
		return keyStoreDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(YYKeyStore keyStore) {
		keyStoreDao.save(keyStore);
		CacheUtils.remove(YYKeyStoreUtils.CACHE_KEY_STORE_LIST);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		keyStoreDao.deleteById(id);
		CacheUtils.remove(YYKeyStoreUtils.CACHE_KEY_STORE_LIST);
	}
	/**
	 *
	 * @param sourceType
	 * @return
	 */
	@Transactional(readOnly = true)
	public YYKeyStore findKeyBySourceType(String sourceType){
		YYKeyStore keyStore = null;
		Criteria criteria = keyStoreDao.getSession().createCriteria(YYKeyStore.class);
		@SuppressWarnings("unchecked")
		List<YYKeyStore> keyStoreList = criteria
				.add( Restrictions.eq("sourceType", sourceType) )
				.add( Restrictions.eq(YYKeyStore.FIELD_DEL_FLAG, YYKeyStore.DEL_FLAG_NORMAL))
				.list();
		if(!keyStoreList.isEmpty())
			keyStore = keyStoreList.get(0);
		return keyStore;
	}

}
