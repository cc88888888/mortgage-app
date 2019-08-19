package com.gc.service;

import org.springframework.context.ApplicationEvent;

public class CustomerServiceEvent extends ApplicationEvent {

	public CustomerServiceEvent(Object source) {
        super(source);
    }

    public String toString() {
        return "My CustomerService Event";
    }
}
