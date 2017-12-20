/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.qlink.modules.sys.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlink.common.persistence.Page;
import com.qlink.common.service.BaseService;
import com.qlink.common.utils.DateUtils;
import com.qlink.common.utils.StringUtils;
import com.qlink.modules.sys.dao.LogDao;
import com.qlink.modules.sys.entity.Log;
import com.qlink.modules.sys.entity.User;
import com.qlink.modules.sys.utils.UserUtils;

/**
 * 日志Service
 * @author admin
 * @version 2013-6-2
 */
@Service
@Transactional(readOnly = true)
public class LogService extends BaseService {

	@Autowired
	private LogDao logDao;
	
	public Log get(String id) {
		return logDao.get(id);
	}
	
	public Page<Log> find(Page<Log> page, Map<String, Object> paramMap) {
		DetachedCriteria dc = logDao.createDetachedCriteria();

		Long createById = StringUtils.toLong(paramMap.get("createById"));
		if (createById > 0){
			dc.add(Restrictions.eq("createBy.id", createById));
		}
		
		String requestUri = ObjectUtils.toString(paramMap.get("requestUri"));
		if (StringUtils.isNotBlank(requestUri)){
			dc.add(Restrictions.like("requestUri", "%"+requestUri+"%"));
		}

		String exception = ObjectUtils.toString(paramMap.get("type"));
		if (StringUtils.isNotBlank(exception)){
			dc.add(Restrictions.eq("type", exception));
		}
		
		Date beginDate = DateUtils.parseDate(paramMap.get("beginDate"));
		if (beginDate == null){
			beginDate = DateUtils.setDays(new Date(), 1);
			paramMap.put("beginDate", DateUtils.formatDate(beginDate, "yyyy-MM-dd"));
		}
		Date endDate = DateUtils.parseDate(paramMap.get("endDate"));
		if (endDate == null){
			endDate = DateUtils.addDays(DateUtils.addMonths(beginDate, 1), -1);
			paramMap.put("endDate", DateUtils.formatDate(endDate, "yyyy-MM-dd"));
		}
		dc.add(Restrictions.between("createDate", beginDate, endDate));
		
		dc.addOrder(Order.desc("id"));
		return logDao.find(page, dc);
	}
	
	
	/**
	 * 保存操作日志
	 * @athor shuxin
	 * @date 2016年7月6日上午11:17:39
	 * @param request 请求参数
	 * @param operate 操作模块（例如：[模块名称]-添加、[模块名称]-修改[{}]、[模块名称]-删除[{}]）
	 * @param param 参数
	 * void 
	 */
	public  void saveOperateLog(HttpServletRequest request, String operate, String... param){
		Log  log = new Log();
		log.setCreateDate(new Date());
		String method = request.getMethod();
		log.setMethod(method);
		String requestUri = request.getRequestURI();
		log.setRequestUri(requestUri);
		String remoteAddr = request.getRemoteAddr();
		log.setRemoteAddr(remoteAddr);
		log.setType("3");
		if(param == null || param.length == 0){
			log.setException(operate);
		} else {
			StringBuffer buff = new StringBuffer();
			String[] tempStr = operate.split("[{}]");
			for (int i = 0; i < tempStr.length; i++) {
				if(!"".equals(tempStr[i])){
					if(param.length == (i/2)){
						buff.append(tempStr[i]);
					} else {
						buff.append(tempStr[i]).append(param[i/2]);
					}
				}
			}
			log.setException(buff.toString());
		}
		logDao.save(log);
	}
	
}
