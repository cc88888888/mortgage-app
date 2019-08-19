package com.gc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gc.model.Customer;
import com.gc.dao.jpa.CustomerRepository;
import com.gc.service.CustomerService;

@Service
public class CustomerService {

private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerService() {
	}

	public Customer createCustomer(Customer customer) {
		if (customer.getDateOfBirth() != null) {
			log.info("Customer Date of Birth :" + customer.getDateOfBirth());
		} else {
			log.info("Customer Date of Birth is null :");
		}
	
		return customerRepository.save(customer);
	}

	public Optional<Customer> getCustomer(long id) {
		return customerRepository.findById(id);
	}

	public void updateCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}

	public Page<Customer> getAllCustomersByPage(Integer page, Integer size) {
		Page pageOfCustomers = customerRepository.findAll(new PageRequest(page, size));
		return pageOfCustomers;
	}

	public List<Customer> getAllCustomers() {
		Iterable<Customer> pageOfCustomers = customerRepository.findAll();
		List<Customer> customers = new ArrayList<Customer>();
		for (Customer customer : pageOfCustomers) {
			customers.add(customer);
		}
		log.info("In Real Service getAllCustomers size :" + customers.size());

		return customers;
	}
}
