package com.example.circfit.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.circfit.entities.Workout
import com.example.circfit.entities.WorkoutWithYourExercise

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM  workout")
    fun getAll(): List<Workout>

    @Transaction
    @Query("select * from workout where workoutId =(:id)")
    fun findById(id: Long): WorkoutWithYourExercise


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(workout: Workout)

    @Delete
    fun delete(workout: Workout)

}