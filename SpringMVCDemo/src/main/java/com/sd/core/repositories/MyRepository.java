package com.sd.core.repositories;

import java.util.List;

import com.sd.core.entities.Book;

public interface MyRepository {
	public List<Book> fetchBestsellers();
}
