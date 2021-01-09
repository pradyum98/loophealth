package com.loophealth.products.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loophealth.products.model.FilterModel;
import com.loophealth.products.model.ProductModel;
import com.loophealth.products.model.ProductsFilteredRequestModel;

@Service
public class ProductsServiceImpl implements ProductsService{
	@Autowired
    MessageSource messageSource;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public HashMap<String,Object> fetchAllProductsV1() {
		String uri = messageSource.getMessage("datadump.products.url",  null, LocaleContextHolder.getLocale());
		HttpHeaders headers = new HttpHeaders();
		String responseOutput = "";
		HashMap<String,Object> finalResponse = new HashMap<String,Object>();
		ArrayList<ProductModel> allProducts = new ArrayList<ProductModel>();
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(headers);
		try {
			ResponseEntity<String> output = restTemplate.exchange(uri, HttpMethod.GET,
					entity, String.class);
			responseOutput = output.getBody();
		} catch (Exception e) {
			finalResponse.put("statusCode", "500");
			finalResponse.put("statusMessage", "There was some error in fetching the products.");
			finalResponse.put("version", "1");
			return finalResponse;
		}
		Map<String, Object> responseMap = new Gson().fromJson(responseOutput,
				new TypeToken<Map<String, Object>>() {
		}.getType());
		ArrayList<Object> productsList = (ArrayList<Object>)responseMap.get("products");
		for(Object product : productsList) {
			Map<String,Object> productData = (Map<String,Object>)product;
			ProductModel newProduct = new ProductModel();
			newProduct.setProduct(productData.get("product")!=null?productData.get("product").toString():"");
			newProduct.setProductId(productData.get("productId")!=null?productData.get("productId").toString():"");
			newProduct.setProductName(productData.get("productName")!=null?productData.get("productName").toString():"");
			newProduct.setBrand(productData.get("brand")!=null?productData.get("brand").toString():"");
			newProduct.setRating(productData.get("rating")!=null?Double.valueOf(productData.get("rating").toString()):0);
			newProduct.setRatingCount(productData.get("ratingCount")!=null?Double.valueOf(productData.get("ratingCount").toString()):0);
			newProduct.setDiscount(productData.get("discount")!=null?Double.valueOf(productData.get("discount").toString()):0);
			newProduct.setMrp(productData.get("mrp")!=null?Double.valueOf(productData.get("mrp").toString()):0);
			newProduct.setPrice(productData.get("price")!=null?Double.valueOf(productData.get("price").toString()):0);
			newProduct.setPrimaryColour(productData.get("primaryColour")!=null?productData.get("primaryColour").toString():"");
			newProduct.setGender(productData.get("gender")!=null?productData.get("gender").toString():"");
			allProducts.add(newProduct);
		}
		finalResponse.put("statusCode", "200");
		finalResponse.put("statusMessage", "Products Fetched Successfully");
		finalResponse.put("version", "1");
		finalResponse.put("products", allProducts);
		return finalResponse;
	}
	
	@Override
	public HashMap<String,Object> fetchAllProductsV2() {
		String uri = messageSource.getMessage("datadump.products.url",  null, LocaleContextHolder.getLocale());
		HttpHeaders headers = new HttpHeaders();
		String responseOutput = "";
		HashMap<String,Object> finalResponse = new HashMap<String,Object>();
		ArrayList<Object> allProducts = new ArrayList<Object>();		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(headers);
		try {
			ResponseEntity<String> output = restTemplate.exchange(uri, HttpMethod.GET,
					entity, String.class);
			responseOutput = output.getBody();
		} catch (Exception e) {
			finalResponse.put("statusCode", "500");
			finalResponse.put("statusMessage", "There was some error in fetching the products.");
			finalResponse.put("version", "1");
			return finalResponse;
		}
		Map<String, Object> responseMap = new Gson().fromJson(responseOutput,
				new TypeToken<Map<String, Object>>() {
		}.getType());
		
		allProducts = (ArrayList<Object>)responseMap.get("products");
		
		finalResponse.put("statusCode", "200");
		finalResponse.put("statusMessage", "Products Fetched Successfully");
		finalResponse.put("version", "1");
		finalResponse.put("products", allProducts);
		return finalResponse;
	}
	
