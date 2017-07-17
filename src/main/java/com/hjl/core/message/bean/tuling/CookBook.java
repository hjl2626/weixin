package com.hjl.core.message.bean.tuling;

/**
 * Created by hjl on 2017/1/11.
 */
public class CookBook {


	//	菜名
	String name;

	//	菜谱信息
	String info;

	//	详情链接
	String detailurl;

	//图标
	String icon;


	public String getName() {
		return name;
	}

	public CookBook setName(String name) {
		this.name = name;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public CookBook setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public String getDetailurl() {
		return detailurl;
	}

	public CookBook setDetailurl(String detailurl) {
		this.detailurl = detailurl;
		return this;
	}

	public String getInfo() {
		return info;
	}

	public CookBook setInfo(String info) {
		this.info = info;
		return this;
	}
}
