package com.gc.service;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "user.service", ignoreUnknownFields = false)
@Component
public class ServiceProperties {

	@NotNull
	private String name = "CustomerService";
	
	@NotNull
	private String description = "Customer Spring Boot MicroService using Spring Data JPA, Spring Cloud";

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
