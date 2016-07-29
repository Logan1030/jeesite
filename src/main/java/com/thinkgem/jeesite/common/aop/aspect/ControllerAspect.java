package com.thinkgem.jeesite.common.aop.aspect;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Controller切面
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title DuplicateSubmissionAspect.java
 * @package com.cxdai.aop.aspect
 * @author zhaowei
 * @version 0.1 2015年11月18日
 */
@Aspect
@Component
public class ControllerAspect {

	private List<ControllerAspectProcessor> processors;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	
	//@Pointcut("@annotation(com.thinkgem.jeesite.common.aop.annotation.GenerateToken)")
	
	@Pointcut("execution(public * com.thinkgem.jeesite.modules.sys.web..*.*(..))")
	public void controllerPointcut() {
	}
    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
    	logger.info("接收到请求，记录请求内容");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Map<String, Object>maps=request.getParameterMap();
        Iterator<String> keys = maps.keySet().iterator();

        while(keys.hasNext()) {
        String key = (String) keys.next();
        Object value=maps.get(key);
        System.out.println("键"+key+"="+"值"+value);

        } 
        logger.info(request.getParameter("revision"));
        logger.debug("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }
    @AfterReturning(returning = "ret", pointcut = "controllerPointcut()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
    	logger.info("处理完请求，返回内容");
        logger.debug("RESPONSE : " + ret);
    }
	/**
	 * 环绕方法
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author zhaowei
	 * @version 0.1 2015年11月19日
	 * @param pjp
	 * @return
	 * @throws Throwable
	 *             Object
	 */
	@Around("controllerPointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		 
		logger.info("在切入点前后切入内容，并自己控制何时执行切入点自身的内容");
		for (ControllerAspectProcessor p : getProcessors()) {
		 
			pjp.getTarget();
			logger.info("p:"+p);
			if (p != null) {
			  
				Object o = p.doProcess(pjp);
                logger.info("00000:"+o); 
				if (o != null) {
					return o;
				}
			}
		}

		return pjp.proceed();
	}

	public List<ControllerAspectProcessor> getProcessors() {
		return processors;
	}

	@Autowired
	public void setProcessors(List<ControllerAspectProcessor> processors) {
		this.processors = processors;
	}

}
