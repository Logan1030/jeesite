package com.thinkgem.jeesite.modules.wx.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;
/**
 * 
 * <p>
 * Description:微信消息文本处理<br />
 * </p>
 * @title TextHandler.java
 * @package com.thinkgem.jeesite.modules.cms.wx 
 * @author yubin
 * @version 0.1 2016年4月15日
 */
@Component
public class TextHandler extends BaseHandler implements WxMpMessageHandler{
	
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
		      WxMpService wxMpService, WxSessionManager sessionManager) {
		    
		    WxMpXmlOutTextMessage m
		        = WxMpXmlOutMessage.TEXT().content("欢迎你加入我们的").fromUser(wxMessage.getToUserName())
		        .toUser(wxMessage.getFromUserName()).build();
		    logger.error(m.toXml());
		    return m;
		  }
}
