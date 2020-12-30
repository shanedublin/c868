package com.example.circfit.database

import androidx.room.*
import com.example.circfit.entities.Exercise

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM  exercise")
    fun getAll(): List<Exercise>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Exercise: Exercise)

    @Delete
    fun delete(Exercise: Exercise)

}