package com.example.circfit.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


class YourExerciseWithSets (
    var yourExercise: YourExercise
){
    lateinit var sets: List<ExerciseSet>
}