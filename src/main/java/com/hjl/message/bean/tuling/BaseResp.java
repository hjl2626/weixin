package com.hjl.message.bean.tuling;

/**
 * Created by hjl on 2017/1/11.
 */
public class BaseResp {

	private String code;

	private String text;

	public String getCode() {
		return code;
	}

	public BaseResp setCode(String code) {
		this.code = code;
		return this;
	}

	public String getText() {
		return text;
	}

	public BaseResp setText(String text) {
		this.text = text;
		return this;
	}
}
