package com.oms.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oms.domain.Customer;

@Repository
public class CustomerRepository implements ICustomerRepository {

	 @Autowired 
     private NamedParameterJdbcTemplate jdbcTemplate; 

	@Override
	public List<Customer> getAllCustomers() {
        Map<String, Object> params = new HashMap<String, Object>(); 
        List<Customer> result = jdbcTemplate.query("SELECT * FROM customers", params, new CustomerMapper()); 
        return result; 
	}
	
	   private static final class CustomerMapper implements RowMapper<Customer> { 
		     public Customer mapRow(ResultSet rs, int rowNum) throws SQLException { 
		    	 Customer product = new Customer(); 
			     //product.setCustomerId(rs.getString("CUSTOMER_ID")); 
			     product.setName(rs.getString("NAME")); 
//			     product.setAddress(rs.getString("ADDRESS")); 
//			     product.setNoOfOrdersMade(rs.getBigDecimal("NO_OF_ORDERS_MADE")); 
			     return product; 
		     } 
		   }

}
