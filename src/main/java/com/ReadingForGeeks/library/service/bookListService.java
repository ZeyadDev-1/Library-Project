package com.ReadingForGeeks.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ReadingForGeeks.library.model.Books;


@Service
public class bookListService {
	


	private List<Books> books;
	
	public bookListService() {}
	
	public List<Books> getBooks(){
		return books;
	}


	
	
}
