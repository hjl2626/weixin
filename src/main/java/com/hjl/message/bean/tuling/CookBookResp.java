package com.hjl.message.bean.tuling;

import java.util.List;

/**
 * Created by hjl on 2017/1/11.
 */
public class CookBookResp extends BaseResp {

	private List<CookBook> list;

	public List<CookBook> getList() {
		return list;
	}

	public CookBookResp setList(List<CookBook> list) {
		this.list = list;
		return this;
	}
}
