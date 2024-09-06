package com.rkisuru.fitnesshub.service;

import com.rkisuru.fitnesshub.entity.Feedback;
import com.rkisuru.fitnesshub.entity.Workout;
import com.rkisuru.fitnesshub.repository.FeedbackRepository;
import com.rkisuru.fitnesshub.repository.WorkoutRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final WorkoutRepository workoutRepository;

    public Feedback saveFeedback(Feedback feedback, Long workoutId) {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        feedback.setWorkout(workout);
        return feedbackRepository.save(feedback);
    }

    public Feedback editFeedback(String feedback, Authentication connectedUser, Long feedbackId) {

        Feedback _feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(()-> new EntityNotFoundException("Feedback not found"));

        if (_feedback.getCreatedBy().equals(connectedUser.getName())) {

            if (!feedback.isEmpty()) {
                _feedback.setFeedback(feedback);
                return feedbackRepository.save(_feedback);
            }
            return feedbackRepository.save(_feedback);
        }
        throw new AccessDeniedException("Access denied");
    }

    public String deleteFeedback(Long feedbackId, Authentication connectedUser) {

        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(()-> new EntityNotFoundException("Feedback not found"));

        if (feedback.getCreatedBy().equals(connectedUser.getName())) {
            feedbackRepository.delete(feedback);
            return "Feedback deleted Successfully";
        }
        throw new AccessDeniedException("Access denied");
    }
}
