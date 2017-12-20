package com.qlink.modules.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qlink.common.persistence.BaseDao;
import com.qlink.common.persistence.Parameter;
import com.qlink.modules.sys.entity.Dict;
import com.qlink.modules.sys.entity.YYKeyStore;


/**
 * @author liaowu
 *
 */
@Repository
public class YYKeyStoreDao extends BaseDao<YYKeyStore> {

	public List<YYKeyStore> findAllList(){
		return find("from YYKeyStore where delFlag=:p1 order by createDate desc", new Parameter(Dict.DEL_FLAG_NORMAL));
	}

}
