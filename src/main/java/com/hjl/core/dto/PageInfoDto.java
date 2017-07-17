package com.hjl.core.dto;

import java.io.Serializable;

/**
 * Created by hjl on 2016/12/21.
 */
public class PageInfoDto implements Serializable{

	private static final long serialVersionUID = 750719126020716301L;
	private Integer pageNum;

	private Integer pageSize;

	private Long total;

	private Integer pages;

	private Integer prePage;

	private Integer nextPage;

	private Boolean hasPreviousPage;

	private Boolean hasNextPage;

	private Integer size;

	public Integer getSize() {
		return size;
	}

	public PageInfoDto setSize(Integer size) {
		this.size = size;
		return this;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public PageInfoDto setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
		return this;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public PageInfoDto setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public Long getTotal() {
		return total;
	}

	public PageInfoDto setTotal(Long total) {
		this.total = total;
		return this;
	}

	public Integer getPages() {
		return pages;
	}

	public PageInfoDto setPages(Integer pages) {
		this.pages = pages;
		return this;
	}

	public Integer getPrePage() {
		return prePage;
	}

	public PageInfoDto setPrePage(Integer prePage) {
		this.prePage = prePage;
		return this;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public PageInfoDto setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
		return this;
	}

	public Boolean getHasPreviousPage() {
		return hasPreviousPage;
	}

	public PageInfoDto setHasPreviousPage(Boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
		return this;
	}

	public Boolean getHasNextPage() {
		return hasNextPage;
	}

	public PageInfoDto setHasNextPage(Boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
		return this;
	}
}
