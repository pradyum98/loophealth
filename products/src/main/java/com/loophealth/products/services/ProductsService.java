package com.loophealth.products.services;

import java.util.ArrayList;
import java.util.HashMap;

import com.loophealth.products.model.ProductModel;
import com.loophealth.products.model.ProductsFilteredRequestModel;

public interface ProductsService {
	public HashMap<String,Object> fetchAllProductsV1();
	
	public HashMap<String,Object> fetchAllProductsV2();
	
	public HashMap<String,Object> fetchFilters();
	
	public HashMap<String,Object> fetchProductsWithfilter(ProductsFilteredRequestModel request);
	
	public HashMap<String,Object> fetchByCategory(String category,String brand);
	
}
