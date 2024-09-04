package com.rkisuru.fitnesshub.service;

import com.rkisuru.fitnesshub.dto.WorkoutRequest;
import com.rkisuru.fitnesshub.entity.Workout;
import com.rkisuru.fitnesshub.mapper.DtoMapper;
import com.rkisuru.fitnesshub.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final DtoMapper mapper;

    public Long saveWorkout(WorkoutRequest request) {

        Workout workout = mapper.toWorkout(request);
        return workoutRepository.save(workout).getId();
    }


    
}
