/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.qlink.modules.sys.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlink.common.service.BaseService;
import com.qlink.common.utils.StringUtils;
import com.qlink.modules.sys.dao.AreaDao;
import com.qlink.modules.sys.entity.Area;
import com.qlink.modules.sys.utils.ApiUtils;
import com.qlink.modules.sys.utils.UserUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 区域Service
 * @author admin
 * @version 2013-5-29
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends BaseService {

	@Autowired
	private AreaDao areaDao;
	
	public Area get(String id) {
		return areaDao.get(id);
	}
	
	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}
	public List<Area> find(){
		return areaDao.find();
	}
	@Transactional(readOnly = false)
	public List<Area> findListBycode(String  code){
		return areaDao.findListBycode(code);
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		area.setParent(this.get(area.getParent().getId()));
		String oldParentIds = area.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
		area.setParentIds(area.getParent().getParentIds()+area.getParent().getId()+",");
		areaDao.clear();
		areaDao.save(area);
		// 更新子节点 parentIds
		List<Area> list = areaDao.findByParentIdsLike("%,"+area.getId()+",%");
		for (Area e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, area.getParentIds()));
		}
		areaDao.save(list);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		areaDao.deleteById(id, "%,"+id+",%");
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	/**
	 * 根据国家编号查询所属的所有
	 */
	public void findAndBuildJson(JSONObject requestObj, JSONObject responseObj) {
			
		DetachedCriteria detachedCriteria = areaDao.createDetachedCriteria();
		Object paramObj = requestObj.get("code");
		if (!StringUtils.isEmpty(paramObj)) {
			if (!(paramObj instanceof String)) {
				ApiUtils.invalidParamResponse("|[code]格式错误!", responseObj);
				return;
			}
			detachedCriteria.add(Restrictions.eq("code", paramObj.toString()));
		}
		List<Area> arealist=areaDao.findListBycode((String) paramObj);
		buildResponseObj(responseObj, arealist);

		
		
	}
	/**
	 * 生成接口结果
	 * @Description wangsai
	 * @author wangsai
	 * @date 2016年6月30日 上午11:37:12
	 */
	private void buildResponseObj(JSONObject responseObj, List<Area> AreaList) {
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj;
		for (Area record : AreaList) {
			jsonObj = new JSONObject();
			jsonObj.put("id", record.getId());
			jsonObj.put("parentId", record.getParent().getId());
			jsonObj.put("parentIds", record.getParentIds());
			jsonObj.put("name", record.getName());
			jsonObj.put("code", record.getCode());
			jsonObj.put("type", record.getType());
			ApiUtils.buildCommonAttributes(jsonObj, record);
			jsonArr.add(jsonObj);
		}
		ApiUtils.putData(responseObj, jsonArr);
	}
}
