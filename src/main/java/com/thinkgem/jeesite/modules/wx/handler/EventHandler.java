package com.thinkgem.jeesite.modules.wx.handler;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.utils.BusinessConstant;
import com.thinkgem.jeesite.modules.wx.entity.WxSubscriber;
import com.thinkgem.jeesite.modules.wx.service.WxSubscriberService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;
/**
 * 
 * <p>
 * Description:事件消息处理<br />
 * </p>
 * @title EventHandler.java
 * @package com.thinkgem.jeesite.modules.wx.handler 
 * @author yubin
 * @version 0.1 2016年4月18日
 */
@Component
public class EventHandler extends BaseHandler implements WxMpMessageHandler {
     @Autowired
	 private WxSubscriberService wxSubscriberService;
	 
	 public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
		      WxSessionManager sessionManager) {
		    WxMpXmlOutTextMessage m=null;
		    String content="";
		    if(wxMessage.getEvent().equals(WxConsts.EVT_SUBSCRIBE)){	
		    	WxSubscriber wxSubscriber=wxSubscriberService.getByOpenId(wxMessage.getFromUserName());
		    	if(wxSubscriber==null){
		    		wxSubscriber=new WxSubscriber();
			    	wxSubscriber.setOpenId(wxMessage.getFromUserName());
			    	wxSubscriber.setType(BusinessConstant.SUBSCRIBE);
			    	wxSubscriberService.save(wxSubscriber);
			    	content="欢迎你关注我们的微信";
		    	}else{
		    		wxSubscriber.setType(BusinessConstant.SUBSCRIBE);
		    		wxSubscriber.setUpdateDate(new Date());
		    		wxSubscriberService.save(wxSubscriber);
		    		content="欢迎你再次关注我们的微信";
		    	}
		    	m = WxMpXmlOutMessage.TEXT().content(content).fromUser(wxMessage.getToUserName())
		    			.toUser(wxMessage.getFromUserName()).build();
		    	
		    }else if(wxMessage.getEvent().equals(WxConsts.EVT_UNSUBSCRIBE)){
		    	WxSubscriber wxSubscriber=wxSubscriberService.getByOpenId(wxMessage.getFromUserName());
		    	wxSubscriber.setType(BusinessConstant.UNSUBSCRIBE);
		    	wxSubscriber.setUpdateDate(new Date());
		        wxSubscriberService.save(wxSubscriber);
		    	m = WxMpXmlOutMessage.TEXT().content("用户取消了微信").fromUser(wxMessage.getToUserName())
		    			.toUser(wxMessage.getFromUserName()).build();
		    }else if(wxMessage.getEvent().equals(WxConsts.EVT_CLICK)){
		    	if(wxMessage.getEventKey().equals("nba")){
		    		m = WxMpXmlOutMessage.TEXT().content("请输入你喜欢的NBA篮球队名称,例如：nba骑士").fromUser(wxMessage.getToUserName())
			    			.toUser(wxMessage.getFromUserName()).build();
		    	}else{
		    		m = WxMpXmlOutMessage.TEXT().content("你点击一下试试").fromUser(wxMessage.getToUserName())
			    			.toUser(wxMessage.getFromUserName()).build();
		    	}
		    	
		    }else if(wxMessage.getEvent().equals(WxConsts.EVT_VIEW)){
		    	m = WxMpXmlOutMessage.TEXT().content("你点击的是超链接").fromUser(wxMessage.getToUserName())
		    			.toUser(wxMessage.getFromUserName()).build();
		    }
		    return m;
		  }
	}
