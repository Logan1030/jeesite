package com.thinkgem.jeesite.common.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.utils.SendMailUtil;
@Service
public class EmailService {
	
	@Autowired
    private SendMailUtil sendMailUtil;
	
	public void sendEmailByAccount(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subject", "你笨蛋");
		map.put("content", "你笨蛋");
		map.put("contextPath", "http://www.gcjr.com/");
		String templatePath = "mailtemplate/test.ftl";
		sendMailUtil.sendFtlMail("292225404@qq.com", "sendemail test!", templatePath, map);
	}
}
