/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信订阅者Entity
 * @author yubin
 * @version 2016-04-18
 */
public class WxSubscriber extends DataEntity<WxSubscriber> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 订阅类型
	private String openId;		// 微信者
	
	public WxSubscriber() {
		super();
	}

	public WxSubscriber(String id){
		super(id);
	}

	@Length(min=0, max=1, message="订阅类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="微信者长度必须介于 0 和 255 之间")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}