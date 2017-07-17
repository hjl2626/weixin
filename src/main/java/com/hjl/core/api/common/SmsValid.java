package com.hjl.core.api.common;

import java.io.Serializable;

public class SmsValid implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6301515823332114744L;
	
	private String code ;
	private String phone ;
	private Long count ;
	private long lastTime ;

	public String getCode() {
		return code;
	}

	public SmsValid setCode(String code) {
		this.code = code;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public SmsValid setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public Long getCount() {
		return count;
	}

	public SmsValid setCount(Long count) {
		this.count = count;
		return this;
	}

	public long getLastTime() {
		return lastTime;
	}

	public SmsValid setLastTime(long lastTime) {
		this.lastTime = lastTime;
		return this;
	}
}
