package com.method.userservice.util;

import java.util.List;

public class Response<T> {
	private T responseObject;
	private List<T> responseList;
	private Long totalRecords;
	private Integer currentPage;
	private Integer totalPages;
	public T getResponseObject() {
		return responseObject;
	}
	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}
	public List<T> getResponseList() {
		return responseList;
	}
	public void setResponseList(List<T> responseList) {
		this.responseList = responseList;
	}
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	
}
