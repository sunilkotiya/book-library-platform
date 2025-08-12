package com.example.surveyservice.repository;

import com.example.surveyservice.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    
    // Find surveys by creator ID
    List<Survey> findByCreatorId(Long creatorId);
    
    // Find surveys by status
    List<Survey> findByStatus(Survey.SurveyStatus status);
    
    // Find surveys by book ID
    List<Survey> findByBookId(Long bookId);
    
    // Find surveys by title containing (case-insensitive)
    List<Survey> findByTitleContainingIgnoreCase(String title);
    
    // Find active surveys
    List<Survey> findByStatusAndStartDateBeforeAndEndDateAfter(
            Survey.SurveyStatus status, LocalDateTime now1, LocalDateTime now2);
    
    // Find surveys created between dates
    List<Survey> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Find surveys by creator name
    List<Survey> findByCreatorNameContainingIgnoreCase(String creatorName);
    
    // Count surveys by status
    Long countByStatus(Survey.SurveyStatus status);
    
    // Custom query to find surveys with response count less than max
    @Query("SELECT s FROM Survey s WHERE s.responseCount < s.maxResponses AND s.status = :status")
    List<Survey> findAvailableSurveys(@Param("status") Survey.SurveyStatus status);
}