package com.oms.repositories;

import com.oms.domain.Cart; 
import com.oms.viewmodels.CartViewModel; 


public interface ICartRepository {

       void create(CartViewModel cartDto); 
  
       Cart read(String id); 
  
       void update(String id, CartViewModel cartDto); 
  
       void delete(String id); 

       void addItem(String cartId, String productId); 

       void removeItem(String cartId, String productId); 
    } 