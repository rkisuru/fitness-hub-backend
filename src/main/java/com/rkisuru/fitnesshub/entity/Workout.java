package com.rkisuru.fitnesshub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rkisuru.fitnesshub.enums.Age;
import com.rkisuru.fitnesshub.enums.BodyType;
import com.rkisuru.fitnesshub.enums.Gender;
import com.rkisuru.fitnesshub.enums.WorkoutType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String duration;
    private Integer calories;
    private String coverImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BodyType bodyType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Age age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkoutType workoutType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @OneToMany(mappedBy = "workout")
    @JsonIgnore
    private List<Exercise> exercises;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "workout")
    @JsonIgnore
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private List<Like> likes;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedAt;

    private int viewCount;
    private int likeCount;

}
