package com.hjl.core.message.bean.weixin.replyMsg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hjl on 2017/2/27.
 */
@XmlRootElement(name = "Image")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeiXinReplyImgMsg extends WeiXinReplyDetailMsg{
	/*
	 <xml>
		 <ToUserName><![CDATA[toUser]]></ToUserName>
		 <FromUserName><![CDATA[fromUser]]></FromUserName>
		 <CreateTime>12345678</CreateTime>
		 <MsgType><![CDATA[image]]></MsgType>
		 <Image>
		    <MediaId><![CDATA[media_id]]></MediaId>
		 </Image>
	 </xml>
	 */
	
	
	/**
	 * 通过素材管理中的接口上传多媒体文件，得到的id。
	 */
	@XmlElement(name = "MediaId")
	private String mediaId;
	
	public String getMediaId() {
		return mediaId;
	}
	
	public WeiXinReplyImgMsg setMediaId(String mediaId) {
		this.mediaId = mediaId;
		return this;
	}
	
	@Override
	public String toString() {
		return "WeiXinReplyImgMsg{" +
				"mediaId='" + mediaId + '\'' +
				'}';
	}
}
