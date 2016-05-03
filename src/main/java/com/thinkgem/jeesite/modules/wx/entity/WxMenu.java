/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信菜单Entity
 * @author yubin
 * @version 2016-04-16
 */
public class WxMenu extends DataEntity<WxMenu> {
	
	private static final long serialVersionUID = 1L;
	private String menuType;		// 菜单类型
	private String menuName;		// 菜单标题
	private String menuKey;		// 菜单键值
	private String menuUrl;		// 网页链接
	private String parentId;		// 父级编号
	private String isShow;		// 是否显示
	
	public WxMenu() {
		super();
	}

	public WxMenu(String id){
		super(id);
	}

	@Length(min=0, max=1, message="菜单类型长度必须介于 0 和 1 之间")
	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
	@Length(min=0, max=32, message="菜单标题长度必须介于 0 和 32 之间")
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	@Length(min=0, max=32, message="菜单键值长度必须介于 0 和 32 之间")
	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}
	
	@Length(min=0, max=255, message="网页链接长度必须介于 0 和 255 之间")
	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	
	@Length(min=0, max=64, message="父级编号长度必须介于 0 和 64 之间")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Length(min=0, max=1, message="是否显示长度必须介于 0 和 1 之间")
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
}