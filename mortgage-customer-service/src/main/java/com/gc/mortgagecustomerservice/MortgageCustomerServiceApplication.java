package com.gc.mortgagecustomerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@SuppressWarnings("deprecation")
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.rollingstone")
@EnableJpaRepositories("com.rollingstone.dao.jpa")
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients
public class MortgageCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MortgageCustomerServiceApplication.class, args);
	}

}
