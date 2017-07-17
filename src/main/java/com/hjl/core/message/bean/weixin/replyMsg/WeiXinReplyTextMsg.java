package com.hjl.core.message.bean.weixin.replyMsg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hjl on 2017/2/27.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "text")
public class WeiXinReplyTextMsg extends WeiXinReplyDetailMsg{
	
	/**
	 * 文本消息内容
	 */
	@XmlElement(name = "Content")
	private String content;
	
	public String getContent() {
		return content;
	}
	
	public WeiXinReplyDetailMsg setContent(String content) {
		this.content = content;
		return this;
	}
	
	@Override
	public String toString() {
		return "WeiXinReplyTextMsg{" +
				"content='" + content + '\'' +
				"} " + super.toString();
	}
}
