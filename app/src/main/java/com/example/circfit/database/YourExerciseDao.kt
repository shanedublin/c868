package com.example.circfit.database

import androidx.room.*
import com.example.circfit.entities.Workout
import com.example.circfit.entities.WorkoutWithYourExercise
import com.example.circfit.entities.YourExercise

@Dao
interface YourExerciseDao {


    @Transaction
    @Query("select * from yourexercise where workoutId =(:id)")
    fun findById(id: Long): List<YourExercise>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(yourExercise: YourExercise)

    @Delete
    fun delete(yourExercise: YourExercise)

}