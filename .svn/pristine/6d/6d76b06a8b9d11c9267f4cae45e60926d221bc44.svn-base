/** 
 * @Package com.qlink.modules.yb.entity 
 * @Description 
 * @author yifang.huang
 * @date 2016年10月28日 下午4:50:30 
 * @version V1.0 
 */ 
package com.qlink.modules.yb.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.qlink.common.persistence.IdEntity;

/** 
 * @Description 书 实体类
 * @author yifang.huang
 * @date 2016年10月28日 下午4:50:30 
 */
@Entity
@Table(name = "yb_book")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Book extends IdEntity<Book> {

	private static final long serialVersionUID = 6713890709681297246L;
	
	private Cover cover;							// 封面
	
	private String userId;							// 所属用户ID
	
	private String companyId;						// 所属公司ID
	
	private String officeId;						// 所属部门ID
	
	private String name;							// 书名
	
	private String author;							// 作者
	
	private String timeYm;							// 时间戳（2016.10）
	
	private String timeYmd;							// 时间戳（2016.10.10）
	
	private Integer imgTotal;						// 图片总数
	
	private Integer pageTotal;						// 页面总数
	
	private Integer viewTotal;						// 查看总数
	
	private Integer praiseTotal;					// 赞总数 
	
	private Integer commentTotal;					// 评论总数
		
	private String preface;							// 序言

	/** 
	 * @return cover
	 */
	@ManyToOne
	@JoinColumn(name="cover_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Cover getCover() {
		return cover;
	}

	/** 
	 * @param cover
	 */
	public void setCover(Cover cover) {
		this.cover = cover;
	}

	/** 
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/** 
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/** 
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/** 
	 * @param companyId
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/** 
	 * @return officeId
	 */
	public String getOfficeId() {
		return officeId;
	}

	/** 
	 * @param officeId
	 */
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	/** 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/** 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}

	/** 
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/** 
	 * @return timeYm
	 */
	public String getTimeYm() {
		return timeYm;
	}

	/** 
	 * @param timeYm
	 */
	public void setTimeYm(String timeYm) {
		this.timeYm = timeYm;
	}

	/** 
	 * @return timeYmd
	 */
	public String getTimeYmd() {
		return timeYmd;
	}

	/** 
	 * @param timeYmd
	 */
	public void setTimeYmd(String timeYmd) {
		this.timeYmd = timeYmd;
	}

	/** 
	 * @return imgTotal
	 */
	public Integer getImgTotal() {
		return imgTotal;
	}

	/** 
	 * @param imgTotal
	 */
	public void setImgTotal(Integer imgTotal) {
		this.imgTotal = imgTotal;
	}

	/** 
	 * @return pageTotal
	 */
	public Integer getPageTotal() {
		return pageTotal;
	}

	/** 
	 * @param pageTotal
	 */
	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	/** 
	 * @return viewTotal
	 */
	public Integer getViewTotal() {
		return viewTotal;
	}

	/** 
	 * @param viewTotal
	 */
	public void setViewTotal(Integer viewTotal) {
		this.viewTotal = viewTotal;
	}

	/** 
	 * @return praiseTotal
	 */
	public Integer getPraiseTotal() {
		return praiseTotal;
	}

	/** 
	 * @param praiseTotal
	 */
	public void setPraiseTotal(Integer praiseTotal) {
		this.praiseTotal = praiseTotal;
	}

	/** 
	 * @return commentTotal
	 */
	public Integer getCommentTotal() {
		return commentTotal;
	}

	/** 
	 * @param commentTotal
	 */
	public void setCommentTotal(Integer commentTotal) {
		this.commentTotal = commentTotal;
	}

	/** 
	 * @return preface
	 */
	public String getPreface() {
		return preface;
	}

	/** 
	 * @param preface
	 */
	public void setPreface(String preface) {
		this.preface = preface;
	}
	
}
