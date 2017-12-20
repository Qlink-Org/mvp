/** 
 * @Package com.qlink.modules.yb.entity 
 * @Description 
 * @author yifang.huang
 * @date 2016年10月28日 下午3:06:26 
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
 * @Description 主题 实体类
 * @author yifang.huang
 * @date 2016年10月28日 下午3:06:26 
 */
@Entity
@Table(name = "yb_theme")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Theme extends IdEntity<Theme> {

	private static final long serialVersionUID = 3882870211025180540L;

	private String imgId;							// 图片Id（有文字）
	
	private String wordStyle;						// 文字样式
	
	private Integer sequence;						// 排序

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
