/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.wx.handler.EventHandler;
import com.thinkgem.jeesite.modules.wx.handler.LogHandler;
import com.thinkgem.jeesite.modules.wx.handler.NbaHandler;
import com.thinkgem.jeesite.modules.wx.handler.NewsHandler;
import com.thinkgem.jeesite.modules.wx.handler.OAuth2Handler;
import com.thinkgem.jeesite.modules.wx.handler.TextHandler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 测试Controller
 * @author ThinkGem
 * @version 2014-02-28
 */
@Controller
@RequestMapping(value = "${frontPath}/weixin")
public class WxController extends BaseController {
     @Autowired
     private WxMpService wxMpService;
     @Autowired
     private WxMpMessageRouter wxMpMessageRouter;//路由
     @Autowired
     private TextHandler textHandler;
     @Autowired
     private LogHandler logHandler;
     @Autowired
     private EventHandler eventHandler;
     @Autowired
     private OAuth2Handler oAuth2Handler;
     @Autowired
     private NewsHandler newsHandler;
     @Autowired
     private NbaHandler nbaHandler;
     
	/**
	 * 确认请求来自微信服务器
	 * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。 
	 * @param timestamp 时间戳
	 * @param nonce 随机数 
	 * @param echostr 随机数 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String get(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request) {
	
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
		if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
		      // 消息签名不正确，说明不是公众平台发过来的消息
		      return "非法请求";
		}
		
		return echostr;
	}
    /**
     * 
     * <p>
     * Description:处理微信服务器发来的消息<br />
     * </p>
     * @author yubin
     * @version 0.1 2016年4月14日
     * @param request
     * @return
     * String
     */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public void post(HttpServletRequest request,HttpServletResponse response)throws Exception {
		//路由前
		routerBefore();
	 
	    String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?"raw" :request.getParameter("encrypt_type");
        // 明文传输的消息
        if ("raw".equals(encryptType)) {
        	
          WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
          WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
          
          if (outMessage != null) {
        	  PrintWriter out=response.getWriter(); 
              out.write(outMessage.toXml());
     
          }
          return;
        }
	}
	/**
	 * 
	 * <p>
	 * Description:描述<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2016年4月19日
	 * void
	 */
	public void routerBefore(){
		 wxMpMessageRouter
		    .rule().async(false).handler(logHandler).next()
		    .rule().async(false).content("哈哈").handler(textHandler).end()
		    .rule().async(false).content("oauth").handler(oAuth2Handler).end()
		    .rule().async(false).content("图文消息").handler(newsHandler).end()
		    .rule().async(false).msgType(WxConsts.XML_MSG_EVENT).handler(eventHandler).end()
		    .rule().async(false).rContent(".*nba.*").handler(nbaHandler).end();
		    
	}
	
}
