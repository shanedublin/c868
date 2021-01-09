package com.example.circfit.database

import androidx.room.*
import com.example.circfit.entities.Exercise
import com.example.circfit.entities.YourExercise

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM  exercise")
    fun getAll(): List<Exercise>

    @Query("select e.* from YourExercise ye , Exercise e where ye.yourExerciseId = e.exerciseId and e.name like '%'||:search||'%'")
    fun findByExercise(search: String): List<Exercise>

    @Query("select * from Exercise e where exerciseId =(:id)")
    fun findById(id:Long): Exercise

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Exercise: Exercise)


    @Delete
    fun delete(Exercise: Exercise)

}