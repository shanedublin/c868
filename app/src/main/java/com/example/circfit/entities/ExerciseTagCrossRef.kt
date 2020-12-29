package com.example.circfit.entities

import androidx.room.Entity

@Entity(primaryKeys = ["exerciseId", "tagId"] )
data class ExerciseTagCrossRef(
    val exerciseId: Long,
    val tagId: Long
)
