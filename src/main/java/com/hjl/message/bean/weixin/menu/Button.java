package com.hjl.message.bean.weixin.menu;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by hjl on 2017/2/28.
 */
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY,
		property = "type",
		defaultImpl = ComplexButton.class ,
		visible = true
)
@JsonSubTypes({
		@JsonSubTypes.Type(value = ViewButton.class ,name = "view"),
		@JsonSubTypes.Type(value = ClickButton.class ,name = "click")
})
public class Button {
	
	/**
	 * 菜单标题，不超过16个字节，子菜单不超过60个字节
	 */
	private String name;
	
	public String getName() {
		return name;
	}
	
	public Button setName(String name) {
		this.name = name;
		return this;
	}
	
	@Override
	public String toString() {
		return "Button{" +
				"name='" + name + '\'' +
				'}';
	}
}
