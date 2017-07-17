package com.hjl.core.message.bean.tuling;

import java.util.List;

/**
 * Created by hjl on 2017/1/11.
 */
public class NewsResp extends BaseResp {

	private List<News> list;

	public List<News> getList() {
		return list;
	}

	public NewsResp setList(List<News> list) {
		this.list = list;
		return this;
	}
}
