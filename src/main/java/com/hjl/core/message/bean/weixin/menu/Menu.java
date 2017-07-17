package com.hjl.core.message.bean.weixin.menu;

import java.util.Arrays;

/**
 * Created by hjl on 2017/2/28.
 */
public class Menu {
	
	/**
	 * 一级菜单数组，个数应为1~3个
	 */
	private Button[] button;
	
	
	public Button[] getButton() {
		return button;
	}
	
	public Menu setButton(Button[] button) {
		this.button = button;
		return this;
	}
	
	@Override
	public String toString() {
		return "Menu{" +
				"button=" + Arrays.toString(button) +
				'}';
	}
}
