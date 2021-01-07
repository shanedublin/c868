package com.example.circfit.database

import androidx.room.*
import com.example.circfit.entities.YourExercise
import com.example.circfit.entities.YourExerciseWithSets

@Dao
interface YourExerciseDao {


    @Transaction
    @Query("select * from YourExercise where workoutId =(:id)")
    fun findById(id: Long): MutableList<YourExercise>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(yourExercise: YourExercise): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(yourExercise: MutableList<YourExerciseWithSets>) {
        yourExercise.forEach { e -> insert(e.yourExercise) }
    }




    @Delete
    fun delete(yourExercise: YourExercise)

}