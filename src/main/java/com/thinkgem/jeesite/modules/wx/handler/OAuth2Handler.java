package com.thinkgem.jeesite.modules.wx.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
@Component
public class OAuth2Handler extends BaseHandler implements WxMpMessageHandler {
    
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		String href = "<a href=\"" + wxMpService.oauth2buildAuthorizationUrl
				("http://yubinpll9110.oicp.net/jeesite/f/weixin/userInfo",WxConsts.OAUTH2_SCOPE_USER_INFO, null)
        + "\">授权</a>";
    return WxMpXmlOutMessage
        .TEXT()
        .content(href)
        .fromUser(wxMessage.getToUserName())
        .toUser(wxMessage.getFromUserName()).build();
	}

}
