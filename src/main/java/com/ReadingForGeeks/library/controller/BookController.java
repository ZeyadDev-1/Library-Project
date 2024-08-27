package com.ReadingForGeeks.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ReadingForGeeks.library.model.Books;
import com.ReadingForGeeks.library.service.BookService;
import com.ReadingForGeeks.library.service.bookListService;

@RestController
@RequestMapping("/library")
public class BookController {
	public BookController() {}
	
	    @Autowired
	    public BookService service;
	    public BookController(BookService service) {
	        this.service = service;
	    }
	    
	    @Autowired
	    public bookListService bookListService;
	public BookController(bookListService bookListService) {
			this.bookListService = bookListService;
		}
	
	

	@GetMapping("/allbooks")
	public ResponseEntity<?> getAllBooks() {
	    try {
	        List<Books> books = service.getAllBooks();
	        if (!books.isEmpty()) {
	            return ResponseEntity.ok(books);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The list is empty currently!");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
	    }
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getBookById(@PathVariable int id){
		try {
		Books book = service.getBookById(id);
		if(book != null) {
		return ResponseEntity.ok(book);
	}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This book id is not found!");
	}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
	}
	}
	
	
	@GetMapping("/category/{keyword}")
	public ResponseEntity<?>  getBookByCategory(@PathVariable String keyword){
		return service.getBookByCategory(keyword);
	}
	
	@GetMapping("/language/{keyword}")
	public ResponseEntity<?> getBookByLanguage(@PathVariable String keyword){
		return service.getBookByLanguage(keyword);
	}
	
	@GetMapping("/author/{keyword}")
	public ResponseEntity<?> getBookByAuther(@PathVariable String keyword){
		return service.getBookByAuthor(keyword);
	}
	
	//GET METHODS END HERE..
	
	@PostMapping("/")
	public ResponseEntity<?> addBook(@RequestBody Books book){
		
		return service.addBook(book);
	}
	
	
	@DeleteMapping("/delete-id/{id}")
	public ResponseEntity<?> deleteByid(@PathVariable int id){
		try {
		Books book = service.getBookById(id);
		if (book != null) {
			service.deleteByid(id);
			return ResponseEntity.ok(book+"\n Deleted Successfully..!");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No books found.");
		
		
		
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Books book){
		try {
			List<Books> books = service.getAllBooks();
			for(Books book1 : books) {
				
				if(book1.equals(book)) {
			       service.delete(book);
			         return ResponseEntity.ok(book+"\nDeleted successfully..!");
				}
			}
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This book doesn't exist!");
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured "+e.getMessage());
		} 
		
	}
	
	
	
	
	
}
