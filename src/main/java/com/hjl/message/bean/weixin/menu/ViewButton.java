package com.hjl.message.bean.weixin.menu;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by hjl on 2017/2/28.
 */
@JsonTypeName(value = "view")
public class ViewButton extends Button {
	
	private String type;
	
	private String url;
	
	public String getType() {
		return type;
	}
	
	public ViewButton setType(String type) {
		this.type = type;
		return this;
	}
	
	public String getUrl() {
		return url;
	}
	
	public ViewButton setUrl(String url) {
		this.url = url;
		return this;
	}
	
	@Override
	public String toString() {
		return "ViewButton{" +
				"type='" + type + '\'' +
				", url='" + url + '\'' +
				"} " + super.toString();
	}
}
