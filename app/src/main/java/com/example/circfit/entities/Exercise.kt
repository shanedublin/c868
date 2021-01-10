package com.example.circfit.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Exercise  (
    @PrimaryKey(autoGenerate = true)
    var exerciseId: Long?,
    var name: String,
    var description: String
)