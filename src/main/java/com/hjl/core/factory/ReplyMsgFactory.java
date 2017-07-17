package com.hjl.core.factory;

import com.hjl.base.constants.WeiXinMsgType;
import com.hjl.core.message.bean.weixin.WeiXinReceiveMsg;
import com.hjl.core.message.bean.weixin.replyMsg.*;

import java.util.List;

/**
 * Created by hjl on 2017/3/3.
 */
public class ReplyMsgFactory {
	
	private ReplyMsgFactory(){
		
	}
	
	
	/**
	 * 获取自动回复 TEXT 消息
	 * @param msg 原消息
	 * @return WeiXinReplyMsg
	 */
	public static WeiXinReplyMsg getTextMsg(WeiXinReceiveMsg msg){
		WeiXinReplyMsg replyMsg = new WeiXinReplyMsg();
		return replyMsg
				.setCreateTime(System.currentTimeMillis()/1000+"")
				.setToUserName(msg.getFromUserName())
				.setFromUserName(msg.getToUserName())
				.setMsgType(WeiXinMsgType.MSG_TYPE_TEXT);
	}
	
	/**
	 * 获取自动回复 图片 消息
	 * @param msg 原消息
	 * @param mediaId mediaId
	 * @return WeiXinReplyMsg
	 */
	public static WeiXinReplyMsg getImgMsg(WeiXinReceiveMsg msg , String mediaId){
		WeiXinReplyMsg replyMsg = new WeiXinReplyMsg();
		WeiXinReplyImgMsg imgMsg = new WeiXinReplyImgMsg().setMediaId(mediaId);
		return replyMsg
				.setCreateTime(System.currentTimeMillis()/1000+"")
				.setToUserName(msg.getFromUserName())
				.setFromUserName(msg.getToUserName())
				.setMsgType(WeiXinMsgType.MSG_TYPE_IMAGE)
				.setData(imgMsg);
	}
	
	/**
	 * 获取自动回复 图文 消息
	 * @param msg
	 * @param items
	 * @return
	 */
	public static WeiXinReplyMsg getImgTextMsg(WeiXinReceiveMsg msg , List<WeiXinReplyImgTextMsg.Item> items){
		WeiXinReplyMsg replyMsg = new WeiXinReplyMsg();
		WeiXinReplyImgTextMsg imgTextMsg = new WeiXinReplyImgTextMsg();
		imgTextMsg.setItems(items);
		return replyMsg
				.setCreateTime(System.currentTimeMillis()/1000+"")
				.setToUserName(msg.getFromUserName())
				.setFromUserName(msg.getToUserName())
				.setMsgType(WeiXinMsgType.MSG_TYPE_NEWS)
				.setArticleCount(items.size() + "")
				.setData(imgTextMsg);
	}
	
	/**
	 * 获取视频回复消息
	 * @param msg 原消息
	 * @param title 视频名称
	 * @param desc 视频描述
	 * @param mediaId mediaId
	 * @return WeiXinReplyMsg
	 */
	public static WeiXinReplyMsg getVideoMsg(WeiXinReceiveMsg msg , String title , String desc , String mediaId){
		WeiXinReplyMsg replyMsg = new WeiXinReplyMsg();
		WeiXinReplyVideoMsg videoMsg = new WeiXinReplyVideoMsg()
				.setDescription(desc)
				.setMediaId(mediaId)
				.setTitle(title);
		
		return replyMsg
				.setCreateTime(System.currentTimeMillis()/1000+"")
				.setToUserName(msg.getFromUserName())
				.setFromUserName(msg.getToUserName())
				.setMsgType(WeiXinMsgType.MSG_TYPE_NEWS)
				.setData(videoMsg);
	}
	
	
	/**
	 * 获取自动回复 语音 信息
	 * @param msg 原消息
	 * @param mediaId mediaId
	 * @return
	 */
	public static WeiXinReplyMsg getVoiceMsg(WeiXinReceiveMsg msg , String mediaId){
		WeiXinReplyMsg replyMsg = new WeiXinReplyMsg();
		WeiXinReplyVoiceMsg voiceMsg = new WeiXinReplyVoiceMsg()
				.setMediaId(mediaId);
		
		return replyMsg
				.setCreateTime(System.currentTimeMillis()/1000+"")
				.setToUserName(msg.getFromUserName())
				.setFromUserName(msg.getToUserName())
				.setMsgType(WeiXinMsgType.MSG_TYPE_NEWS)
				.setData(voiceMsg);
	}
	
	/**
	 * 获取自动回复 音乐 信息
	 * @param msg 原消息
	 * @param title 标题
	 * @param desc 描述
	 * @param musicUrl 音乐url
	 * @param hqMusicUrl 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 * @param mediaId 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
	 * @return WeiXinReplyMsg
	 */
	public static WeiXinReplyMsg getMusicMsg(WeiXinReceiveMsg msg , String title
			, String desc , String musicUrl ,String hqMusicUrl
			, String mediaId){
		WeiXinReplyMsg replyMsg = new WeiXinReplyMsg();
		WeiXinReplyMusicMsg musicMsg = new WeiXinReplyMusicMsg()
				.setTitle(title)
				.setMusicUrl(musicUrl)
				.sethQMusicUrl(hqMusicUrl)
				.setDescription(desc)
				.setThumbMediaId(mediaId);
		
		return replyMsg
				.setCreateTime(System.currentTimeMillis()/1000+"")
				.setToUserName(msg.getFromUserName())
				.setFromUserName(msg.getToUserName())
				.setMsgType(WeiXinMsgType.MSG_TYPE_NEWS)
				.setData(musicMsg);
	}
	
	/**
	 * 获取默认回复消息
	 * @param msg 原消息
	 * @return WeiXinReplyMsg
	 */
	public static WeiXinReplyMsg getReplyDefaultMsg(WeiXinReceiveMsg msg){
		WeiXinReplyMsg replyMsg = new WeiXinReplyMsg();
		return replyMsg
				.setCreateTime(System.currentTimeMillis()/1000+"")
				.setToUserName(msg.getFromUserName())
				.setFromUserName(msg.getToUserName())
				.setMsgType(WeiXinMsgType.MSG_TYPE_TEXT)
				.setContent("系统繁忙 , 请稍后再试!");
	}
	
	/**
	 * 获取奖品不够回复
	 * @param msg 原Msg
	 * @return  WeiXinReplyMsg
	 */
	public static WeiXinReplyMsg getPrizeNotEnoughMsg(WeiXinReceiveMsg msg) {
		WeiXinReplyMsg replyMsg = new WeiXinReplyMsg();
		return replyMsg
				.setCreateTime(System.currentTimeMillis()/1000+"")
				.setToUserName(msg.getFromUserName())
				.setFromUserName(msg.getToUserName())
				.setMsgType(WeiXinMsgType.MSG_TYPE_TEXT)
				.setContent("奖品不够");
	}
	
	/**
	 *  未中奖回复
	 * @param msg 原Msg
	 * @return WeiXinReplyMsg
	 */
	public static WeiXinReplyMsg getDrawDefaultMsg(WeiXinReceiveMsg msg){
		WeiXinReplyMsg replyMsg = new WeiXinReplyMsg();
		return replyMsg
				.setCreateTime(System.currentTimeMillis()/1000+"")
				.setToUserName(msg.getFromUserName())
				.setFromUserName(msg.getToUserName())
				.setMsgType(WeiXinMsgType.MSG_TYPE_TEXT)
				.setContent("再接再厉");
	}
	
}
