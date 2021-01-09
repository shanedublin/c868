package com.example.circfit.database

import androidx.room.*
import com.example.circfit.entities.YourExercise
import com.example.circfit.entities.YourExerciseWithSets

@Dao
interface YourExerciseDao {

    @Transaction
    @Query("select * from YourExercise where workoutId =(:id)")
    fun findByWorkoutId(id: Long): MutableList<YourExercise>

    @Query("select * from YourExercise where exerciseId =(:id)")
    fun findByExerciseId(id: Long): MutableList<YourExercise>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(yourExercise: YourExercise): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(yourExercise: MutableList<YourExerciseWithSets>) {
        yourExercise.forEach { e -> insert(e.yourExercise) }
    }

    @Query("select * from YourExercise ye , Exercise e where ye.yourExerciseId = e.exerciseId and e.name like '%'||:search||'%'")
    fun findByExercise(search: String): List<YourExercise>

    @Delete
    fun delete(yourExercise: YourExercise)

}