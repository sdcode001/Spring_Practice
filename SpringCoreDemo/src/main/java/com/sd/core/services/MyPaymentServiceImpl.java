package com.sd.core.services;

import org.springframework.stereotype.Component;


@Component
public class MyPaymentServiceImpl implements MyService {
    
	public MyPaymentServiceImpl() {
		System.out.println("MyPaymentServiceImpl bean initialized...");
	}
	
}
