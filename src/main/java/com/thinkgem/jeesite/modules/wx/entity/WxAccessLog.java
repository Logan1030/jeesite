/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信访问日志Entity
 * @author yubin
 * @version 2016-04-16
 */
public class WxAccessLog extends DataEntity<WxAccessLog> {
	
	private static final long serialVersionUID = 1L;
	private String openId;		// 微信者
	private String ip;		// ip
	
	public WxAccessLog() {
		super();
	}

	public WxAccessLog(String id){
		super(id);
	}

	@Length(min=0, max=255, message="微信者长度必须介于 0 和 255 之间")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	@Length(min=0, max=64, message="ip长度必须介于 0 和 64 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}