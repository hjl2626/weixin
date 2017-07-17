package com.hjl.message.bean.tuling;

/**
 * Created by hjl on 2017/1/11.
 */
public class News {

	//标题
	String article;

	// 来源
	String source;

	// 图片
	String icon;

	// 详细地址
	String detailurl;

	public String getArticle() {
		return article;
	}

	public News setArticle(String article) {
		this.article = article;
		return this;
	}

	public String getSource() {
		return source;
	}

	public News setSource(String source) {
		this.source = source;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public News setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public String getDetailurl() {
		return detailurl;
	}

	public News setDetailurl(String detailurl) {
		this.detailurl = detailurl;
		return this;
	}
}
