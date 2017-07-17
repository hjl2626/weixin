package com.hjl.message.bean.weixin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by hjl on 2017/2/27.
 */
@XmlRootElement(name = "Articles")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeiXinReplyImgTextMsg extends WeiXinReplyDetailMsg{
	
	/*
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>12345678</CreateTime>
		<MsgType><![CDATA[news]]></MsgType>
		<ArticleCount>2</ArticleCount>
		<Articles>
			<item>
				<Title><![CDATA[title1]]></Title>
				<Description><![CDATA[description1]]></Description>
				<PicUrl><![CDATA[picurl]]></PicUrl>
				<Url><![CDATA[url]]></Url>
			</item>
			<item>
				<Title><![CDATA[title]]></Title>
				<Description><![CDATA[description]]></Description>
				<PicUrl><![CDATA[picurl]]></PicUrl>
				<Url><![CDATA[url]]></Url>
			</item>
		</Articles>
	</xml>
	*/
	
	public List<Item> getItems() {
		return items;
	}
	
	public WeiXinReplyImgTextMsg setItems(List<Item> items) {
		this.items = items;
		return this;
	}
	@XmlElement(name = "item")
	private List<Item> items;
	
	@XmlRootElement(name = "item")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Item{
		
		/**
		 * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应  	图文消息标题
		 */
		@XmlElement(name = "Title")
		private String title;
		
		/**
		 * 图文消息描述
		 */
		@XmlElement(name = "Description")
		private String description;
		
		/**
		 * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
		 */
		@XmlElement(name = "PicUrl")
		private String picUrl;
		
		/**
		 * 点击图文消息跳转链接
		 */
		@XmlElement(name = "Url")
		private String url;
		
		public String getTitle() {
			return title;
		}
		
		public Item setTitle(String title) {
			this.title = title;
			return this;
		}
		
		public String getDescription() {
			return description;
		}
		
		public Item setDescription(String description) {
			this.description = description;
			return this;
		}
		
		public String getPicUrl() {
			return picUrl;
		}
		
		public Item setPicUrl(String picUrl) {
			this.picUrl = picUrl;
			return this;
		}
		
		public String getUrl() {
			return url;
		}
		
		public Item setUrl(String url) {
			this.url = url;
			return this;
		}
		
		@Override
		public String toString() {
			return "Item{" +
					"title='" + title + '\'' +
					", description='" + description + '\'' +
					", picUrl='" + picUrl + '\'' +
					", url='" + url + '\'' +
					'}';
		}
	}
	
	@Override
	public String toString() {
		return "WeiXinReplyImgTextMsg{" +
				"items=" + items +
				"} " + super.toString();
	}
}
