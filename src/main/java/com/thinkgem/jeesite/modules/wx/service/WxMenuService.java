/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.BusinessConstant;
import com.thinkgem.jeesite.modules.wx.dao.WxMenuDao;
import com.thinkgem.jeesite.modules.wx.entity.WxMenu;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 微信菜单Service
 * @author yubin
 * @version 2016-04-16
 */
@Service
@Transactional(readOnly = true)
public class WxMenuService extends CrudService<WxMenuDao, WxMenu> {
    
	@Autowired
	private WxMpService wxMpService; 
	
	public WxMenu get(String id) {
		return super.get(id);
	}
	
	public List<WxMenu> findList(WxMenu wxMenu) {
		return super.findList(wxMenu);
	}
	
	public Page<WxMenu> findPage(Page<WxMenu> page, WxMenu wxMenu) {
		return super.findPage(page, wxMenu);
	}
	
	@Transactional(readOnly = false)
	public void save(WxMenu wxMenu) {
		super.save(wxMenu);
	}
	
	@Transactional(readOnly = false)
	public void delete(WxMenu wxMenu) {
		super.delete(wxMenu);
	}
	public List<WxMenu> findAllMenu(){
		return dao.findAllMenu();
	}
	public List<WxMenu>findsubMenu(String id){
		return dao.findsubMenu(id);
	}
	public String creatMenu() throws WxErrorException {
		
		String result=BusinessConstant.SUCCESS;
		
	    List<WxMenu>menus=this.findAllMenu();
	    if(menus==null){
	    	return "你还未建立菜单呢！";
	    }
        if(menus!=null&&menus.size()!=3){
        	return "必须建立三个一级菜单！";
        }
        List<WxMenuButton>buttonss=new ArrayList<WxMenuButton>();//微信一级菜单栏
        for(WxMenu wxMenu:menus){
        	List<WxMenu>menu=this.findsubMenu(wxMenu.getId());
        	if(menu!=null){//说明有二级菜单
        		List<WxMenuButton>buttons=new ArrayList<WxMenuButton>();//微信二级菜单栏
        		for(WxMenu wxMenu2:menu){
        			WxMenuButton wxMenuButton=new WxMenuButton();
            		wxMenuButton.setName(wxMenu2.getMenuName());
            		if(BusinessConstant.MENU_TYPE_CLICK.equals(wxMenu2.getMenuType())){
            			wxMenuButton.setType(WxConsts.BUTTON_CLICK);
            			wxMenuButton.setKey(wxMenu2.getMenuKey());
            		}else{
            			wxMenuButton.setType(WxConsts.BUTTON_VIEW);
            			String url=Global.getWxUrl()+wxMenu2.getMenuUrl();
            			wxMenuButton.setUrl(url);
            			//wxMenuButton.setUrl(wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAUTH2_SCOPE_USER_INFO, null));
            		}
            		buttons.add(wxMenuButton);
        		}
        		WxMenuButton wxMenuButtonMain=new WxMenuButton();
        		wxMenuButtonMain.setSubButtons(buttons);
        		wxMenuButtonMain.setName(wxMenu.getMenuName());
        		buttonss.add(wxMenuButtonMain);
        	}else{//说明只有一级菜单
        		WxMenuButton wxMenuButton=new WxMenuButton();
        		wxMenuButton.setName(wxMenu.getMenuName());
        		if(BusinessConstant.MENU_TYPE_CLICK.equals(wxMenu.getMenuType())){
        			wxMenuButton.setType(WxConsts.BUTTON_CLICK);
        			wxMenuButton.setKey(wxMenu.getMenuKey());
        		}else{
        			wxMenuButton.setType(WxConsts.BUTTON_VIEW);
        			String url=Global.getWxUrl()+wxMenu.getMenuUrl();
        			//wxMenuButton.setUrl(wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAUTH2_SCOPE_USER_INFO, null));
        		}
        		buttonss.add(wxMenuButton);
        	}
        }
	    
	    me.chanjar.weixin.common.bean.WxMenu menu=new me.chanjar.weixin.common.bean.WxMenu();
	    menu.setButtons(buttonss);
	    logger.info("微信创建菜单:"+menu.toJson());
	    wxMpService.menuCreate(menu);
	    return result;
	}
   
}