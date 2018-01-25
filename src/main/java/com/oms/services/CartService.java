package com.oms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.domain.Cart;
import com.oms.repositories.CartRepository;
import com.oms.viewmodels.CartViewModel;

@Service
public class CartService implements ICartService {

	@Autowired
	private CartRepository cartRepository;

	public void create(CartViewModel cartDto) {
		cartRepository.create(cartDto);
	}

	@Override
	public Cart read(String id) {
		return cartRepository.read(id);
	}

	@Override
	public void update(String id, CartViewModel cartDto) {
		cartRepository.update(id, cartDto);
	}

	@Override
	public void delete(String id) {
		cartRepository.delete(id);
	}

	@Override
	public void addItem(String cartId, String productId) {
		cartRepository.addItem(cartId, productId);
	}

	@Override
	public void removeItem(String cartId, String productId) {
		cartRepository.removeItem(cartId, productId);
	}
}
