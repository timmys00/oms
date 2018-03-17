package com.oms.repositories;

import com.oms.domain.Order;

public interface IOrderRepository {
	long saveOrder(Order order);
}