package com.example.circfit.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class YourExercise (

    @PrimaryKey(autoGenerate = true)
    var yourExerciseId: Long?,
    var workoutId: Long?,
    var exerciseId: Long?,
    var notes: String?
)