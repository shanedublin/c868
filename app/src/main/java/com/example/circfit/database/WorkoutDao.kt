package com.example.circfit.database

import android.util.Log
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

    @Transaction
    @Query("select * from workout where workoutId =(:id)")
    fun findWorkoutById(id: Long): Workout




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(workout: Workout)


    fun insert(workoutWithYourExercise: WorkoutWithYourExercise) {
        insert(workoutWithYourExercise.workout)
    }

    @Delete
    fun delete(workout: Workout)

}