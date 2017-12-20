/** 
 * @Package com.qlink.modules.ico.entity 
 * @Description 
 * @author yifang.huang
 * @date 2017年7月4日 下午4:43:44 
 * @version V1.0 
 */ 
package com.qlink.modules.neo.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.qlink.common.persistence.BaseEntity;
import com.qlink.common.utils.IdGen;
@Entity
@Table(name = "neo_record")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NeoRecord extends BaseEntity<NeoRecord> {
	/** 
	 * serialVersionUID long
	 */ 
	private static final long serialVersionUID = 6548271956737303453L;

	private String id;						// 唯一值
	
	private String recordKey;
	
	private String addressFrom;
	
	private String formP2pId;					
	
	private String addressTo;				
	
	private String toP2pId;					
	
	private BigDecimal qlc;	
	
	private Date createDate;				// 创建时间
	
	public NeoRecord() {
		super();
		id = IdGen.uuid();
		createDate = new Date();
	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getRecordKey() {
		return recordKey;
	}

	public void setRecordKey(String recordKey) {
		this.recordKey = recordKey;
	}

	public String getAddressFrom() {
		return addressFrom;
	}

	public void setAddressFrom(String addressFrom) {
		this.addressFrom = addressFrom;
	}

	@Column(name = "form_p2p_id")
	public String getFormP2pId() {
		return formP2pId;
	}

	public void setFormP2pId(String formP2pId) {
		this.formP2pId = formP2pId;
	}

	public String getAddressTo() {
		return addressTo;
	}

	public void setAddressTo(String addressTo) {
		this.addressTo = addressTo;
	}

	@Column(name = "to_p2p_id")
	public String getToP2pId() {
		return toP2pId;
	}

	public void setToP2pId(String toP2pId) {
		this.toP2pId = toP2pId;
	}

	public BigDecimal getQlc() {
		return qlc;
	}

	public void setQlc(BigDecimal qlc) {
		this.qlc = qlc;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public static final Integer SUCESS = 1;  //调用成功
	public static final Integer FAILED = 0;  //调用失败
}
