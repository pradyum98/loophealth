package com.loophealth.products.model;

import java.util.HashMap;

public class ProductsFilteredRequestModel {
	private HashMap<String,Object> filter;

	public HashMap<String, Object> getFilter() {
		return filter;
	}

	public void setFilter(HashMap<String, Object> filter) {
		this.filter = filter;
	}

}
