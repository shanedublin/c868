package com.example.circfit.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation


data class WorkoutWithYourExercise (
    @Embedded val workout: Workout,
    @Relation(
        parentColumn = "workoutId",
        entityColumn = "yourExerciseId"
    )
    val exercises: List<YourExercise>
)