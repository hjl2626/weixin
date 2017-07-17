package com.hjl.core.message.bean.weixin;

import javax.xml.bind.annotation.*;

/**
 * Created by hjl on 2017/1/10.
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeiXinImgMessage {


	public WeiXinImgMessage() {
	}
	

	@XmlElement(name = "PicUrl")
	private String picUrl;     //图片链接（由系统生成）
	@XmlElement(name = "MediaId")
	private String mediaId;     //图片消息媒体id，可以调用多媒体文件下载接口拉取数据。


	public String getPicUrl() {
		return picUrl;
	}

	public WeiXinImgMessage setPicUrl(String picUrl) {
		this.picUrl = picUrl;
		return this;
	}

	public String getMediaId() {
		return mediaId;
	}

	public WeiXinImgMessage setMediaId(String mediaId) {
		this.mediaId = mediaId;
		return this;
	}


	@Override
	public String toString() {
		return "WeiXinImgMessage{" +
				"picUrl='" + picUrl + '\'' +
				", mediaId='" + mediaId + '\'' +
				"} " + super.toString();
	}
}
