package com.ReadingForGeeks.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ReadingForGeeks.library.model.Books;
import com.ReadingForGeeks.library.repository.BooksRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {

	public BooksRepository repo;

	public BookService(BooksRepository repo) {
		super();
		this.repo = repo;
	}
	

	public List<Books> getAllBooks() {
		return repo.findAll();
	}

	
	public Books getBookById(int id) {
		return repo.findById(id).orElse(null);
	}
	

	public ResponseEntity<?> getBookByCategory(String keyword) {

		try {
			List<Books> books = repo.findByCategory(keyword);
			if (books == null || books.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No books found for the given category.");
			}

			return ResponseEntity.ok(books);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
	}
	

	public ResponseEntity<?> getBookByLanguage(String keyword) {

		try {
			List<Books> books = repo.findByLanguage(keyword);
			if (books.isEmpty() || books == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such language..");
			}

			return ResponseEntity.ok(books);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
	}
	
	

	public ResponseEntity<?> getBookByAuthor(String keyword) {

		try {
			List<Books> books = repo.findByAuthor(keyword);
			if (books.isEmpty() || books == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such Author..");
			}

			return ResponseEntity.ok(books);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
	}
	
	// GET METHODS END HERE..

	public ResponseEntity<?> addBook(Books book) {

		try {
			if (book != null 
	                && book.getAuthor() != null && !book.getAuthor().trim().isEmpty()
	                && book.getBookName() != null && !book.getBookName().trim().isEmpty()
	                && book.getCategory() != null && !book.getCategory().trim().isEmpty()
	                && book.getFormat() != null && !book.getFormat().trim().isEmpty()
	                && book.getLanguage() != null && !book.getLanguage().trim().isEmpty()
	                && book.getYear() >= 1900 && book.getYear() <= 2024)
			{
				repo.save(book);
				return ResponseEntity.status(HttpStatus.CREATED).body(book+"\n Successfully saved..!");
			}

			else {
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Missing information..!");
			}

		}

		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
	}


	public void deleteByid(int id) {
           repo.deleteById(id);
		}
		
	

}

