/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.wx.entity.WxUserinfo;
import com.thinkgem.jeesite.modules.wx.service.WxUserinfoService;

/**
 * 微信用户信息Controller
 * @author yubin
 * @version 2016-04-28
 */
@Controller
@RequestMapping(value = "${adminPath}/wx/wxUserinfo")
public class WxUserinfoController extends BaseController {

	@Autowired
	private WxUserinfoService wxUserinfoService;
	
	@ModelAttribute
	public WxUserinfo get(@RequestParam(required=false) String id) {
		WxUserinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wxUserinfoService.get(id);
		}
		if (entity == null){
			entity = new WxUserinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("wx:wxUserinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxUserinfo wxUserinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WxUserinfo> page = wxUserinfoService.findPage(new Page<WxUserinfo>(request, response), wxUserinfo); 
		model.addAttribute("page", page);
		return "modules/wx/wxUserinfoList";
	}

	@RequiresPermissions("wx:wxUserinfo:view")
	@RequestMapping(value = "form")
	public String form(WxUserinfo wxUserinfo, Model model) {
		model.addAttribute("wxUserinfo", wxUserinfo);
		return "modules/wx/wxUserinfoForm";
	}

	@RequiresPermissions("wx:wxUserinfo:edit")
	@RequestMapping(value = "save")
	public String save(WxUserinfo wxUserinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wxUserinfo)){
			return form(wxUserinfo, model);
		}
		wxUserinfoService.save(wxUserinfo);
		addMessage(redirectAttributes, "保存yubin成功");
		return "redirect:"+Global.getAdminPath()+"/wx/wxUserinfo/?repage";
	}
	
	@RequiresPermissions("wx:wxUserinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(WxUserinfo wxUserinfo, RedirectAttributes redirectAttributes) {
		wxUserinfoService.delete(wxUserinfo);
		addMessage(redirectAttributes, "删除yubin成功");
		return "redirect:"+Global.getAdminPath()+"/wx/wxUserinfo/?repage";
	}

}