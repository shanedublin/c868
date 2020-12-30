package com.example.circfit.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ExerciseWithTags(
    @Embedded val exercise: Exercise,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "tagId",
        associateBy = Junction(ExerciseTagCrossRef::class)
    )
    val tags: List<Tag>
)
