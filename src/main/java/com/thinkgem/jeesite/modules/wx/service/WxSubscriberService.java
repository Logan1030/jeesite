/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wx.entity.WxSubscriber;
import com.thinkgem.jeesite.modules.wx.dao.WxSubscriberDao;

/**
 * 微信订阅者Service
 * @author yubin
 * @version 2016-04-18
 */
@Service
@Transactional(readOnly = true)
public class WxSubscriberService extends CrudService<WxSubscriberDao, WxSubscriber> {

	public WxSubscriber get(String id) {
		return super.get(id);
	}
	
	public List<WxSubscriber> findList(WxSubscriber wxSubscriber) {
		return super.findList(wxSubscriber);
	}
	
	public Page<WxSubscriber> findPage(Page<WxSubscriber> page, WxSubscriber wxSubscriber) {
		return super.findPage(page, wxSubscriber);
	}
	
	@Transactional(readOnly = false)
	public void save(WxSubscriber wxSubscriber) {
		super.save(wxSubscriber);
	}
	
	@Transactional(readOnly = false)
	public void delete(WxSubscriber wxSubscriber) {
		super.delete(wxSubscriber);
	}
	public WxSubscriber getByOpenId(String openId){
		return dao.getByOpenId(openId);
	}
	
}