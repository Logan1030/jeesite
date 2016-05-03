/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.service;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wx.dao.WxUserinfoDao;
import com.thinkgem.jeesite.modules.wx.entity.WxUserinfo;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 微信用户信息Service
 * @author yubin
 * @version 2016-04-28
 */
@Service
@Transactional(readOnly = true)
public class WxUserinfoService extends CrudService<WxUserinfoDao, WxUserinfo> {

	public WxUserinfo get(String id) {
		return super.get(id);
	}
	
	public List<WxUserinfo> findList(WxUserinfo wxUserinfo) {
		return super.findList(wxUserinfo);
	}
	
	public Page<WxUserinfo> findPage(Page<WxUserinfo> page, WxUserinfo wxUserinfo) {
		return super.findPage(page, wxUserinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(WxUserinfo wxUserinfo) {
		super.save(wxUserinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(WxUserinfo wxUserinfo) {
		super.delete(wxUserinfo);
	}
	@Transactional(readOnly = false)
	public void saveUserInfo(WxMpUser wxMpUser)throws Exception{
		if(wxMpUser!=null){
			WxUserinfo wxUserinfo=dao.getByOpenId(wxMpUser.getOpenId().trim());
			if(wxUserinfo==null){
				wxUserinfo=new WxUserinfo();
				wxUserinfo.setOpenid(wxMpUser.getOpenId());
				wxUserinfo.setHeadimgurl(wxMpUser.getHeadImgUrl());
				wxUserinfo.setUnionid(wxMpUser.getUnionId());
				BeanUtils.copyProperties(wxUserinfo, wxMpUser);
				save(wxUserinfo);
			}
		}
	}
	
}