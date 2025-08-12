package com.example.reviewservice.repository;

import com.example.reviewservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    // Find reviews by book ID
    List<Review> findByBookId(Long bookId);
    
    // Find reviews by user ID
    List<Review> findByUserId(Long userId);
    
    // Find reviews by rating
    List<Review> findByRating(Integer rating);
    
    // Find reviews by book ID and rating greater than or equal to specified rating
    List<Review> findByBookIdAndRatingGreaterThanEqual(Long bookId, Integer rating);
    
    // Get average rating for a book
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.bookId = :bookId")
    Double getAverageRatingByBookId(@Param("bookId") Long bookId);
    
    // Count reviews for a book
    Long countByBookId(Long bookId);
    
    // Find reviews by reviewer name
    List<Review> findByReviewerNameContainingIgnoreCase(String reviewerName);
}