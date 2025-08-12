package com.example.commentservice.controller;

import com.example.commentservice.entity.Comment;
import com.example.commentservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    
    private final CommentService commentService;
    
    // Create a new comment
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(comment);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }
    
    // Get all comments
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }
    
    // Get comment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    // Update comment
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment commentDetails) {
        try {
            Comment updatedComment = commentService.updateComment(id, commentDetails);
            return ResponseEntity.ok(updatedComment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Delete comment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Get comments by review ID
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<List<Comment>> getCommentsByReviewId(@PathVariable Long reviewId) {
        List<Comment> comments = commentService.getCommentsByReviewId(reviewId);
        return ResponseEntity.ok(comments);
    }
    
    // Get comments by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable Long userId) {
        List<Comment> comments = commentService.getCommentsByUserId(userId);
        return ResponseEntity.ok(comments);
    }
    
    // Get replies to a comment
    @GetMapping("/{parentCommentId}/replies")
    public ResponseEntity<List<Comment>> getRepliesByParentCommentId(@PathVariable Long parentCommentId) {
        List<Comment> replies = commentService.getRepliesByParentCommentId(parentCommentId);
        return ResponseEntity.ok(replies);
    }
    
    // Get top-level comments
    @GetMapping("/top-level")
    public ResponseEntity<List<Comment>> getTopLevelComments() {
        List<Comment> comments = commentService.getTopLevelComments();
        return ResponseEntity.ok(comments);
    }
    
    // Get top-level comments for a review
    @GetMapping("/review/{reviewId}/top-level")
    public ResponseEntity<List<Comment>> getTopLevelCommentsByReviewId(@PathVariable Long reviewId) {
        List<Comment> comments = commentService.getTopLevelCommentsByReviewId(reviewId);
        return ResponseEntity.ok(comments);
    }
    
    // Get comment count for a review
    @GetMapping("/review/{reviewId}/count")
    public ResponseEntity<Long> getCommentCountByReviewId(@PathVariable Long reviewId) {
        Long count = commentService.getCommentCountByReviewId(reviewId);
        return ResponseEntity.ok(count);
    }
}