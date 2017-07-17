package com.hjl.core.message.bean.weixin.menu;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by hjl on 2017/2/28.
 */
@JsonTypeName(value = "click")
public class ClickButton extends Button {
	
	/**
	 * 菜单的响应动作类型
	 */
	private String type;
	
	/**
	 * 菜单KEY值，用于消息接口推送，不超过128字节  click等点击类型必须
	 */
	private String key;
	
	public String getType() {
		return type;
	}
	
	public ClickButton setType(String type) {
		this.type = type;
		return this;
	}
	
	public String getKey() {
		return key;
	}
	
	public ClickButton setKey(String key) {
		this.key = key;
		return this;
	}
	
	@Override
	public String toString() {
		return "ClickButton{" +
				"type='" + type + '\'' +
				", key='" + key + '\'' +
				"} " + super.toString();
	}
}
