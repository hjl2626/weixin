package com.hjl.core.message.bean.weixin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hjl on 2017/2/27.
 */
@XmlRootElement(name = "Video")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeiXinReplyVideoMsg extends WeiXinReplyDetailMsg{
	
	/**
	 * <xml>
		 <ToUserName><![CDATA[toUser]]></ToUserName>
		 <FromUserName><![CDATA[fromUser]]></FromUserName>
		 <CreateTime>12345678</CreateTime>
		 <MsgType><![CDATA[video]]></MsgType>
		 <Video>
			 <MediaId><![CDATA[media_id]]></MediaId>
			 <Title><![CDATA[title]]></Title>
			 <Description><![CDATA[description]]></Description>
		 </Video>
	    </xml>
	 */
	
	/**
	 * 通过素材管理中的接口上传多媒体文件，得到的id。
	 */
	@XmlElement(name = "MediaId")
	private String mediaId;
	
	/**
	 * 视频消息的标题
	 */
	@XmlElement(name = "Title")
	private String title;
	
	/**
	 * 视频消息的描述
	 */
	@XmlElement(name = "Description")
	private String description;
	
	public String getMediaId() {
		return mediaId;
	}
	
	public WeiXinReplyVideoMsg setMediaId(String mediaId) {
		this.mediaId = mediaId;
		return this;
	}
	
	public String getTitle() {
		return title;
	}
	
	public WeiXinReplyVideoMsg setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public String getDescription() {
		return description;
	}
	
	public WeiXinReplyVideoMsg setDescription(String description) {
		this.description = description;
		return this;
	}
	
	@Override
	public String toString() {
		return "WeiXinReplyVideoMsg{" +
				"mediaId='" + mediaId + '\'' +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
