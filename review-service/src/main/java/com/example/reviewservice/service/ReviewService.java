package com.example.reviewservice.service;

import com.example.reviewservice.entity.Review;
import com.example.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    
    // Create a new review
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }
    
    // Get all reviews
    @Transactional(readOnly = true)
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    
    // Get review by ID
    @Transactional(readOnly = true)
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }
    
    // Update review
    public Review updateReview(Long id, Review reviewDetails) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        
        review.setRating(reviewDetails.getRating());
        review.setComment(reviewDetails.getComment());
        review.setReviewerName(reviewDetails.getReviewerName());
        
        return reviewRepository.save(review);
    }
    
    // Delete review
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        reviewRepository.delete(review);
    }
    
    // Get reviews by book ID
    @Transactional(readOnly = true)
    public List<Review> getReviewsByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }
    
    // Get reviews by user ID
    @Transactional(readOnly = true)
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }
    
    // Get reviews by rating
    @Transactional(readOnly = true)
    public List<Review> getReviewsByRating(Integer rating) {
        return reviewRepository.findByRating(rating);
    }
    
    // Get average rating for a book
    @Transactional(readOnly = true)
    public Double getAverageRatingByBookId(Long bookId) {
        return reviewRepository.getAverageRatingByBookId(bookId);
    }
    
    // Get review count for a book
    @Transactional(readOnly = true)
    public Long getReviewCountByBookId(Long bookId) {
        return reviewRepository.countByBookId(bookId);
    }
}