package com.example.commentservice.service;

import com.example.commentservice.entity.Comment;
import com.example.commentservice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    
    private final CommentRepository commentRepository;
    
    // Create a new comment
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }
    
    // Get all comments
    @Transactional(readOnly = true)
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    
    // Get comment by ID
    @Transactional(readOnly = true)
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }
    
    // Update comment
    public Comment updateComment(Long id, Comment commentDetails) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        
        comment.setContent(commentDetails.getContent());
        comment.setCommenterName(commentDetails.getCommenterName());
        
        return commentRepository.save(comment);
    }
    
    // Delete comment
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        commentRepository.delete(comment);
    }
    
    // Get comments by review ID
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByReviewId(Long reviewId) {
        return commentRepository.findByReviewId(reviewId);
    }
    
    // Get comments by user ID
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }
    
    // Get replies to a comment
    @Transactional(readOnly = true)
    public List<Comment> getRepliesByParentCommentId(Long parentCommentId) {
        return commentRepository.findByParentCommentId(parentCommentId);
    }
    
    // Get top-level comments
    @Transactional(readOnly = true)
    public List<Comment> getTopLevelComments() {
        return commentRepository.findByParentCommentIdIsNull();
    }
    
    // Get top-level comments for a review
    @Transactional(readOnly = true)
    public List<Comment> getTopLevelCommentsByReviewId(Long reviewId) {
        return commentRepository.findByReviewIdAndParentCommentIdIsNull(reviewId);
    }
    
    // Get comment count for a review
    @Transactional(readOnly = true)
    public Long getCommentCountByReviewId(Long reviewId) {
        return commentRepository.countByReviewId(reviewId);
    }
}