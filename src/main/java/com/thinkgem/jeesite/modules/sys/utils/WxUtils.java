package com.thinkgem.jeesite.modules.sys.utils;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.wx.dao.WxMenuDao;
import com.thinkgem.jeesite.modules.wx.entity.WxMenu;

public class WxUtils {
	private static WxMenuDao wxMenuDao = SpringContextHolder.getBean(WxMenuDao.class);
	private static final String WX_CACHE="WX_CACHE";
	public static final String WX_CACHE_ID_ = "id_";
	public static String getWxParentName(String id){
		WxMenu wxMenu=(WxMenu)CacheUtils.get(WX_CACHE, WX_CACHE_ID_ + id);
		if (wxMenu ==  null){
			wxMenu=wxMenuDao.get(id);
			if (wxMenu == null){
				return null;
			}	 
		 
			CacheUtils.put(WX_CACHE, WX_CACHE_ID_+id, wxMenu);
		}
		return wxMenu.getMenuName();
	}
}
