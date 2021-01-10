package com.example.circfit.database

import androidx.room.*
import com.example.circfit.entities.Exercise
import com.example.circfit.entities.YourExercise

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM  exercise")
    fun getAll(): List<Exercise>

    @Query("select * from Exercise where exerciseId in( select distinct exerciseId from YourExercise) and name like '%'||:search||'%'")
    fun findByYourExerciseJoin(search: String): List<Exercise>


    @Query("select * from Exercise where name like '%'||:search||'%'")
    fun findByExerciseByName(search: String): List<Exercise>

    @Query("select * from Exercise e where exerciseId =(:id)")
    fun findById(id:Long): Exercise

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Exercise: Exercise)


    @Delete
    fun delete(Exercise: Exercise)

}