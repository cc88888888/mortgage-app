package com.gc.service;

import org.springframework.context.ApplicationEvent;

public class CustomerAccountServiceEvent extends ApplicationEvent {

	public CustomerAccountServiceEvent(Object source) {
        super(source);
    }

    public String toString() {
        return "My CustomerAccountService Event";
    }
}
