package com.gc.api.rest;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gc.api.rest.AbstractRestController;
import com.gc.model.Customer;
import com.gc.exception.HTTP400Exception;
import com.gc.service.CustomerService;
import com.gc.service.ServiceEvent;

public class CustomerController extends AbstractRestController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	public void createCustomer(@RequestBody Customer customer, HttpServletRequest request,
			HttpServletResponse response) {
		Customer createdCustomer = this.customerService.createCustomer(customer);
		if (createdCustomer != null){
			eventPublisher.publishEvent(new ServiceEvent(this, createdCustomer, "CustomerCreated" ));
		}
		response.setHeader("Location", request.getRequestURL().append("/").append(createdCustomer.getId()).toString());
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Page<Customer> getAllCustomersByPage(
			@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
			@RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
			HttpServletRequest request, HttpServletResponse response) {
		return this.customerService.getAllCustomersByPage(page, size);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Customer> getAllCustomers(
			@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
			@RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
			HttpServletRequest request, HttpServletResponse response) {
		return this.customerService.getAllCustomers();
	}

	@RequestMapping("/simple/{id}")
	public Optional<Customer> getSimpleCustomer(@PathVariable("id") Long id) {
		Optional<Customer> customer = this.customerService.getCustomer(id);
		checkResourceFound(customer);
		return customer;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody

			Optional<Customer> getCustomer(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		Optional<Customer> customer = this.customerService.getCustomer(id);
		checkResourceFound(customer);
		return customer;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer, HttpServletRequest request,
			HttpServletResponse response) {
		checkResourceFound(this.customerService.getCustomer(id));
		if (id != customer.getId())
			throw new HTTP400Exception("ID doesn't match!");
		this.customerService.updateCustomer(customer);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
		checkResourceFound(this.customerService.getCustomer(id));
		this.customerService.deleteCustomer(id);
	}
}
