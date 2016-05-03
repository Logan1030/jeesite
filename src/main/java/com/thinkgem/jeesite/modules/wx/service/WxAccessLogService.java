/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wx.entity.WxAccessLog;
import com.thinkgem.jeesite.modules.wx.dao.WxAccessLogDao;

/**
 * 微信访问日志Service
 * @author yubin
 * @version 2016-04-16
 */
@Service
@Transactional(readOnly = true)
public class WxAccessLogService extends CrudService<WxAccessLogDao, WxAccessLog> {

	public WxAccessLog get(String id) {
		return super.get(id);
	}
	
	public List<WxAccessLog> findList(WxAccessLog wxAccessLog) {
		return super.findList(wxAccessLog);
	}
	
	public Page<WxAccessLog> findPage(Page<WxAccessLog> page, WxAccessLog wxAccessLog) {
		return super.findPage(page, wxAccessLog);
	}
	
	@Transactional(readOnly = false)
	public void save(WxAccessLog wxAccessLog) {
		super.save(wxAccessLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(WxAccessLog wxAccessLog) {
		super.delete(wxAccessLog);
	}
	
}