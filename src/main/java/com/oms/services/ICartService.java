package com.oms.services;

import com.oms.domain.Cart;
import com.oms.viewmodels.CartViewModel;

public interface ICartService {

	void create(CartViewModel cartDto);

	Cart read(String cartId);

	void update(String cartId, CartViewModel cartDto);

	void delete(String id);

	void addItem(String cartId, String productId);

	void removeItem(String cartId, String productId);
}
