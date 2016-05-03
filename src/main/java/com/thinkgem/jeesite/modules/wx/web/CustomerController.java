package com.thinkgem.jeesite.modules.wx.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.wx.service.WxUserinfoService;
import com.thinkgem.jeesite.modules.wx.util.SignUtil;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
@Controller
@RequestMapping(value = "${frontPath}/wx")
public class CustomerController extends BaseController {
	
	@Autowired
    private WxMpService wxMpService;
	@Autowired
	private WxUserinfoService wxUserinfoService;
	/**
	 * 
	 * <p>
	 * Description:我的简介<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2016年4月27日
	 * @param code
	 * @param model
	 * @return
	 * String
	 */
	@RequestMapping(value = "userInfo")
	public String userInfo(String code, HttpServletRequest request,Model model) {
		WxMpUser wxMpUser=null;
		try {
		    //WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
		    //wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
		    //wxUserinfoService.saveUserInfo(wxMpUser);
		    model.addAttribute("jsapi", SignUtil.sign(wxMpService.getJsapiTicket(), request.getRequestURL().toString()));
		} catch (Exception e) {
			logger.error("我的简介",e);
		}
		model.addAttribute("wxMpUser", wxMpUser);
		return "modules/wx/userInfo";
	}
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request,Model model){
	    try {
	    	Map<String, String> ret = SignUtil.sign(wxMpService.getJsapiTicket(), request.getRequestURL().toString());
		   model.addAttribute("jsapi", ret);
		} catch (WxErrorException e) {
			logger.error("微信js",e);
		}catch (Exception e) {
			logger.error("微信js",e);
		}
		return "modules/wx/form";
	}
}
