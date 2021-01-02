package com.example.circfit.database

import androidx.room.*
import com.example.circfit.entities.ExerciseSet
import com.example.circfit.entities.WorkoutWithYourExercise
import kotlin.collections.Set as Set1

@Dao
interface ExerciseSetDao {

    @Query("SELECT * FROM  ExerciseSet")
    fun getAll(): List<ExerciseSet>

    @Query("select * from ExerciseSet where yourExerciseId =(:id)")
    fun findById(id: Long): List<ExerciseSet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(set: ExerciseSet)

    @Delete
    fun delete(set: ExerciseSet)

}