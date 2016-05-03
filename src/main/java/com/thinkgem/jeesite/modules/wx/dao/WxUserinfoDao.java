/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wx.entity.WxUserinfo;

/**
 * 微信用户信息DAO接口
 * @author yubin
 * @version 2016-04-28
 */
@MyBatisDao
public interface WxUserinfoDao extends CrudDao<WxUserinfo> {
	
	public WxUserinfo getByOpenId(@Param("openid")String openid);
}