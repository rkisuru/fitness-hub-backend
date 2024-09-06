package com.rkisuru.fitnesshub.controller;

import com.rkisuru.fitnesshub.entity.Feedback;
import com.rkisuru.fitnesshub.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FeedbackController {

    public final FeedbackService feedbackService;

    @PostMapping("/{workoutId}")
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback, @PathVariable Long workoutId) {

        return ResponseEntity.ok(feedbackService.saveFeedback(feedback, workoutId));
    }

    @PutMapping("/{feedbackId}")
    public ResponseEntity<Feedback> updateFeedback(@RequestBody String feedback, Authentication connectedUser, @PathVariable Long feedbackId) {

        return ResponseEntity.ok(feedbackService.editFeedback(feedback, connectedUser, feedbackId));
    }

    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long feedbackId, Authentication connectedUser) {

        return ResponseEntity.ok(feedbackService.deleteFeedback(feedbackId, connectedUser));
    }
}
