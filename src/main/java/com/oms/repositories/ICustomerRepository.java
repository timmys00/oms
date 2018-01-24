package com.oms.repositories;

import java.util.List;

import com.oms.domain.Customer;

public interface ICustomerRepository {
	List<Customer> getAllCustomers();
}
