package com.thinkgem.jeesite.modules.wx.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseHandler {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected HttpServletRequest currentRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
	}
}
