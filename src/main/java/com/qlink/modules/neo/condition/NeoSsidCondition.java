package com.qlink.modules.neo.condition;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.qlink.common.utils.DateUtils;

/** 
 * ssid过滤条件
 * @Description 
 * @author shuxin
 * @date 2017年12月14日 下午1:44:28 
 */ 
public class NeoSsidCondition {

	private String ssid;
	
	private String startTime;
	
	private String endTime;
	
	public void build(DetachedCriteria dc) {
		
		if (StringUtils.isNotBlank(ssid)){
			dc.add(Restrictions.like("ssid", ssid, MatchMode.ANYWHERE));
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		if (StringUtils.isNotBlank(startTime)) {
			try {
				dc.add(Restrictions.ge("createDate", DateUtils.getDateStart(df.parse(startTime))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if (StringUtils.isNotBlank(endTime)) {
			try {
				dc.add(Restrictions.le("createDate", DateUtils.getDateEnd(df.parse(endTime))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		// 按创建时间降序排序
		dc.addOrder(Order.asc("createDate"));
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
