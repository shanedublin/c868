package com.example.circfit.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Tag (
    @PrimaryKey(autoGenerate = true)
    val tagId: Long?,
    val name: String
)