package com.sd.core.repositories;

import org.springframework.stereotype.Component;


@Component
public class MyRepositoryImpl implements MyRepository{
    
	public MyRepositoryImpl() {
		System.out.println("MyRepositoryImpl bean initialized...");
	}
	
}
