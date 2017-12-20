/** 
 * @Package com.qlink.modules.ico.entity 
 * @Description 
 * @author yifang.huang
 * @date 2017年7月4日 下午4:43:44 
 * @version V1.0 
 */ 
package com.qlink.modules.neo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.junit.Ignore;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qlink.common.persistence.BaseEntity;
import com.qlink.common.utils.IdGen;

/** 
 * neo_ssid
 * @Description 
 * @author shuxin
 * @date 2017年12月9日 下午4:23:14 
 */ 
@Entity
@Table(name = "neo_ssid")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NeoSsid extends BaseEntity<NeoSsid> {
	/** 
	 * serialVersionUID long
	 */ 
	private static final long serialVersionUID = 3264682877100351759L;

	private String id;						// 唯一值
	
	private String ssid;	
	
	private String mac;					
	 	
	private String p2pId;
	
	private Date createDate;				// 创建时间
	
	public NeoSsid(){
		super();
		id = IdGen.uuid();
		createDate = new Date();
	}
	
	@JsonIgnore
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getP2pId() {
		return p2pId;
	}

	public void setP2pId(String p2pId) {
		this.p2pId = p2pId;
	}

	@JsonIgnore
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public static final Integer SUCESS = 1;  //调用成功
	public static final Integer FAILED = 0;  //调用失败
}
