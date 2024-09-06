package com.rkisuru.fitnesshub.repository;

import com.rkisuru.fitnesshub.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("SELECT likes FROM Like likes WHERE likes.user.email= :userName AND likes.workout.id=:workoutId")
    Optional<Like> findByUserId(@Param("email") String email, @Param("workoutId") Long workoutId);
}
