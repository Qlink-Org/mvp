/** 
 * @Package com.qlink.modules.yb.entity 
 * @Description 
 * @author yifang.huang
 * @date 2016年10月27日 上午11:30:27 
 * @version V1.0 
 */ 
package com.qlink.modules.yb.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.qlink.common.persistence.IdEntity;

/** 
 * @Description 封面 实体类
 * @author yifang.huang
 * @date 2016年10月27日 上午11:30:27 
 */
@Entity
@Table(name = "yb_cover")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Cover extends IdEntity<Cover> {

	private static final long serialVersionUID = 6025724232083844506L;
	
	private String type;								// 类型
	
	private String wordStyle;						// 文字样式
	
	private String imgId;							// 图片Id
	
	private Integer sequence;						// 排序

	/** 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/** 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/** 
	 * @return wordStyle
	 */
	public String getWordStyle() {
		return wordStyle;
	}

	/** 
	 * @param wordStyle
	 */
	public void setWordStyle(String wordStyle) {
		this.wordStyle = wordStyle;
	}

	/** 
	 * @return imgId
	 */
	public String getImgId() {
		return imgId;
	}

	/** 
	 * @param imgId
	 */
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	/** 
	 * @return sequence
	 */
	public Integer getSequence() {
		return sequence;
	}

	/** 
	 * @param sequence
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}
