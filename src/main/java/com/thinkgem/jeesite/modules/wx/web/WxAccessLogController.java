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
import com.thinkgem.jeesite.modules.wx.entity.WxAccessLog;
import com.thinkgem.jeesite.modules.wx.service.WxAccessLogService;

/**
 * 微信访问日志Controller
 * @author yubin
 * @version 2016-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/wx/wxAccessLog")
public class WxAccessLogController extends BaseController {

	@Autowired
	private WxAccessLogService wxAccessLogService;
	
	@ModelAttribute
	public WxAccessLog get(@RequestParam(required=false) String id) {
		WxAccessLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wxAccessLogService.get(id);
		}
		if (entity == null){
			entity = new WxAccessLog();
		}
		return entity;
	}
	
	@RequiresPermissions("wx:wxAccessLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxAccessLog wxAccessLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WxAccessLog> page = wxAccessLogService.findPage(new Page<WxAccessLog>(request, response), wxAccessLog); 
		model.addAttribute("page", page);
		return "modules/wx/wxAccessLogList";
	}

	@RequiresPermissions("wx:wxAccessLog:view")
	@RequestMapping(value = "form")
	public String form(WxAccessLog wxAccessLog, Model model) {
		model.addAttribute("wxAccessLog", wxAccessLog);
		return "modules/wx/wxAccessLogForm";
	}

	@RequiresPermissions("wx:wxAccessLog:edit")
	@RequestMapping(value = "save")
	public String save(WxAccessLog wxAccessLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wxAccessLog)){
			return form(wxAccessLog, model);
		}
		wxAccessLogService.save(wxAccessLog);
		addMessage(redirectAttributes, "保存微信访问日志成功");
		return "redirect:"+Global.getAdminPath()+"/wx/wxAccessLog/?repage";
	}
	
	@RequiresPermissions("wx:wxAccessLog:edit")
	@RequestMapping(value = "delete")
	public String delete(WxAccessLog wxAccessLog, RedirectAttributes redirectAttributes) {
		wxAccessLogService.delete(wxAccessLog);
		addMessage(redirectAttributes, "删除微信访问日志成功");
		return "redirect:"+Global.getAdminPath()+"/wx/wxAccessLog/?repage";
	}

}