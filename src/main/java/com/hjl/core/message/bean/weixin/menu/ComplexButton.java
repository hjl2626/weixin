package com.hjl.core.message.bean.weixin.menu;

import java.util.Arrays;
public class ComplexButton extends Button {
	/**
	 * 二级菜单数组，个数应为1~5个
	 */
	private Button[] sub_button;
	
	public Button[] getSub_button() {
		return sub_button;
	}
	
	public ComplexButton setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
		return this;
	}
	
	@Override
	public String toString() {
		return "ComplexButton{" +
				"sub_button=" + Arrays.toString(sub_button) +
				"} " + super.toString();
	}
}
