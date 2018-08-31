package com.wps;

import java.util.List;

public class DatatableResponse {

	public Long recordsTotal;
	public List<? extends Object> data;
	public Long recordsFiltered;
	public String msg;
	
	public DatatableResponse(Long recordsTotal, List<? extends Object> data, Long recordsFiltered) {
		super();
		
		this.recordsTotal = recordsTotal;
		this.data = data;
		this.recordsFiltered = recordsFiltered;
	}
	
	public DatatableResponse(Long recordsTotal, List<? extends Object> data, Long recordsFiltered, String msg) {
		super();
		
		this.recordsTotal = recordsTotal;
		this.data = data;
		this.recordsFiltered = recordsFiltered;
		this.msg = msg;
	}
}
