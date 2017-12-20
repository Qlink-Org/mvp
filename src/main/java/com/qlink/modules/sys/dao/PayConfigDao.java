/** 
 * @Package com.qlink.modules.sys.dao 
 * @Description 
 * @author yifang.huang
 * @date 2016-3-8 下午4:38:41 
 * @version V1.0 
 */ 
package com.qlink.modules.sys.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.qlink.common.persistence.BaseDao;
import com.qlink.common.persistence.Parameter;
import com.qlink.modules.sys.entity.PayConfig;

/** 
 * @Description 在线支付 配置信息 DAO接口
 * @author yifang.huang
 * @date 2016-3-8 下午4:38:41 
 */
@Repository
public class PayConfigDao extends BaseDao<PayConfig> {

	/**
	 * 
	 * @Description 根据查询参数取记录
	 * @param type 支付类型
	 * @param isEnabled 是否启用（1启用，0禁用）
	 * @return PayConfig  
	 * @author yifang.huang
	 * @date 2016-3-10 上午10:48:35
	 */
	public PayConfig findByParameter(String type, String isEnabled) {
		
		Parameter parameter = new Parameter();		// 查询参数
		
		StringBuffer sb = new StringBuffer("from PayConfig where delFlag = :p1");
		parameter.put("p1", PayConfig.DEL_FLAG_NORMAL);
		
		if (StringUtils.isNotBlank(type)) {
			sb.append("  and payType = :p2");
			parameter.put("p2", type);
		}
		
		if (StringUtils.isNotBlank(isEnabled)) {
			sb.append(" and isEnabled = :p3");
			parameter.put("p3", isEnabled);
			
		}
		
		return getByHql(sb.toString(), parameter);
	}
	
}
