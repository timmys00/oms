package com.oms.viewmodels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartViewModel implements Serializable {

	private static final long serialVersionUID = -2017182726290898588L;

	private String id;
	private List<CartItemViewModel> cartItems;

	public CartViewModel() {}

	public CartViewModel(String id) { 
      this.id = id; 
      cartItems = new ArrayList<>(); 
   }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CartItemViewModel> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemViewModel> cartItems) {
		this.cartItems = cartItems;
	}

	public void addCartItem(CartItemViewModel cartItemDto) {
		this.cartItems.add(cartItemDto);
	}
}
