package com.thinkgem.jeesite.modules.wx.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.utils.HttpTookit;
import com.thinkgem.jeesite.modules.wx.entity.WxAccessLog;
import com.thinkgem.jeesite.modules.wx.service.WxAccessLogService;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
/**
 * 
 * <p>
 * Description:日志事件处理<br />
 * </p>
 * @title LogHandler.java
 * @package com.thinkgem.jeesite.modules.wx.handler 
 * @author yubin
 * @version 0.1 2016年4月18日
 */
@Component
public class LogHandler extends BaseHandler implements WxMpMessageHandler{
 @Autowired
 private  WxAccessLogService wxAccessLogService;
 
 public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
	      WxSessionManager sessionManager) {
	    logger.info(wxMessage.toString());
	    WxAccessLog wxAccessLog=new WxAccessLog();
	    wxAccessLog.setOpenId(wxMessage.getFromUserName());
	    wxAccessLog.setIp(HttpTookit.getRealIpAddr(currentRequest()));
	    wxAccessLogService.save(wxAccessLog);
	    return null;
	  }
}
