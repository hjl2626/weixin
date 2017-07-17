package com.hjl.message.bean.weixin;

import javax.xml.bind.annotation.*;

/**
 * Created by hjl on 2017/2/27.
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeiXinReplyMsg {
	
	/**
	 * 接收方帐号（收到的OpenID）
	 */
	@XmlElement(name = "ToUserName")
	private String toUserName;
	
	/**
	 * 开发者微信号
	 */
	@XmlElement(name = "FromUserName")
	private String fromUserName;
	
	/**
	 * 消息创建时间 （整型）
	 */
	@XmlElement(name = "CreateTime")
	private String createTime;
	
	/**
	 * text image  voice  video  music
	 */
	@XmlElement(name = "MsgType")
	private String msgType;
	
	/**
	 * 文本消息内容
	 */
	@XmlElement(name = "Content")
	private String content;
	
	/**
	 * 图文消息个数，限制为8条以内
	 */
	@XmlElement(name = "ArticleCount")
	private String articleCount;
	
	
	@XmlElements({
			@XmlElement(name = "text" , type = WeiXinReplyTextMsg.class),
			@XmlElement(name = "Image" , type = WeiXinReplyImgMsg.class),
			@XmlElement(name = "Voice" , type = WeiXinReplyVoiceMsg.class),
			@XmlElement(name = "Video" , type = WeiXinReplyVideoMsg.class),
			@XmlElement(name = "Music" , type = WeiXinReplyMusicMsg.class),
			@XmlElement(name = "Articles" , type = WeiXinReplyImgTextMsg.class)
		})
	private WeiXinReplyDetailMsg data;
	
	
	public String getToUserName() {
		return toUserName;
	}
	
	public WeiXinReplyMsg setToUserName(String toUserName) {
		this.toUserName = toUserName;
		return this;
	}
	
	public String getFromUserName() {
		return fromUserName;
	}
	
	public WeiXinReplyMsg setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
		return this;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public WeiXinReplyMsg setCreateTime(String createTime) {
		this.createTime = createTime;
		return this;
	}
	
	public String getMsgType() {
		return msgType;
	}
	
	public WeiXinReplyMsg setMsgType(String msgType) {
		this.msgType = msgType;
		return this;
	}
	
	
	public WeiXinReplyDetailMsg getData() {
		return data;
	}
	
	public WeiXinReplyMsg setData(WeiXinReplyDetailMsg data) {
		this.data = data;
		return this;
	}
	
	public String getContent() {
		return content;
	}
	
	public WeiXinReplyMsg setContent(String content) {
		this.content = content;
		return this;
	}
	
	public String getArticleCount() {
		return articleCount;
	}
	
	public WeiXinReplyMsg setArticleCount(String articleCount) {
		this.articleCount = articleCount;
		return this;
	}
	
	@Override
	public String toString() {
		return "WeiXinReplyMsg{" +
				"toUserName='" + toUserName + '\'' +
				", fromUserName='" + fromUserName + '\'' +
				", createTime='" + createTime + '\'' +
				", msgType='" + msgType + '\'' +
				", content='" + content + '\'' +
				", data=" + data +
				'}';
	}
}
