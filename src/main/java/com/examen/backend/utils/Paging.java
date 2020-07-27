package com.examen.backend.utils;

public class Paging {

	private Integer page;
	private Integer size;
	private Integer total;
	
	public Paging(Integer page, Integer size, Integer total) {
		this.page = page;
		this.size = size;
		this.total = total;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getSize() {
		return size;
	}

	public Integer getTotal() {
		return total;
	}
	
	
	
}
