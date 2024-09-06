package com.rkisuru.fitnesshub.repository;

import com.rkisuru.fitnesshub.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
