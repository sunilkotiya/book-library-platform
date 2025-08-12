package com.example.reviewservice.controller;

import com.example.reviewservice.entity.Review;
import com.example.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;
    
    // Create a new review
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review createdReview = reviewService.createReview(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }
    
    // Get all reviews
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }
    
    // Get review by ID
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Optional<Review> review = reviewService.getReviewById(id);
        return review.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    // Update review
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
        try {
            Review updatedReview = reviewService.updateReview(id, reviewDetails);
            return ResponseEntity.ok(updatedReview);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Delete review
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        try {
            reviewService.deleteReview(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Get reviews by book ID
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Review>> getReviewsByBookId(@PathVariable Long bookId) {
        List<Review> reviews = reviewService.getReviewsByBookId(bookId);
        return ResponseEntity.ok(reviews);
    }
    
    // Get reviews by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long userId) {
        List<Review> reviews = reviewService.getReviewsByUserId(userId);
        return ResponseEntity.ok(reviews);
    }
    
    // Get reviews by rating
    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Review>> getReviewsByRating(@PathVariable Integer rating) {
        List<Review> reviews = reviewService.getReviewsByRating(rating);
        return ResponseEntity.ok(reviews);
    }
    
    // Get average rating for a book
    @GetMapping("/book/{bookId}/average-rating")
    public ResponseEntity<Double> getAverageRatingByBookId(@PathVariable Long bookId) {
        Double averageRating = reviewService.getAverageRatingByBookId(bookId);
        return ResponseEntity.ok(averageRating);
    }
    
    // Get review count for a book
    @GetMapping("/book/{bookId}/count")
    public ResponseEntity<Long> getReviewCountByBookId(@PathVariable Long bookId) {
        Long count = reviewService.getReviewCountByBookId(bookId);
        return ResponseEntity.ok(count);
    }
}