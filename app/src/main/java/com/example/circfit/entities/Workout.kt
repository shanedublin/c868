package com.example.circfit.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Workout (
    @PrimaryKey(autoGenerate = true)
    var workoutId: Long?,
    var date: LocalDate,
    var notes: String
)