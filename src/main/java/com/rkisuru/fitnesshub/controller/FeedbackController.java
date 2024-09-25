package com.rkisuru.fitnesshub.controller;

import com.rkisuru.fitnesshub.dto.FeedbackEditRequest;
import com.rkisuru.fitnesshub.dto.FeedbackRequest;
import com.rkisuru.fitnesshub.entity.Feedback;
import com.rkisuru.fitnesshub.service.FeedbackService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody FeedbackRequest request, @PathVariable Long workoutId) throws Exception {

        return ResponseEntity.ok(feedbackService.saveFeedback(request, workoutId));
    }

    @PutMapping("/{feedbackId}")
    public ResponseEntity<Feedback> updateFeedback(@RequestBody FeedbackEditRequest request, Authentication connectedUser, @PathVariable Long feedbackId) {

        return ResponseEntity.ok(feedbackService.editFeedback(request, connectedUser, feedbackId));
    }

    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long feedbackId, Authentication connectedUser) {

        return ResponseEntity.ok(feedbackService.deleteFeedback(feedbackId, connectedUser));
    }
}
