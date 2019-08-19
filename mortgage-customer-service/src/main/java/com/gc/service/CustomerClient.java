package com.gc.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gc.model.Customer;

@FeignClient("mortgage-customer-service")
public interface CustomerClient {

	@RequestMapping(method = RequestMethod.GET, value="/mortgage-customerservice/v1/customer/all")
	List<Customer> getCustomers();
	
	@RequestMapping(method = RequestMethod.GET, value="/mortgage-customerservice/v1/customer/simple/{id}")
	Customer getCustomer(@PathVariable("id") Long id);
}
