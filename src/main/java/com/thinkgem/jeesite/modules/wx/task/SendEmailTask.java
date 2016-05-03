package com.thinkgem.jeesite.modules.wx.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.service.EmailService;

@Service
@Lazy(false)
public class SendEmailTask {
    
	@Autowired
	private EmailService emailService;
	
	 
	public void sendEmail(){
		emailService.sendEmailByAccount();
	}
}
