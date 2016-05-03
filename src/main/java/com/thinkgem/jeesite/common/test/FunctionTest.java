package com.thinkgem.jeesite.common.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.service.EmailService;

public class FunctionTest extends SpringTransactionalContextTests{
 
	@Autowired
    private EmailService emailService;	
	@Test
	public void doTest(){
		emailService.sendEmailByAccount();
	}
}
