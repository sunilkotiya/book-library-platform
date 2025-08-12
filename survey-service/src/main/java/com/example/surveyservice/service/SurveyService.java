package com.example.surveyservice.service;

import com.example.surveyservice.entity.Survey;
import com.example.surveyservice.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SurveyService {
    
    private final SurveyRepository surveyRepository;
    
    // Create a new survey
    public Survey createSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }
    
    // Get all surveys
    @Transactional(readOnly = true)
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }
    
    // Get survey by ID
    @Transactional(readOnly = true)
    public Optional<Survey> getSurveyById(Long id) {
        return surveyRepository.findById(id);
    }
    
    // Update survey
    public Survey updateSurvey(Long id, Survey surveyDetails) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found with id: " + id));
        
        survey.setTitle(surveyDetails.getTitle());
        survey.setDescription(surveyDetails.getDescription());
        survey.setStatus(surveyDetails.getStatus());
        survey.setBookId(surveyDetails.getBookId());
        survey.setStartDate(surveyDetails.getStartDate());
        survey.setEndDate(surveyDetails.getEndDate());
        survey.setMaxResponses(surveyDetails.getMaxResponses());
        survey.setCreatorName(surveyDetails.getCreatorName());
        
        return surveyRepository.save(survey);
    }
    
    // Delete survey
    public void deleteSurvey(Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found with id: " + id));
        surveyRepository.delete(survey);
    }
    
    // Get surveys by creator ID
    @Transactional(readOnly = true)
    public List<Survey> getSurveysByCreatorId(Long creatorId) {
        return surveyRepository.findByCreatorId(creatorId);
    }
    
    // Get surveys by status
    @Transactional(readOnly = true)
    public List<Survey> getSurveysByStatus(Survey.SurveyStatus status) {
        return surveyRepository.findByStatus(status);
    }
    
    // Get surveys by book ID
    @Transactional(readOnly = true)
    public List<Survey> getSurveysByBookId(Long bookId) {
        return surveyRepository.findByBookId(bookId);
    }
    
    // Search surveys by title
    @Transactional(readOnly = true)
    public List<Survey> searchSurveysByTitle(String title) {
        return surveyRepository.findByTitleContainingIgnoreCase(title);
    }
    
    // Get active surveys
    @Transactional(readOnly = true)
    public List<Survey> getActiveSurveys() {
        LocalDateTime now = LocalDateTime.now();
        return surveyRepository.findByStatusAndStartDateBeforeAndEndDateAfter(
                Survey.SurveyStatus.ACTIVE, now, now);
    }
    
    // Get available surveys (not at max capacity)
    @Transactional(readOnly = true)
    public List<Survey> getAvailableSurveys() {
        return surveyRepository.findAvailableSurveys(Survey.SurveyStatus.ACTIVE);
    }
    
    // Increment response count
    public Survey incrementResponseCount(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new RuntimeException("Survey not found with id: " + surveyId));
        
        survey.setResponseCount(survey.getResponseCount() + 1);
        return surveyRepository.save(survey);
    }
    
    // Update survey status
    public Survey updateSurveyStatus(Long id, Survey.SurveyStatus status) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found with id: " + id));
        
        survey.setStatus(status);
        return surveyRepository.save(survey);
    }
}