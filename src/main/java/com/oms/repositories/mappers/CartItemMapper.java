package com.oms.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.oms.domain.CartItem;
import com.oms.services.ProductService;

public class CartItemMapper implements RowMapper<CartItem> {
	private ProductService productService;

	public CartItemMapper(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		CartItem cartItem = new CartItem(rs.getString("ID"));

		cartItem.setProduct(productService.getById(rs.getString("PRODUCT_ID")));
		cartItem.setQuantity(rs.getInt("QUANTITY"));

		return cartItem;
	}
}
