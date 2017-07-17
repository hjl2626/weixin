package com.hjl.constants;

/**
 * Created by hjl on 2017/4/6.
 */
public class WeiXinEventType {
	
	/**
	 * 用户未关注时，进行关注后的事件推送
	 */
	public final static String EVENT_TYPE_SUB = "subscribe";
	
	/**
	 * 关注/取消关注事件
	 */
	public final static String EVENT_TYPE_UNSUB = "unsubscribe";
	
	/**
	 * 用户已关注时的事件推送
	 */
	public final static String EVENT_TYPE_SCAN = "SCAN";
	
	/**
	 * 用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，或在进入会话后每5秒上报一次地理位置，
	    公众号可以在公众平台网站中修改以上设置。上报地理位置时，微信会将上报地理位置事件推送到开发者填写的URL。
	 */
	public final static String EVENT_TYPE_LOCATION = "LOCATION";
	
	/**
	 * 用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。
	 * 点击菜单拉取消息时的事件推送
	 */
	public final static String EVENT_TYPE_CLICK = "CLICK";
	
	/**
	 * 点击菜单跳转链接时的事件推送
	 */
	public final static String EVENT_TYPE_VIEW = "VIEW";
	
	
}
