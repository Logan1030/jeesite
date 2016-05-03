/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.BusinessConstant;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.wx.entity.WxMenu;
import com.thinkgem.jeesite.modules.wx.service.WxMenuService;

import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * 微信菜单Controller
 * @author yubin
 * @version 2016-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/wx/wxMenu")
public class WxMenuController extends BaseController {

	@Autowired
	private WxMenuService wxMenuService;
	
	@ModelAttribute
	public WxMenu get(@RequestParam(required=false) String id) {
		WxMenu entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wxMenuService.get(id);
		}
		if (entity == null){
			entity = new WxMenu();
		}
		return entity;
	}
	
	@RequiresPermissions("wx:wxMenu:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxMenu wxMenu, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WxMenu> page = wxMenuService.findPage(new Page<WxMenu>(request, response), wxMenu); 
		model.addAttribute("page", page);
		
		return "modules/wx/wxMenuList";
	}

	@RequiresPermissions("wx:wxMenu:view")
	@RequestMapping(value = "form")
	public String form(WxMenu wxMenu, Model model) {
		model.addAttribute("wxMenu", wxMenu);
		model.addAttribute("menu", wxMenuService.get(wxMenu.getParentId()));
		return "modules/wx/wxMenuForm";
	}

	@RequiresPermissions("wx:wxMenu:edit")
	@RequestMapping(value = "save")
	public String save(WxMenu wxMenu, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wxMenu)){
			return form(wxMenu, model);
		}
		wxMenuService.save(wxMenu);
		addMessage(redirectAttributes, "保存微信菜单成功");
		return "redirect:"+Global.getAdminPath()+"/wx/wxMenu/?repage";
	}
	@RequiresPermissions("wx:wxMenu:edit")
	@RequestMapping(value = "createMenu")
	public String createMenu( RedirectAttributes redirectAttributes) {
		String result="";
		try {
			result=wxMenuService.creatMenu();
		} catch (WxErrorException e) {
			logger.error("微信创建菜单", e);
			e.printStackTrace();
		}
		if(BusinessConstant.SUCCESS.equals(result)){
		   addMessage(redirectAttributes, " 微信菜单创建成功");
		}else{
		   addMessage(redirectAttributes, result);	
		}
		return "redirect:"+Global.getAdminPath()+"/wx/wxMenu/?repage";
	}
	
	@RequiresPermissions("wx:wxMenu:edit")
	@RequestMapping(value = "delete")
	public String delete(WxMenu wxMenu, RedirectAttributes redirectAttributes) {
		wxMenuService.delete(wxMenu);
		addMessage(redirectAttributes, "删除微信菜单成功");
		return "redirect:"+Global.getAdminPath()+"/wx/wxMenu/?repage";
	}
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId,@RequestParam(required=false) String isShowHide) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<WxMenu> list =wxMenuService.findAllMenu();
		for (int i=0; i<list.size(); i++){
			WxMenu e = list.get(i);
			if(isShowHide != null && isShowHide.equals("0") && e.getIsShow().equals("0")){
				continue;
			}
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", e.getMenuName());
			mapList.add(map);
		}
		return mapList;
	}

}