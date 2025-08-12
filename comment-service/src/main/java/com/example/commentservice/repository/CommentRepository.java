package com.example.commentservice.repository;

import com.example.commentservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    // Find comments by review ID
    List<Comment> findByReviewId(Long reviewId);
    
    // Find comments by user ID
    List<Comment> findByUserId(Long userId);
    
    // Find comments by parent comment ID (replies)
    List<Comment> findByParentCommentId(Long parentCommentId);
    
    // Find top-level comments (no parent)
    List<Comment> findByParentCommentIdIsNull();
    
    // Find comments by review ID and parent comment ID is null (top-level comments for a review)
    List<Comment> findByReviewIdAndParentCommentIdIsNull(Long reviewId);
    
    // Find comments by commenter name
    List<Comment> findByCommenterNameContainingIgnoreCase(String commenterName);
    
    // Count comments by review ID
    Long countByReviewId(Long reviewId);
}