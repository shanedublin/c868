package com.example.circfit.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class YourExercise (

    @PrimaryKey(autoGenerate = true)
    val yourExerciseId: Long,
    val workoutId: Long,
    val exerciseId: Long,
    val notes: String
)