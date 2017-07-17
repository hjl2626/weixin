package com.hjl.core.message.bean.weixin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hjl on 2017/1/10.
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeiXinTextMessage{
	
	
	/**
	 * 文本消息内容
	 */
	@XmlElement(name = "Content")
	private String content;



	public String getContent() {
		return content;
	}

	public WeiXinTextMessage setContent(String content) {
		this.content = content;
		return this;
	}

	@Override
	public String toString() {
		return "WeiXinTextMessage{" +
				"content='" + content + '\'' +
				"} " + super.toString();
	}
}
