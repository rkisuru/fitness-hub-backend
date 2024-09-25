package com.rkisuru.fitnesshub.service;

import com.rkisuru.fitnesshub.dto.WorkoutEditRequest;
import com.rkisuru.fitnesshub.dto.WorkoutRequest;
import com.rkisuru.fitnesshub.dto.WorkoutResponse;
import com.rkisuru.fitnesshub.entity.Exercise;
import com.rkisuru.fitnesshub.entity.WorkoutLikes;
import com.rkisuru.fitnesshub.entity.Workout;
import com.rkisuru.fitnesshub.exception.OperationNotPermittedException;
import com.rkisuru.fitnesshub.mapper.DtoMapper;
import com.rkisuru.fitnesshub.repository.ExerciseRepository;
import com.rkisuru.fitnesshub.repository.LikeRepository;
import com.rkisuru.fitnesshub.repository.WorkoutRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final DtoMapper mapper;
    private final ExerciseRepository exerciseRepository;
    private final LikeRepository likeRepository;
    private final ImageUploadService imageUploadService;

    public Long saveWorkout(WorkoutRequest request) {

        Workout workout = mapper.toWorkout(request);
        workout.setViewCount(0);
        workout.setLikeCount(0);
        return workoutRepository.save(workout).getId();
    }

    public String deleteWorkout(Long id, Authentication connectedUser) {

        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workout not found"));

        if (workout.getCreatedBy().equals(connectedUser.getName())) {

            List<Exercise> exercises = workout.getExercises();
            exerciseRepository.deleteAll(exercises);
            workoutRepository.delete(workout);
            return "Workout deleted Successfully";
        }
        throw new OperationNotPermittedException("You do not have permission to delete this workout");
    }

    public List<WorkoutResponse> getAllWorkouts() {

        return workoutRepository.findAll()
                .stream()
                .map(mapper::fromWorkout)
                .toList();
    }

    public WorkoutResponse findWorkoutById(Long workoutId, Authentication connectedUser) {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(()-> new EntityNotFoundException("Workout not found"));

        if (!workout.getCreatedBy().equals(connectedUser.getName())) {
            workout.setViewCount(workout.getViewCount() + 1);
            workoutRepository.save(workout);
        }
        return mapper.fromWorkout(workout);
    }

    public Workout editWorkout(Long workoutId, WorkoutEditRequest request, Authentication connectedUser) {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(()-> new EntityNotFoundException("Workout not found"));

        if (workout.getCreatedBy().equals(connectedUser.getName())) {

            if (!request.title().isBlank()) {
                workout.setTitle(request.title());
            }
            if (request.calories() != null) {
                workout.setCalories(request.calories());
            }
            if (!request.duration().isBlank()) {
                workout.setDuration(request.duration());
            }
            workout.setBodyType(request.bodyType());
            workout.setAge(request.age());
            workout.setWorkoutType(request.workoutType());
            workout.setGender(request.gender());

            return workoutRepository.save(workout);
        }
        throw new OperationNotPermittedException("You do not have permission to edit this workout");
    }

    public Workout likeWorkout(Long workoutId, Authentication connectedUser) {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(()-> new EntityNotFoundException("Workout not found"));

        Optional<WorkoutLikes> optionalLike = likeRepository.findByUserId(connectedUser.getName(), workoutId);
        if (optionalLike.isPresent()) {
            WorkoutLikes workoutLikes = optionalLike.get();
            workout.setLikeCount(workout.getLikeCount() - 1);
            likeRepository.delete(workoutLikes);
        }
        else {
            WorkoutLikes workoutLikes = new WorkoutLikes();
            likeRepository.save(workoutLikes);
            workout.setLikeCount(workout.getLikeCount()+1);
        }
        return workoutRepository.save(workout);
    }

    public Workout uploadCover(Long workoutId, MultipartFile file, Authentication connectedUser) throws IOException {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(()-> new EntityNotFoundException("Workout not found"));

        if (workout.getCreatedBy().equals(connectedUser.getName())) {

            var image = imageUploadService.uploadFile(file);
            workout.setCoverImage(image);
            return workoutRepository.save(workout);
        }
        throw new OperationNotPermittedException("You do not have permission to upload cover");
    }

}
