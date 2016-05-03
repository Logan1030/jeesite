package com.thinkgem.jeesite.modules.wx.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.modules.wx.entity.Nba;
import com.thinkgem.jeesite.modules.wx.service.BasketballService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;
@Component
public class NbaHandler extends BaseHandler implements WxMpMessageHandler {

	@Autowired
	private BasketballService basketballService;
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		
		try {
			List<Nba>nbas=basketballService.queryByTeamName(wxMessage.getContent().replace("nba", ""));
			if(nbas==null){
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT()
						.content("查询不到队伍赛程相关信息")
						.fromUser(wxMessage.getToUserName())
		    			.toUser(wxMessage.getFromUserName()).build();
				 return m;
			}else{
				WxMpXmlOutNewsMessage m=WxMpXmlOutMessage.NEWS().fromUser(wxMessage.getToUserName())
						.toUser(wxMessage.getFromUserName()).build();
				for(Nba nba:nbas){
					WxMpXmlOutNewsMessage.Item item=new WxMpXmlOutNewsMessage.Item();
					item.setPicUrl(nba.getPlayer1logo());
					item.setUrl(nba.getLink1url());					
					item.setTitle(nba.getPlayer1()+"-"+nba.getPlayer2()+" :"+nba.getScore());
					item.setDescription("比分"+nba.getScore());
					m.addArticle(item);
				}
				return m;
			}
		} catch (Exception e) {
			logger.error("nba赛事",e);
		}
		return null;
		
	}
	public static void main(String[] args) {
		String a="98-102";
		for(String b:a.split("-")){
			System.out.println(b);
		}
		System.out.println();
	}
    
}
