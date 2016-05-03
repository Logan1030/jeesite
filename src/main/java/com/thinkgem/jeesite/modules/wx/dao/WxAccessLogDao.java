/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wx.entity.WxAccessLog;
/**
 * 
 * <p>
 * Description:微信访问日志DAO接口<br />
 * </p>
 * @title WxAccessLogDao.java
 * @package com.thinkgem.jeesite.modules.wx.dao 
 * @author yubin
 * @version 0.1 2016年5月3日
 */
@MyBatisDao
public interface WxAccessLogDao extends CrudDao<WxAccessLog> {
	
}