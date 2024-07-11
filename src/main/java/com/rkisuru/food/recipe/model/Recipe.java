package com.rkisuru.food.recipe.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String recipeName;

    @Column(nullable = false)
    private String recipeContent;

    private String recipeImage;

    @ManyToOne
    private User user;

    private boolean vegan;
    private LocalDateTime createdAt;

    private List<Long> likes = new ArrayList<>();
}
