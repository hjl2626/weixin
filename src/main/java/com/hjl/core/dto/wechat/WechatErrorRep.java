package com.hjl.core.dto.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WechatErrorRep implements Serializable {
    
    /** **/
	private static final long serialVersionUID = 5993202393596717499L;


	private String errCode;
	
	
	private String errMsg;

	

	/**
	 * @return the errCode
	 */
	
	public String getErrCode() {
		return errCode;
	}

	/**
	 * @param errCode the errCode to set
	 */
	@JsonProperty("errcode")
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * @return the errMsg
	 */
	
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @param errMsg the errMsg to set
	 */
	@JsonProperty("errmsg")
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	
}
