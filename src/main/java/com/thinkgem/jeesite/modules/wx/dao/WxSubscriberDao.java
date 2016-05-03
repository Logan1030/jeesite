/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wx.entity.WxSubscriber;

/**
 * 微信订阅者DAO接口
 * @author yubin
 * @version 2016-04-18
 */
@MyBatisDao
public interface WxSubscriberDao extends CrudDao<WxSubscriber> {
	/**
	 * 
	 * <p>
	 * Description:根据openId查询微信订阅者<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2016年4月18日
	 * @param openId
	 * @return
	 * WxSubscriber
	 */
	public WxSubscriber getByOpenId(@Param("openId") String openId);
}