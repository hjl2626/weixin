package com.hjl.core.api.reply.impl;

import com.hjl.core.api.reply.ReplyStrategy;
import com.hjl.base.config.AppConfig;
import com.hjl.base.constants.WeiXinMsgType;
import com.hjl.core.message.bean.tuling.*;
import com.hjl.core.message.bean.weixin.WeiXinReceiveMsg;
import com.hjl.core.message.bean.weixin.WeiXinReplyMsg;
import com.hjl.base.utils.GsonUtil;
import com.hjl.base.utils.HttpUtil;
import org.springframework.stereotype.Component;

/**
 * Created by hjl on 2017/3/1.
 */
@Component("tulingReplyStrategy")
public class TulingReplyStrategy implements ReplyStrategy {
	@Override
	public WeiXinReplyMsg reply(WeiXinReceiveMsg msg) {
		
		WeiXinReplyMsg reply = new WeiXinReplyMsg();
		reply.setCreateTime(System.currentTimeMillis()+"");
		reply.setToUserName(msg.getFromUserName());
		reply.setFromUserName(msg.getToUserName());
		reply.setMsgType(WeiXinMsgType.MSG_TYPE_TEXT);
		reply.setContent(this.getReply(msg.getFromUserName(), msg.getContent()));
		return reply;
	}
	
	@Override
	public String reply(String content) {
		return null;
	}
	
	private String getReply(String userId, String content) {
		String url = AppConfig.getConfigTuLingApiUrl();
		String key = AppConfig.getConfigTuLingApiKey();
		ApiRequest request = new ApiRequest()
				.setInfo(content)
				.setKey(key)
				.setUserid(userId);
		String result = HttpUtil.httpPostWithJson(url, GsonUtil.toJson(request));
		BaseResp resp = (BaseResp) GsonUtil.fromJson(result, BaseResp.class);
		switch (resp.getCode()) {
			case "100000":
				return resp.getText();
			case "200000":
				LinkResp linkResp = (LinkResp) GsonUtil.fromJson(result, LinkResp.class);
				return linkResp.getText() + "\n" + linkResp.getUrl();
			case "302000":
				NewsResp newsResp = (NewsResp) GsonUtil.fromJson(result, NewsResp.class);
				return getStringForNews(newsResp);
			case "308000":
				CookBookResp cookbookResp = (CookBookResp) GsonUtil.fromJson(result, CookBookResp.class);
				return getStringForCookBook(cookbookResp);
			default:
				break;
			
			
		}
		return "";
	}
	
	private String getStringForNews(NewsResp newsResp) {
		StringBuilder builder = new StringBuilder(newsResp.getText() + "\n");
		
		for (News news : newsResp.getList()) {
			builder.append(news.getArticle())
					.append("-----")
					.append(news.getDetailurl());
			builder.append("\n");
		}
		return builder.toString();
	}
	
	private String getStringForCookBook(CookBookResp cookbookResp) {
		StringBuilder builder = new StringBuilder(cookbookResp.getText() + "\n");
		
		for (CookBook c : cookbookResp.getList()) {
			builder.append(c.getName())
					.append("-----")
					.append(c.getInfo())
					.append("-----")
					.append(c.getDetailurl());
			builder.append("\n");
		}
		return builder.toString();
	}
}
