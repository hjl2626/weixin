package com.hjl.core.exception;

/**
 * Created by hjl on 2016/12/20.
 */
public class BizException extends Exception{


	private String code;

	private String msg;

	private Object data;


	public BizException(String code, String msg){
		super();
		this.msg = msg;
		this.code = code;
	}

	public BizException(String code, String msg ,Object data){
		super();
		this.msg = msg;
		this.code = code;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public BizException setCode(String code) {
		this.code = code;
		return this;
	}

	public Object getData() {
		return data;
	}

	public BizException setData(Object data) {
		this.data = data;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public BizException setMsg(String msg) {
		this.msg = msg;
		return this;
	}
}
