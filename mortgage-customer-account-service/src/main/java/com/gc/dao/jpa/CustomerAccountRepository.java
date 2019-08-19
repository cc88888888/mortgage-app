package com.gc.dao.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gc.model.Account;
import com.gc.model.Customer;

public interface CustomerAccountRepository extends PagingAndSortingRepository<Account, Long> {

    List<Account> findCustomerAccountsByCustomer(Customer customer);
    Page findAll(Pageable pageable);
}
