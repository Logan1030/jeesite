/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wx.entity.WxMenu;

/**
 * 微信菜单DAO接口
 * @author yubin
 * @version 2016-04-16
 */
@MyBatisDao
public interface WxMenuDao extends CrudDao<WxMenu> {
	
	public List<WxMenu>findAllMenu();
	
	public List<WxMenu>findsubMenu(@Param("id") String id);
}