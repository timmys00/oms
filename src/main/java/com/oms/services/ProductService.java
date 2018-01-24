package com.oms.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.domain.Product;
import com.oms.repositories.ProductRepository;

@Service
public class ProductService implements IProductService {
	
	@Autowired 
    private ProductRepository productRepository; 

	@Override 
    public void addProduct(Product product) { 
       productRepository.addProduct(product); 
    } 

    @Override 
    public void updateAllStock() { 
       List<Product> allProducts = productRepository.getAllProducts(); 

       for(Product product : allProducts) 
       { 
          if(product.getUnitsInStock()<500) 
             productRepository.updateStock(product.getProductId(), product.getUnitsInStock()+1000); 
       } 
    }

	@Override
	public List<Product> getAll() {
		int count = productRepository.getAllProducts().size();
		return count > 0 ? productRepository.getAllProducts() : new ArrayList<Product>();
	} 
	
	@Override 
    public Product getById(String productID) { 
       return productRepository.getProductById(productID); 
    } 

	@Override
	public List<Product> getByCategory(String category) { 
        return productRepository.getProductsByCategory(category); 
    } 
	
	@Override
	public List<Product> getByFilter(Map<String, List<String>> filterParams) { 
		return productRepository.getProductsByFilter(filterParams); 
	} 

}
