package com.oms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oms.services.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/customers")
	public ModelAndView Customers(Model model) {
		ModelAndView modelAndView = new ModelAndView();  
		modelAndView.addObject("customers", customerService.getAllCustomers()); 
		modelAndView.setViewName("customers"); 
		return modelAndView; 
//		model.addAttribute("customers", customerService.getAllCustomers());
//		return "customers";
	}

}
