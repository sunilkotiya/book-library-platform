package com.example.surveyservice.controller;

import com.example.surveyservice.entity.Survey;
import com.example.surveyservice.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
public class SurveyController {
    
    private final SurveyService surveyService;
    
    // Create a new survey
    @PostMapping
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey) {
        Survey createdSurvey = surveyService.createSurvey(survey);
        return new ResponseEntity<>(createdSurvey, HttpStatus.CREATED);
    }
    
    // Get all surveys
    @GetMapping
    public ResponseEntity<List<Survey>> getAllSurveys() {
        List<Survey> surveys = surveyService.getAllSurveys();
        return ResponseEntity.ok(surveys);
    }
    
    // Get survey by ID
    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable Long id) {
        Optional<Survey> survey = surveyService.getSurveyById(id);
        return survey.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    // Update survey
    @PutMapping("/{id}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable Long id, @RequestBody Survey surveyDetails) {
        try {
            Survey updatedSurvey = surveyService.updateSurvey(id, surveyDetails);
            return ResponseEntity.ok(updatedSurvey);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Delete survey
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        try {
            surveyService.deleteSurvey(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Get surveys by creator ID
    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<Survey>> getSurveysByCreatorId(@PathVariable Long creatorId) {
        List<Survey> surveys = surveyService.getSurveysByCreatorId(creatorId);
        return ResponseEntity.ok(surveys);
    }
    
    // Get surveys by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Survey>> getSurveysByStatus(@PathVariable Survey.SurveyStatus status) {
        List<Survey> surveys = surveyService.getSurveysByStatus(status);
        return ResponseEntity.ok(surveys);
    }
    
    // Get surveys by book ID
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Survey>> getSurveysByBookId(@PathVariable Long bookId) {
        List<Survey> surveys = surveyService.getSurveysByBookId(bookId);
        return ResponseEntity.ok(surveys);
    }
    
    // Search surveys by title
    @GetMapping("/search/title")
    public ResponseEntity<List<Survey>> searchSurveysByTitle(@RequestParam String title) {
        List<Survey> surveys = surveyService.searchSurveysByTitle(title);
        return ResponseEntity.ok(surveys);
    }
    
    // Get active surveys
    @GetMapping("/active")
    public ResponseEntity<List<Survey>> getActiveSurveys() {
        List<Survey> surveys = surveyService.getActiveSurveys();
        return ResponseEntity.ok(surveys);
    }
    
    // Get available surveys
    @GetMapping("/available")
    public ResponseEntity<List<Survey>> getAvailableSurveys() {
        List<Survey> surveys = surveyService.getAvailableSurveys();
        return ResponseEntity.ok(surveys);
    }
    
    // Increment response count
    @PostMapping("/{id}/increment-response")
    public ResponseEntity<Survey> incrementResponseCount(@PathVariable Long id) {
        try {
            Survey updatedSurvey = surveyService.incrementResponseCount(id);
            return ResponseEntity.ok(updatedSurvey);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Update survey status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Survey> updateSurveyStatus(@PathVariable Long id, @RequestParam Survey.SurveyStatus status) {
        try {
            Survey updatedSurvey = surveyService.updateSurveyStatus(id, status);
            return ResponseEntity.ok(updatedSurvey);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}