package com.example.circfit.database

import androidx.room.*
import com.example.circfit.entities.Workout

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM  workout")
    fun getAll(): List<Workout>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(workout: Workout)

    @Delete
    fun delete(workout: Workout)

}