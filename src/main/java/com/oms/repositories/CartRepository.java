package com.oms.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oms.domain.Cart;
import com.oms.domain.CartItem;
import com.oms.domain.Product;
import com.oms.repositories.mappers.CartMapper;
import com.oms.viewmodels.CartViewModel;
import com.oms.viewmodels.CartItemViewModel;
import com.oms.services.ProductService;

@Repository
public class CartRepository implements ICartRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTempleate;

	@Autowired
	private ProductService productService;

	public void create(CartViewModel cartDto) {

		String INSERT_CART_SQL = "INSERT INTO CART(ID) VALUES (:id)";

		Map<String, Object> cartParams = new HashMap<String, Object>();
		cartParams.put("id", cartDto.getId());

		jdbcTempleate.update(INSERT_CART_SQL, cartParams);

		cartDto.getCartItems().stream().forEach(cartItemDto -> {

			Product productById = productService.getById(cartItemDto.getProductId());

			String INSERT_CART_ITEM_SQL = "INSERT INTO CART_ITEM(ID,PRODUCT_ID ,CART_ID,QUANTITY) "
					+ "VALUES (:id, :product_id, :cart_id, :quantity)";

			Map<String, Object> cartItemsParams = new HashMap<String, Object>();
			cartItemsParams.put("id", cartItemDto.getId());
			cartItemsParams.put("product_id", productById.getProductId());
			cartItemsParams.put("cart_id", cartDto.getId());
			cartItemsParams.put("quantity", cartItemDto.getQuantity());

			jdbcTempleate.update(INSERT_CART_ITEM_SQL, cartItemsParams);
		});
	}

	public Cart read(String id) {
		String SQL = "SELECT * FROM CART WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		CartMapper cartMapper = new CartMapper(jdbcTempleate, productService);

		try {
			return jdbcTempleate.queryForObject(SQL, params, cartMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void update(String id, CartViewModel cartDto) {

		List<CartItemViewModel> cartItems = cartDto.getCartItems();
		for (CartItemViewModel cartItem : cartItems) {

			String SQL = "UPDATE CART_ITEM SET QUANTITY = :quantity,  PRODUCT_ID = :productId WHERE ID = :id AND CART_ID = :cartId";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", cartItem.getId());
			params.put("quantity", cartItem.getQuantity());
			params.put("productId", cartItem.getProductId());
			params.put("cartId", id);

			jdbcTempleate.update(SQL, params);
		}
	}

	@Override
	public void delete(String id) {
		String SQL_DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE CART_ID = :id";
		String SQL_DELETE_CART = "DELETE FROM CART WHERE ID = :id";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);

		jdbcTempleate.update(SQL_DELETE_CART_ITEM, params);
		jdbcTempleate.update(SQL_DELETE_CART, params);
	}

	@Override
	public void addItem(String cartId, String productId) {

		String SQL = null;
		Cart cart = null;

		cart = read(cartId);
		if (cart == null) {
			CartItemViewModel newCartItemDto = new CartItemViewModel();
			newCartItemDto.setId(cartId + productId);
			newCartItemDto.setProductId(productId);
			newCartItemDto.setQuantity(1);

			CartViewModel newCartDto = new CartViewModel(cartId);
			newCartDto.addCartItem(newCartItemDto);
			create(newCartDto);
			return;
		}

		Map<String, Object> cartItemsParams = new HashMap<String, Object>();

		if (cart.getItemByProductId(productId) == null) {
			SQL = "INSERT INTO CART_ITEM (ID, PRODUCT_ID, CART_ID, QUANTITY) VALUES (:id, :productId, :cartId, :quantity)";
			cartItemsParams.put("id", cartId + productId);
			cartItemsParams.put("quantity", 1);
		} else {
			SQL = "UPDATE CART_ITEM SET QUANTITY = :quantity WHERE CART_ID = :cartId AND PRODUCT_ID = :productId";
			CartItem existingItem = cart.getItemByProductId(productId);
			cartItemsParams.put("id", existingItem.getId());
			cartItemsParams.put("quantity", existingItem.getQuantity() + 1);
		}

		cartItemsParams.put("productId", productId);
		cartItemsParams.put("cartId", cartId);

		jdbcTempleate.update(SQL, cartItemsParams);
	}

	@Override
	public void removeItem(String cartId, String productId) {
		String SQL_DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE PRODUCT_ID = :productId AND CART_ID = :id";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", cartId);
		params.put("productId", productId);

		jdbcTempleate.update(SQL_DELETE_CART_ITEM, params);
	}

}
