package com.hjl.core.message.bean;


import com.hjl.core.message.enums.RespCodeEnum;

/**
 * Created by hjl on 2016/12/20.
 */
public class JsonResult {

	private String code;

	private String msg;

	public String getCode() {
		return code;
	}

	public JsonResult setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public JsonResult setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public JsonResult init(){
		return init(RespCodeEnum.RC_0000.code(),RespCodeEnum.RC_0000.description());
	}

	public JsonResult init(String code ,String msg){
		this.setCode(code);
		this.setMsg(msg);
		return this;
	}

}
