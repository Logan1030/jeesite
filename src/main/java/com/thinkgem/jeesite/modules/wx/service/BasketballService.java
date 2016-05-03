package com.thinkgem.jeesite.modules.wx.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.modules.wx.entity.Nba;
import com.thinkgem.jeesite.modules.wx.util.WxUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Component
public class BasketballService {
	
	private  final Logger logger=LoggerFactory.getLogger(getClass());
    public static final String APPKEY ="4c107e14407ecbc255bf4298e853ce81";
    
    public List<Nba>queryByTeamName(String name)throws Exception{
       Object nbaJson=this.queryTeam(name+"队");	
       if(nbaJson!=null){
	       JSONArray array =JSONArray.fromObject(nbaJson);
	 	   List<?>oList=JSONArray.toList(array);
	 	   List<Nba>nbas=new ArrayList<Nba>();
	 	   for(Object object:oList){
	 		   Nba nba=new Nba();
	 		   Class userCla =(Class)nba.getClass(); 
	 		   Field[] fs = userCla.getDeclaredFields();
	 		   for(int i = 0 ; i < fs.length; i++){  
	 	           Field f = fs[i];
	 	           //设置些属性是可以访问的  
	 	           f.setAccessible(true); 
	 	           f.set(nba, JSONObject.fromObject(object).get(f.getName()));
	 		   }
	 		   nbas.add(nba);
	 	   }
	 	   return nbas;
       }else{
    	   return null;
       }
    }
    
	public  Object queryTeam(String name)throws Exception{
        String result =null;
        String url ="http://op.juhe.cn/onebox/basketball/team";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json
        params.put("team",name);//球队名称
  
        result =WxUtil.net(url, params, "GET");
        JSONObject object = JSONObject.fromObject(result);
        if(object.getInt("error_code")==0){
            return JSONObject.fromObject(object.get("result")).get("list");
        }else{
        	logger.info("篮球赛事接口"+object.get("error_code")+":"+object.get("reason"));
        	return null;
        }
        
    }
	
}
