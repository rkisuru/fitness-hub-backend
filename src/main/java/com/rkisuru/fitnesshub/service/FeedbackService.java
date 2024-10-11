package com.rkisuru.fitnesshub.service;

import com.rkisuru.fitnesshub.dto.FeedbackEditRequest;
import com.rkisuru.fitnesshub.dto.FeedbackRequest;
import com.rkisuru.fitnesshub.entity.Feedback;
import com.rkisuru.fitnesshub.entity.Workout;
import com.rkisuru.fitnesshub.exception.OperationNotPermittedException;
import com.rkisuru.fitnesshub.mapper.DtoMapper;
import com.rkisuru.fitnesshub.repository.FeedbackRepository;
import com.rkisuru.fitnesshub.repository.WorkoutRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final WorkoutRepository workoutRepository;
    private final DtoMapper mapper;

    public Feedback saveFeedback(FeedbackRequest request, Long workoutId) throws Exception {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        Feedback feedback = mapper.toFeedback(request);
        if (!feedback.getFeedback().isBlank()) {
            feedback.setWorkout(workout);
            workout.getFeedbacks().add(feedback);
            return feedbackRepository.save(feedback);
        }
        throw new Exception("Feedback should not be empty!");
    }

    public Feedback editFeedback(FeedbackEditRequest request, Authentication connectedUser, Long feedbackId) {

        Feedback _feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(()-> new EntityNotFoundException("feedback not found"));

        if (_feedback.getCreatedBy().equals(connectedUser.getName())) {

            if (!request.feedback().isBlank()) {
                _feedback.setFeedback(request.feedback());
                return feedbackRepository.save(_feedback);
            }
        }
        throw new OperationNotPermittedException("You do not have permission to edit this feedback!");
    }

    public void deleteFeedback(Long feedbackId, Authentication connectedUser) {

        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(()-> new EntityNotFoundException("feedback not found"));

        if (feedback.getCreatedBy().equals(connectedUser.getName())) {
            feedbackRepository.delete(feedback);
        }
        throw new OperationNotPermittedException("You do not have permission to delete this feedback!");
    }
}
