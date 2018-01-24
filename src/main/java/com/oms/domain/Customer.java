package com.oms.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Customer implements Serializable {
	
	public Customer(){
		super();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 7272428423973011366L;
	
	private String customerId;
	private String name;
	private String address;
	private BigDecimal noOfOrdersMade;
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public BigDecimal getNoOfOrdersMade() {
		return noOfOrdersMade;
	}
	
	public void setNoOfOrdersMade(BigDecimal noOfOrdersMade) {
		this.noOfOrdersMade = noOfOrdersMade;
	}
}
