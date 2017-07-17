package com.hjl.core.factory;

import com.hjl.base.constants.WeiXinMsgType;
import com.hjl.core.message.bean.weixin.WeiXinReceiveMsg;
import com.hjl.core.message.bean.weixin.WeiXinReplyImgTextMsg;
import com.hjl.core.message.bean.weixin.WeiXinReplyMsg;

import java.util.List;

/**
 * Created by hjl on 2017/3/3.
 */
public class ReplyMsgFactory {
	
	private ReplyMsgFactory(){
		
	}
	
	
	public static WeiXinReplyMsg getTextMsg(WeiXinReceiveMsg msg){
		WeiXinReplyMsg replyMsg = new WeiXinReplyMsg();
		return replyMsg
				.setCreateTime(System.currentTimeMillis()/1000+"")
				.setToUserName(msg.getFromUserName())
				.setFromUserName(msg.getToUserName())
				.setMsgType(WeiXinMsgType.MSG_TYPE_TEXT);
	}
	
	public static WeiXinReplyMsg getImgMsg(WeiXinReceiveMsg msg){
		WeiXinReplyMsg replyMsg = new WeiXinReplyMsg();
		return replyMsg
				.setCreateTime(System.currentTimeMillis()/1000+"")
				.setToUserName(msg.getFromUserName())
				.setFromUserName(msg.getToUserName())
				.setMsgType(WeiXinMsgType.MSG_TYPE_IMAGE);
	}
	
	public static WeiXinReplyMsg getDrawDefaultMsg(WeiXinReceiveMsg msg){
		WeiXinReplyMsg replyMsg = new WeiXinReplyMsg();
		return replyMsg
				.setCreateTime(System.currentTimeMillis()/1000+"")
				.setToUserName(msg.getFromUserName())
				.setFromUserName(msg.getToUserName())
				.setMsgType(WeiXinMsgType.MSG_TYPE_TEXT)
				.setContent("再接再厉");
	}
	
	public static WeiXinReplyMsg getReplyDefaultMsg(WeiXinReceiveMsg msg){
		WeiXinReplyMsg replyMsg = new WeiXinReplyMsg();
		return replyMsg
				.setCreateTime(System.currentTimeMillis()/1000+"")
				.setToUserName(msg.getFromUserName())
				.setFromUserName(msg.getToUserName())
				.setMsgType(WeiXinMsgType.MSG_TYPE_TEXT)
				.setContent("欢迎咨询");
	}
	
	
	public static WeiXinReplyMsg getPrizeNotEnoughMsg(WeiXinReceiveMsg msg) {
		WeiXinReplyMsg replyMsg = new WeiXinReplyMsg();
		return replyMsg
				.setCreateTime(System.currentTimeMillis()/1000+"")
				.setToUserName(msg.getFromUserName())
				.setFromUserName(msg.getToUserName())
				.setMsgType(WeiXinMsgType.MSG_TYPE_TEXT)
				.setContent("奖品不够");
	}
	
	public static WeiXinReplyMsg getNewsIMg(WeiXinReceiveMsg message , List<WeiXinReplyImgTextMsg.Item> items){
		WeiXinReplyMsg msg = new WeiXinReplyMsg();
		msg.setFromUserName(message.getToUserName());
		msg.setToUserName(message.getFromUserName());
		msg.setCreateTime((System.currentTimeMillis() / 1000) + "");
		msg.setMsgType("news");
		msg.setArticleCount(items.size()+"");
		WeiXinReplyImgTextMsg imgTextMsg = new WeiXinReplyImgTextMsg();
		imgTextMsg.setItems(items);
		msg.setData(imgTextMsg);
		return msg;
	}
}
