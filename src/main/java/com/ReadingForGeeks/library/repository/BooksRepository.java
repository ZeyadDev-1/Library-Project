package com.ReadingForGeeks.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ReadingForGeeks.library.model.Books;



@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {

	@Query("SELECT b FROM Books b WHERE UPPER(b.category) = UPPER(:keyword)")
    List<Books> findByCategory(String keyword);
	
	@Query("SELECT b FROM Books b WHERE UPPER(b.language) = UPPER(:keyword)")
	List<Books> findByLanguage(String keyword);

	@Query("SELECT b FROM Books b WHERE " +
		       "SUBSTRING(UPPER(b.author), 1, 3) = UPPER(:keyword) OR " + 
		       "UPPER(b.author) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " + 
		       "SUBSTRING(UPPER(b.author), LENGTH(b.author) - 2, 3) = UPPER(:keyword)")
		List<Books> findByAuthor(String keyword);

	// GET METHODS END HERE..
	
	

}
