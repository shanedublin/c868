package com.example.circfit.entities

import androidx.room.PrimaryKey

class YourExercise (

    @PrimaryKey(autoGenerate = true)
    val yourExerciseId: Long,
    val workoutId: Long,
    val exerciseId: Long,
    val notes: String
)