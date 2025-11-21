package com.sd.core.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sd.core.entities.Book;
import com.sd.core.repositories.MyRepository;
import com.sd.core.services.MyService;



@Controller
//@RequestMapping("/books") //For base path
public class MyController {
    private final MyService myService;
    private final MyRepository myRepository;

    
	//Constructor Injection of MyService and MyRepository
	public MyController(MyService myService, MyRepository myRepository) {
        this.myService = myService;
        this.myRepository = myRepository;
	}
	
	
	//@GetMapping maps this method to handle HTTP GET requests at this URL
	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}
	
	
	/*
	 * Model is used to pass data from the controller to the view.
	 * It is request-scoped, meaning it exists only for that HTTP request.
	 * We can also use HttpServeltsRequest object to bind the data for view
	 */
	@GetMapping("/")
	public String home(Model model){
		List<Book> books = this.myRepository.fetchBestsellers();
		model.addAttribute("bestSellerList", books);
		return "home";
	}
   
	
	/*
	 * @RequestParam is used to extract query parameters from the URL and pass 
	 * them to a controller method.
	 * from this URL- http://localhost:8080/book?id=3
	 * 
	 * defaultValue makes the request param non-required and sets the given value 
	 * if param value is not passed in URL
	 */
	@GetMapping("/book") 
	public String getBook(@RequestParam(value="id", defaultValue="0") int id, Model model){
		List<Book> books = this.myRepository.fetchBestsellers();
		Book book = books.stream().filter(v -> v.getId()==id).findAny().get();
		model.addAttribute("book", book);
		return "book";
	}
	
	@GetMapping("/create")
	public String create(){
		return "create";
	}
	
	
	//@PostMapping maps this method to handle HTTP POST requests at this URL
	//Here method is POST but request data is passed in query-parameters
	@PostMapping("/book") 
	public String addBook(
			@RequestParam("title") String title, 
			@RequestParam("authors") List<String> authors,
			@RequestParam(value = "amazonRating", defaultValue = "0.0") double amazonRating,
			@RequestParam("imageUrl") String imageUrl,
			Model model) 
	{
		List<Book> books = myRepository.fetchBestsellers();
		
		Book book = new Book(books.size() + 1, title, authors, amazonRating, imageUrl);
		books.add(book);
		
		model.addAttribute("book", book);
		
		return "book";
	}
}



