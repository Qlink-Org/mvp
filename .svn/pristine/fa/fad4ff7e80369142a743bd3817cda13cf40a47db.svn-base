/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.qlink.modules.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qlink.common.persistence.BaseDao;
import com.qlink.common.persistence.Parameter;
import com.qlink.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author admin
 * @version 2013-8-23
 */
@Repository
public class AreaDao extends BaseDao<Area> {
	
	public List<Area> findByParentIdsLike(String parentIds){
		return find("from Area where parentIds like :p1", new Parameter(parentIds));
	}

	public List<Area> findAllList(){
		return find("from Area where delFlag=:p1 order by code", new Parameter(Area.DEL_FLAG_NORMAL));
	}
	
	public List<Area> findAllChild(Long parentId, String likeParentIds){
		return find("from Area where delFlag=:p1 and (id=:p2 or parent.id=:p2 or parentIds like :p3) order by code", 
				new Parameter(Area.DEL_FLAG_NORMAL, parentId, likeParentIds));
	}
	public List<Area> findListBycode(String code){
	
		return find("from Area where parent = (select id from Area WHERE code =:p1 )and delFlag=0", new Parameter(code));
		
	}
	public List<Area> find(){
		
		return find("from Area where type = 1 and delFlag=0");
		
	}
	
	
	
	
	
}
