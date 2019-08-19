package com.gc.service;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "customer.service", ignoreUnknownFields = false)
@Component
public class ServiceProperties {

	@NotNull
	private String name = "CustomerAccountService";

    @NotNull
	private String description = "Customer Account MicroService that helps maintain the customer bank, credit card and other types of expense account";

    
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
