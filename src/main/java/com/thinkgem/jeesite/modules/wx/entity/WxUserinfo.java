/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信用户信息Entity
 * @author yubin
 * @version 2016-04-28
 */
public class WxUserinfo extends DataEntity<WxUserinfo> {
	
	private static final long serialVersionUID = 1L;
	private String openid;		// 用户的唯一标识
	private String nickname;		// 用户昵称
	private String sex;		// 用户的性别
	private String province;		// 用户省份
	private String city;		// 普通用户个人资料填写的城市
	private String country;		// 国家，如中国为CN
	private String headimgurl;		// 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	private String privilege;		// 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	private String unionid;		// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	
	public WxUserinfo() {
		super();
	}

	public WxUserinfo(String id){
		super(id);
	}

	@Length(min=0, max=128, message="用户的唯一标识长度必须介于 0 和 128 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Length(min=0, max=128, message="用户昵称长度必须介于 0 和 128 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=0, max=1, message="用户的性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=64, message="用户省份长度必须介于 0 和 64 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=64, message="普通用户个人资料填写的城市长度必须介于 0 和 64 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=64, message="国家，如中国为CN长度必须介于 0 和 64 之间")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Length(min=0, max=255, message="用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。长度必须介于 0 和 255 之间")
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	@Length(min=0, max=255, message="用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）长度必须介于 0 和 255 之间")
	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	@Length(min=0, max=128, message="只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。长度必须介于 0 和 128 之间")
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
}