package com.rkisuru.fitnesshub.repository;

import com.rkisuru.fitnesshub.entity.WorkoutLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<WorkoutLikes, Long> {

    @Query("SELECT likes FROM WorkoutLikes likes WHERE likes.createdBy= :email AND likes.workout.id=:workoutId")
    Optional<WorkoutLikes> findByUserId(@Param("email") String email, @Param("workoutId") Long workoutId);
}
