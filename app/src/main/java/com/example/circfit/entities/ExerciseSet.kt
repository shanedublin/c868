package com.example.circfit.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExerciseSet(
   @PrimaryKey(autoGenerate = true)
   var setId: Long?,
   var yourExerciseId: Long,
   var order: Long?,
   var reps: Long?)
