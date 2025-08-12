package com.example.bookservice.repository;

import com.example.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Find books by title (case-insensitive)
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    // Find books by author (case-insensitive)
    List<Book> findByAuthorContainingIgnoreCase(String author);
    
    // Find books by genre
    List<Book> findByGenre(String genre);
    
    // Find books by ISBN
    Optional<Book> findByIsbn(String isbn);
    
    // Find books by published year
    List<Book> findByPublishedYear(Integer year);
    
    // Custom query to find books by title and author
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title% AND b.author LIKE %:author%")
    List<Book> findByTitleAndAuthor(@Param("title") String title, @Param("author") String author);
}
