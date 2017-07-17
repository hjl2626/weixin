package com.hjl.message.bean.tuling;

/**
 * Created by hjl on 2017/1/11.
 */
public class ApiRequest {

	private String key;

	private String userid;

	private String info;

	public String getKey() {
		return key;
	}

	public ApiRequest setKey(String key) {
		this.key = key;
		return this;
	}

	public String getUserid() {
		return userid;
	}

	public ApiRequest setUserid(String userid) {
		this.userid = userid;
		return this;
	}

	public String getInfo() {
		return info;
	}

	public ApiRequest setInfo(String info) {
		this.info = info;
		return this;
	}
}
