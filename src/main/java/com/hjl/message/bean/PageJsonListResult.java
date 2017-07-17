package com.hjl.message.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.PageInfo;
import com.hjl.dto.PageInfoDto;


import java.util.List;

/**
 * Created by hjl on 2016/12/21.
 */
public class PageJsonListResult<T> extends JsonResult {

	@JsonProperty("pageInfo")
	private PageInfoDto pageInfoDto;

	private List<T> data;

	public PageJsonListResult(PageInfo pageInfo) {
		super();
		this.pageInfoDto = new PageInfoDto();
		this.pageInfoDto.setPageNum(pageInfo.getPageNum());
		this.pageInfoDto.setPageSize(pageInfo.getPageSize());
		this.pageInfoDto.setTotal(pageInfo.getTotal());
		this.pageInfoDto.setPages(pageInfo.getPages());
		this.pageInfoDto.setPrePage(pageInfo.getPrePage());
		this.pageInfoDto.setNextPage(pageInfo.getNextPage());
		this.pageInfoDto.setHasPreviousPage(pageInfo.isHasPreviousPage());
		this.pageInfoDto.setHasNextPage(pageInfo.isHasNextPage());
		this.pageInfoDto.setSize(pageInfo.getSize());
	}

	public List<T> getData() {
		return data;
	}

	public PageJsonListResult setData(List<T> data) {
		this.data = data;
		return this;
	}

	public PageInfoDto getPageInfoDto() {
		return pageInfoDto;
	}

	public PageJsonListResult setPageInfoDto(PageInfoDto pageInfoDto) {
		this.pageInfoDto = pageInfoDto;
		return this;
	}
}
