package com.hjl.core.message.bean.weixin.replyMsg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hjl on 2017/2/27.
 */
@XmlRootElement(name = "Music")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeiXinReplyMusicMsg extends WeiXinReplyDetailMsg{
	
	/*
		<xml>
			<ToUserName><![CDATA[toUser]]></ToUserName>
			<FromUserName><![CDATA[fromUser]]></FromUserName>
			<CreateTime>12345678</CreateTime>
			<MsgType><![CDATA[music]]></MsgType>
			<Music>
				<Title><![CDATA[TITLE]]></Title>
				<Description><![CDATA[DESCRIPTION]]></Description>
				<MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
				<HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
				<ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>
			</Music>
		</xml>
	 */
	
	/**
	 * 音乐标题
	 */
	@XmlElement(name = "Title")
	private String title;
	
	/**
	 * 音乐描述。
	 */
	@XmlElement(name = "Description")
	private String description;
	
	/**
	 * 音乐url。
	 */
	@XmlElement(name = "MusicUrl")
	private String musicUrl;
	
	/**
	 * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 */
	@XmlElement(name = "HQMusicUrl")
	private String hQMusicUrl;
	
	/**
	 * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
	 */
	@XmlElement(name = "ThumbMediaId")
	private String thumbMediaId;
	
	public String getTitle() {
		return title;
	}
	
	public WeiXinReplyMusicMsg setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public String getDescription() {
		return description;
	}
	
	public WeiXinReplyMusicMsg setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public String getMusicUrl() {
		return musicUrl;
	}
	
	public WeiXinReplyMusicMsg setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
		return this;
	}
	
	public String gethQMusicUrl() {
		return hQMusicUrl;
	}
	
	public WeiXinReplyMusicMsg sethQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = hQMusicUrl;
		return this;
	}
	
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	
	public WeiXinReplyMusicMsg setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
		return this;
	}
	
	@Override
	public String toString() {
		return "WeiXinReplyMusicMsg{" +
				"title='" + title + '\'' +
				", description='" + description + '\'' +
				", musicUrl='" + musicUrl + '\'' +
				", hQMusicUrl='" + hQMusicUrl + '\'' +
				", thumbMediaId='" + thumbMediaId + '\'' +
				'}';
	}
}