	@Override
	public HashMap<String,Object> fetchFilters() {
		String uri = messageSource.getMessage("datadump.products.url",  null, LocaleContextHolder.getLocale());
		HttpHeaders headers = new HttpHeaders();
		String responseOutput = "";
		HashMap<String,Object> finalResponse = new HashMap<String,Object>();
		ArrayList<HashMap<String,Object>> allFilters = new ArrayList<HashMap<String,Object>>();		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(headers);
		try {
			ResponseEntity<String> output = restTemplate.exchange(uri, HttpMethod.GET,
					entity, String.class);
			responseOutput = output.getBody();
		} catch (Exception e) {
			finalResponse.put("statusCode", "500");
			finalResponse.put("statusMessage", "There was some error in fetching the products.");
			finalResponse.put("version", "1");
			return finalResponse;
		}
		Map<String, Object> responseMap = new Gson().fromJson(responseOutput,
				new TypeToken<Map<String, Object>>() {
		}.getType());
		
		ArrayList<Object> productsList = (ArrayList<Object>)responseMap.get("products");
		FilterModel filter = new FilterModel();
		ArrayList<String> brand = new ArrayList<String>();
		ArrayList<String> gender = new ArrayList<String>();
		ArrayList<String> primaryColour = new ArrayList<String>();
		ArrayList<String> category = new ArrayList<String>();
		ArrayList<String> season = new ArrayList<String>();
		ArrayList<String> year = new ArrayList<String>();

		for(Object product : productsList) {
			Map<String,Object> productData = (Map<String,Object>)product;
			if(productData.get("brand")!=null) {
				if(!brand.contains(productData.get("brand"))) {
					brand.add(productData.get("brand").toString());
				}
			}
			if(productData.get("gender")!=null) {
				if(!gender.contains(productData.get("gender"))) {
					gender.add(productData.get("gender").toString());
				}
			}
			if(productData.get("primaryColour")!=null) {
				if(!primaryColour.contains(productData.get("primaryColour"))) {
					primaryColour.add(productData.get("primaryColour").toString());
				}
			}
			if(productData.get("category")!=null) {
				if(!category.contains(productData.get("category"))) {
					category.add(productData.get("category").toString());
				}
			}
			if(productData.get("season")!=null) {
				if(!season.contains(productData.get("season"))) {
					season.add(productData.get("season").toString());
				}
			}
			if(productData.get("year")!=null) {
				if(!year.contains(productData.get("year"))) {
					year.add(productData.get("year").toString());
				}
			}
		}
		filter.setBrand(brand);
		filter.setGender(gender);
		filter.setPrimaryColour(primaryColour);
		filter.setCategory(category);
		filter.setSeason(season);
		filter.setYear(year);

		finalResponse.put("statusCode", "200");
		finalResponse.put("statusMessage", "Product Filters Fetched Successfully");
		finalResponse.put("version", "1");
		finalResponse.put("filters", filter);
		
		return finalResponse;
	}
	
	@Override
	public HashMap<String,Object> fetchProductsWithfilter(ProductsFilteredRequestModel request) {
		String uri = messageSource.getMessage("datadump.products.url",  null, LocaleContextHolder.getLocale());
		HttpHeaders headers = new HttpHeaders();
		String responseOutput = "";
		HashMap<String,Object> finalResponse = new HashMap<String,Object>();
		ArrayList<Map<String,Object>> filteredProducts = new ArrayList<Map<String,Object>>();		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(headers);
		try {
			ResponseEntity<String> output = restTemplate.exchange(uri, HttpMethod.GET,
					entity, String.class);
			responseOutput = output.getBody();
		} catch (Exception e) {
			finalResponse.put("statusCode", "500");
			finalResponse.put("statusMessage", "There was some error in fetching the products.");
			finalResponse.put("version", "1");
			return finalResponse;
		}
		Map<String, Object> responseMap = new Gson().fromJson(responseOutput,
				new TypeToken<Map<String, Object>>() {
		}.getType());
		
		ArrayList<Object> productsList = (ArrayList<Object>)responseMap.get("products");
		Map<String,Object> search =  request.getFilter();
		for(Object product : productsList) {
			boolean filter = true;
			Map<String,Object> productData = (Map<String,Object>)product;
			for(Map.Entry<String,Object> keyValuePair: search.entrySet()) {
				if(productData.containsKey(keyValuePair.getKey())) {
					if(!productData.get(keyValuePair.getKey()).equals(keyValuePair.getValue())) {
						filter = false;
						break;
					}
				}
			}
			if(filter) {
				filteredProducts.add(productData);
			}
		}
		
		finalResponse.put("statusCode", "200");
		finalResponse.put("statusMessage", "Filtered Products Fetched Successfully");
		finalResponse.put("version", "1");
		finalResponse.put("filteredProducts", filteredProducts);
		return finalResponse;
	}
	
	@Override
	public HashMap<String,Object> fetchByCategory(String category,String brand) {
		String uri = messageSource.getMessage("datadump.products.url",  null, LocaleContextHolder.getLocale());
		HttpHeaders headers = new HttpHeaders();
		String responseOutput = "";
		HashMap<String,Object> finalResponse = new HashMap<String,Object>();
		ArrayList<Map<String,Object>> filteredProducts = new ArrayList<Map<String,Object>>();		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(headers);
		try {
			ResponseEntity<String> output = restTemplate.exchange(uri, HttpMethod.GET,
					entity, String.class);
			responseOutput = output.getBody();
		} catch (Exception e) {
			finalResponse.put("statusCode", "500");
			finalResponse.put("statusMessage", "There was some error in fetching the products.");
			finalResponse.put("version", "1");
			return finalResponse;
		}
		Map<String, Object> responseMap = new Gson().fromJson(responseOutput,
				new TypeToken<Map<String, Object>>() {
		}.getType());
		ArrayList<Object> productsList = (ArrayList<Object>)responseMap.get("products");

		for(Object product : productsList) {
			Map<String,Object> productData = (Map<String,Object>)product;
			if(!category.equals("") && !brand.equals("")) {
				if(productData.containsKey("category") && productData.containsKey("brand")) {
					if(productData.get("category").equals(category) && productData.get("brand").equals(brand)) {
						filteredProducts.add(productData);
 					}
				}
			}else if(category.equals("") && !brand.equals("")){
				if(productData.containsKey("brand")) {
					if(productData.get("brand").equals(brand)) {
						filteredProducts.add(productData);
 					}
				}
			}else {
				if(productData.containsKey("category")) {
					if(productData.get("category").equals(category)) {
						filteredProducts.add(productData);
 					}
				}
			}
		}
		finalResponse.put("statusCode", "200");
		finalResponse.put("statusMessage", "Filtered Products Fetched Successfully");
		finalResponse.put("version", "1");
		finalResponse.put("filteredProducts", filteredProducts);
		return finalResponse;
	}
}
