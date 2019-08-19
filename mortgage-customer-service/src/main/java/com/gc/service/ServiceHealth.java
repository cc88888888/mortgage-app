package com.gc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import com.gc.service.ServiceProperties;

public class ServiceHealth implements HealthIndicator {

	@Autowired
	private ServiceProperties configuration;

	
	@Override
	public Health health() {
		return Health.up().withDetail("details",
				"{ 'internals' : 'getting close to limit', 'profile' : '" + this.configuration.getName() + "' }" + "Description :"+ this.configuration.getDescription() )
				.status("itsok!").build();
	}
}
