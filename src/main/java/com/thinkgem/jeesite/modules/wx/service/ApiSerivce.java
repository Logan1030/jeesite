package com.thinkgem.jeesite.modules.wx.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.thinkgem.jeesite.modules.wx.util.WxUtil;
import net.sf.json.JSONObject;
@Component
public class ApiSerivce {
	public static final String  IP_URL = "http://apis.juhe.cn/ip/ip2addr";
	public static final String IP_APPKEY = "967be67142331ee28ac9e01fea0fed10"; 
	private  final Logger logger=LoggerFactory.getLogger(getClass());
	/**
	 * 
	 * <p>
	 * Description:IP地址<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2016年4月19日
	 * @param ip
	 * void
	 */
	public Object queryIp(String ip){
		  logger.info("ip"+ip);
		  Map params = new HashMap();//请求参数
	      params.put("key",IP_APPKEY);//应用APPKEY(应用详细页查询)
	      params.put("dtype","");//返回数据的格式,xml或json，默认json
	      params.put("ip",ip);//球队名称
	      String result="";
	      try {
	          result =WxUtil.net(IP_URL, params, "GET");
	          JSONObject object = JSONObject.fromObject(result);
	          if(object.getInt("error_code")==0){
	        	   logger.info(object.get("result").toString());
	        	  return object.get("result");
	          }else{
	              return null;
	          }
	      } catch (Exception e) {
	         logger.error("获取IP地址",e);
	      }
	      return null;
	  }
}
