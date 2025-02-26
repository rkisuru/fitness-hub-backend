package com.rkisuru.fitnesshub.controller;

import com.rkisuru.fitnesshub.dto.FeedbackRequest;
import com.rkisuru.fitnesshub.entity.Feedback;
import com.rkisuru.fitnesshub.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/{workoutId}/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    public final FeedbackService feedbackService;

    @PostMapping("/")
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody FeedbackRequest request, @PathVariable Long workoutId) throws Exception {

        return ResponseEntity.ok(feedbackService.saveFeedback(request, workoutId));
    }

    @PatchMapping("/")
    public ResponseEntity<Feedback> updateFeedback(@RequestBody FeedbackRequest request, Authentication connectedUser, @PathVariable Long workoutId, @RequestParam Long feedbackId) {

        return ResponseEntity.ok(feedbackService.editFeedback(request, connectedUser, feedbackId));
    }

    @DeleteMapping("/")
    public ResponseEntity<Map<String, String>> deleteFeedback(@PathVariable Long workoutId, @RequestParam Long feedbackId, Authentication connectedUser) {

        feedbackService.deleteFeedback(feedbackId, connectedUser);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Feedback deleted successfully");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
