package com.oms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.domain.Customer;
import com.oms.repositories.CustomerRepository;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		int count = customerRepository.getAllCustomers().size();
		return count > 0 ? customerRepository.getAllCustomers() : new ArrayList<Customer>();
	}

}
