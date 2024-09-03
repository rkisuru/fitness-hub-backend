package com.rkisuru.fitnesshub.repository;

import com.rkisuru.fitnesshub.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
