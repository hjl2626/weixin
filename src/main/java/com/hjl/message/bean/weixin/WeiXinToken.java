package com.hjl.message.bean.weixin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hjl on 2017/1/10.
 */
public class WeiXinToken {

	@SerializedName("access_token")
	@JsonProperty("access_token")
	private String accessToken;


	@JsonProperty("expires_in")
	@SerializedName("expires_in")
	private String expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public WeiXinToken setAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public WeiXinToken setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}
}
