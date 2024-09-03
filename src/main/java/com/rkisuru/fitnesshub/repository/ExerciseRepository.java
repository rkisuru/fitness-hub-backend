package com.rkisuru.fitnesshub.repository;

import com.rkisuru.fitnesshub.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
