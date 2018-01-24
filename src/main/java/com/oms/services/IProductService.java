package com.oms.services;

import java.util.List;
import java.util.Map;

import com.oms.domain.Product;

public interface IProductService {
	List<Product> getAll();
	Product getById(String productID); 
	List<Product> getByCategory(String category); 
	List<Product> getByFilter(Map<String, List<String>> filterParams); 
	void updateAllStock(); 
	void addProduct(Product product); 

}
