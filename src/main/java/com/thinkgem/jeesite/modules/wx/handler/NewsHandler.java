package com.thinkgem.jeesite.modules.wx.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
@Component
public class NewsHandler extends BaseHandler implements WxMpMessageHandler	{
	
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		
		WxMpXmlOutNewsMessage.Item item=new WxMpXmlOutNewsMessage.Item();
		item.setDescription("heeh");
		item.setPicUrl("http://inews.gtimg.com/newsapp_ls/0/257593192/0");
		item.setTitle("我");
		item.setUrl("http://sports.qq.com/a/20160417/006167.htm#p=1");
		WxMpXmlOutNewsMessage m=WxMpXmlOutMessage.NEWS()
				           .fromUser(wxMessage.getToUserName())
				           .toUser(wxMessage.getFromUserName())
				           .addArticle(item).addArticle(item)
				           .build();
		return m;
	}

}
