package com.sd.core.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sd.core.entities.Book;


@Component
public class MyRepositoryImpl implements MyRepository{
    
    private List<Book> books = new ArrayList<>();
	
	// Initializing 
	{
		books.add(new Book(1, "Effective Java", Arrays.asList("Joshua Bloch"), 4.8, "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1513389229i/34927404.jpg"));
		books.add(new Book(2, "Clean Code", Arrays.asList("Robert C. Martin"), 4.7, "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1436202607i/3735293.jpg"));
		books.add(new Book(3, "Design Patterns", Arrays.asList("Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"), 4.6, "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1348027904i/85009.jpg"));
	}	
	
	public MyRepositoryImpl() {
		System.out.println("MyRepositoryImpl created");
	}
	
	public List<Book> fetchBestsellers() {
    	return books;
	}
}
