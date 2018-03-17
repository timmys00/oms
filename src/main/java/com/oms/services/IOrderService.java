package com.oms.services;

import com.oms.domain.Order;

public interface IOrderService {
	Long saveOrder(Order order);
}