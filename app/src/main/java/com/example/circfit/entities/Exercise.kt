package com.example.circfit.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Exercise (
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Long,
    val name: String,
    val description: String
)