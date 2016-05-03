package com.thinkgem.jeesite.modules.wx.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

@Service
public class WeiXinService {
	
	private  final Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
    private WxMpService wxMpService;
	
	public void creatMenu() throws WxErrorException {
	     
        WxMenuButton wxMenuButton=new WxMenuButton();
        wxMenuButton.setName("今日歌曲");
        wxMenuButton.setType(WxConsts.BUTTON_CLICK);
        wxMenuButton.setKey("V1001_TODAY_MUSIC");
        WxMenuButton wxMenuButton1=new WxMenuButton();
        wxMenuButton1.setName("歌手简介");
        wxMenuButton1.setType(WxConsts.BUTTON_CLICK);
        wxMenuButton1.setKey("V1001_TODAY_SINGER");
        WxMenuButton wxMenuButton2=new WxMenuButton();
        wxMenuButton2.setName("菜单简介");
        wxMenuButton2.setType(WxConsts.BUTTON_VIEW);
        wxMenuButton2.setUrl("http://www.soso.com/");
        
        List<WxMenuButton>buttons=new ArrayList<WxMenuButton>();
	    buttons.add(wxMenuButton);
	    buttons.add(wxMenuButton1);
	    buttons.add(wxMenuButton2);
        
        WxMenuButton wxmenu=new WxMenuButton();
	    wxmenu.setName("菜单简介");
	    wxmenu.setSubButtons(buttons);
	    
	    List<WxMenuButton>buttonss=new ArrayList<WxMenuButton>();
	    buttonss.add(wxMenuButton);
	    buttonss.add(wxMenuButton1);
	    buttonss.add(wxmenu);
	    
	    WxMenu menu=new WxMenu();
	    menu.setButtons(buttonss);
	    logger.info("创建订单"+menu.toString());
	    wxMpService.menuCreate(menu);
	  }
}
