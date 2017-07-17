package com.hjl.message.bean;

/**
 * Created by hjl on 2016/12/28.
 */
public class JsonDataResult<T> extends JsonResult {

	private T data;

	public T getData() {
		return data;
	}

	public JsonDataResult setData(T data) {
		this.data = data;
		return this;
	}
}
