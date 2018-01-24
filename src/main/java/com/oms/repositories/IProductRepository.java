package com.oms.repositories;

import java.util.List;
import java.util.Map;

import com.oms.domain.Product; 

public interface IProductRepository { 

   List<Product> getAllProducts(); 
   Product getProductById(String productID); 
   List<Product> getProductsByCategory(String category); 
   List<Product> getProductsByFilter(Map<String, List<String>> filterParams); 
   void updateStock(String productId, long noOfUnits); 
   void addProduct(Product product); 
} 
