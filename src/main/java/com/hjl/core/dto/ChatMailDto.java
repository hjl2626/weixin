package com.hjl.core.dto;

/**
 * Created by hjl on 2017/2/6.
 */
public class ChatMailDto {
	
	private String content;
	
	public String getContent() {
		return content;
	}
	
	public ChatMailDto setContent(String content) {
		this.content = content;
		return this;
	}
	
	@Override
	public String toString() {
		return  content;
	}
}
