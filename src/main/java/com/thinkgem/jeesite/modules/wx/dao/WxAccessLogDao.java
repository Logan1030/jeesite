/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wx.entity.WxAccessLog;

/**
 * 微信访问日志DAO接口
 * @author yubin
 * @version 2016-04-16
 */
@MyBatisDao
public interface WxAccessLogDao extends CrudDao<WxAccessLog> {
	
}