package com.hjl.base.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hjl on 2017/2/27.
 */
public final class WeiXinMsgType {
	
	/**
	 * 文本消息
	 */
	public final static String MSG_TYPE_TEXT = "text";
	/**
	 * 图片消息
	 */
	public final static String MSG_TYPE_IMAGE = "image";
	/**
	 * 语音消息
	 */
	public final static String MSG_TYPE_VOICE = "voice";
	/**
	 * 视频消息
	 */
	public final static String MSG_TYPE_VIDEO = "video";
	/**
	 * 短视频消息
	 */
	public final static String MSG_TYPE_SHORTVIDEO = "shortvideo";
	
	/**
	 * 位置消息
	 */
	public final static String MSG_TYPE_LOCATION = "location";
	
	/**
	 * 链接消息
	 */
	public final static String MSG_TYPE_LINK = "link";
	
	/**
	 * 音乐消息
	 */
	public final static String MSG_TYPE_MUSIC = "music";
	
	/**
	 * 图文消息
	 */
	public final static String MSG_TYPE_NEWS = "news";
	
	/**
	 * 事件消息
	 */
	public final static String MSG_TYPE_EVENT = "event";
	
	/**
	 * 图文消息
	 */
	public final static String MSG_TYPE_MP_NEWS = "mpnews";
	/**
	 * 群发视频消息
	 */
	public final static String MSG_TYPE_MP_VIDEO = "mpvideo";
	/**
	 * 微信卡券消息
	 */
	public final static String MSG_TYPE_WXCARD = "wxcard";
	
	
	/**
	 * 微信消息与描述映射
	 */
	private static final Map<String, String> WECHAT_MSG_TYPE = new HashMap<String, String>();
	
	static {
		WECHAT_MSG_TYPE.put(MSG_TYPE_TEXT, "被动回复文本消息");
		WECHAT_MSG_TYPE.put(MSG_TYPE_IMAGE, "被动回复图片消息");
		WECHAT_MSG_TYPE.put(MSG_TYPE_VOICE, "被动回复语音消息");
		WECHAT_MSG_TYPE.put(MSG_TYPE_VIDEO, "被动回复视频消息");
		WECHAT_MSG_TYPE.put(MSG_TYPE_SHORTVIDEO, "短视频消息");
		WECHAT_MSG_TYPE.put(MSG_TYPE_LOCATION, "地址消息");
		WECHAT_MSG_TYPE.put(MSG_TYPE_LINK, "链接消息");
		WECHAT_MSG_TYPE.put(MSG_TYPE_MUSIC, "被动回复音乐消息");
		WECHAT_MSG_TYPE.put(MSG_TYPE_NEWS, "被动回复图文消息");
		
		WECHAT_MSG_TYPE.put(MSG_TYPE_EVENT, "事件消息");
		WECHAT_MSG_TYPE.put(MSG_TYPE_MP_NEWS, "群发图文消息");
		
		WECHAT_MSG_TYPE.put(MSG_TYPE_MP_VIDEO, "群发视频消息");
		WECHAT_MSG_TYPE.put(MSG_TYPE_WXCARD, "群发微信卡券消息");
	}
	
	/**
	 * 获取消息类型的描述
	 * @param type 类型
	 * @return 描述
	 */
	public static String getMsgType(String type) {
		return WECHAT_MSG_TYPE.get(type);
	}
	
}
