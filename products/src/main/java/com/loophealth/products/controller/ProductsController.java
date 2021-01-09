package com.loophealth.products.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.loophealth.products.model.ProductModel;
import com.loophealth.products.model.ProductsFilteredRequestModel;
import com.loophealth.products.services.ProductsService;

@RestController
@RequestMapping("/loophealth")
public class ProductsController {
	@Autowired
	private ProductsService productsService;
	
	@GetMapping(path = "/fetch/all-products/v1", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String,Object>> fetchAllProductsV1() {
		HashMap<String,Object> productsList = productsService.fetchAllProductsV1();
		if(productsList.get("statusCode").equals("200")) {
			return new ResponseEntity<HashMap<String,Object>>(productsList,HttpStatus.OK);
		}else {
			return new ResponseEntity<HashMap<String,Object>>(productsList,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/fetch/all-products/v2", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String,Object>> fetchAllProductsV2() {
		HashMap<String,Object> productsList = productsService.fetchAllProductsV2();
		if(productsList.get("statusCode").equals("200")) {
			return new ResponseEntity<HashMap<String,Object>>(productsList,HttpStatus.OK);
		}else {
			return new ResponseEntity<HashMap<String,Object>>(productsList,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/fetch/all-filters/v1", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String,Object>> fetchFilters() {
		HashMap<String,Object> filtersList = productsService.fetchFilters();
		if(filtersList.get("statusCode").equals("200")) {
			return new ResponseEntity<HashMap<String,Object>>(filtersList,HttpStatus.OK);
		}else {
			return new ResponseEntity<HashMap<String,Object>>(filtersList,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "/fetch/all-filtered-products/v1", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String,Object>> fetchProductsWithfilter(@RequestBody ProductsFilteredRequestModel request) {
		HashMap<String,Object> filteredProductsList = productsService.fetchProductsWithfilter(request);
		if(filteredProductsList.get("statusCode").equals("200")) {
			return new ResponseEntity<HashMap<String,Object>>(filteredProductsList,HttpStatus.OK);
		}else {
			return new ResponseEntity<HashMap<String,Object>>(filteredProductsList,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/fetch/products/v1", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String,Object>> fetchByCaetgory(@RequestParam(required=false,defaultValue = "") String category,
    		@RequestParam(required=false,defaultValue = "") String brand) {
		HashMap<String,Object> filteredProductsList = productsService.fetchByCategory(category,brand);
		if(filteredProductsList.get("statusCode").equals("200")) {
			return new ResponseEntity<HashMap<String,Object>>(filteredProductsList,HttpStatus.OK);
		}else {
			return new ResponseEntity<HashMap<String,Object>>(filteredProductsList,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
