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

import com.gc.dao.jpa.CustomerAccountRepository;
import com.gc.model.Account;
import com.gc.model.Customer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class CustomerAccountService {

	private static final Logger log = LoggerFactory.getLogger(CustomerAccountService.class);

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

   @Autowired
  	private CustomerClient customerClient;

    public CustomerAccountService() {
    }

    @HystrixCommand(fallbackMethod = "createAccountWithoutCustomerValidation")
    @HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
    public Account createAccount(Account account) throws Exception {
    	Account createdAccount = null;
    	if (account != null && account.getCustomer() != null){
    		
    		log.info("In service account create"+ account.getCustomer().getId());
    		if (customerClient == null){
        		log.info("In customerClient null got customer");
    		}
    		else {
    			log.info("In customerClient not null got customer");
    		}
    		
    		Customer customer = customerClient.getCustomer((new Long(account.getCustomer().getId())));
    		
    		if (customer != null){
    			createdAccount  = customerAccountRepository.save(account);
    			log.info("Valid Customer Created Account.");
    		}else {
    			log.info("Invalid Customer");
    			throw new Exception("Invalid Customer");
    		}
    	}
    	else {
    			throw new Exception("Invalid Customer");
    	}
        return createdAccount;
    }
    
    public Account createAccountWithoutCustomerValidation(Account account) throws Exception {
    	Account createdAccount = null;
    
    	log.info("Customer Validation Failed. Creating Customer Account without validation.");
    	
    	createdAccount  = customerAccountRepository.save(account);
    	
        return createdAccount;
    }

    public Optional<Account> getAccount(long id) {
        return customerAccountRepository.findById(id);
    }

    @HystrixCommand(fallbackMethod = "updateAccountWithoutValidation")
    @HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
    public void updateAccount(Account account) throws Exception {
    	Account createdAccount = null;
    	if (account != null && account.getCustomer() != null){
    		
    		log.info("In service account create"+ account.getCustomer().getId());
    		if (customerClient == null){
        		log.info("In customerClient null got customer");
    		}
    		else {
    			log.info("In customerClient not null got customer");
    		}
    		
    		Customer customer = customerClient.getCustomer((new Long(account.getCustomer().getId())));
    		
    		if (customer != null){
    			createdAccount  = customerAccountRepository.save(account);
    		}else {
    			log.info("Invalid Customer");
    			throw new Exception("Invalid Customer");
    		}
    	}
    	else {
    			throw new Exception("Invalid Customer");
    	}
    }
    
    public void updateAccountWithoutValidation(Account account) throws Exception {
    	Account createdAccount = null;
    	if (account != null && account.getCustomer() != null){
    		
    		log.info("In service account create"+ account.getCustomer().getId());
    		if (customerClient == null){
        		log.info("In customerClient null got customer");
    		}
    		else {
    			log.info("In customerClient not null got customer");
    		}
   			createdAccount  = customerAccountRepository.save(account);
    	}
    	else {
    			throw new Exception("Invalid Customer");
    	}
    }

    public void deleteAccount(Long id) {
    	customerAccountRepository.deleteById(id);
    }

    public Page<Account> getAllAccountsByPage(Integer page, Integer size) {
        Page pageOfAccounts = customerAccountRepository.findAll(new PageRequest(page, size));
        
        return pageOfAccounts;
    }
    
    public List<Account> getAllAccounts() {
        Iterable<Account> pageOfAccounts = customerAccountRepository.findAll();
        
        List<Account> customerAccounts = new ArrayList<Account>();
        
        for (Account account : pageOfAccounts){
        	customerAccounts.add(account);
        }
    	log.info("In Real Service getAllAccounts  size :"+customerAccounts.size());

    	
        return customerAccounts;
    }
    
    public List<Account> getAllAccountsForCustomer(Customer customer) {
        Iterable<Account> pageOfAccounts = customerAccountRepository.findCustomerAccountsByCustomer(customer);
        
        List<Account> customerAccounts = new ArrayList<Account>();
        
        for (Account account : pageOfAccounts){
        	customerAccounts.add(account);
        }
    	log.info("In Real Service getAllAccounts  size :"+customerAccounts.size());

    	
        return customerAccounts;
    }
}
