package com.gc.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gc.model.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	Customer findCustomerByRating(int rating);
	Page findAll(Pageable pageable);
}
