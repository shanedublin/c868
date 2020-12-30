package com.example.circfit.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.circfit.entities.*

@Database(entities = [Exercise::class, ExerciseTagCrossRef::class, Tag::class, Workout::class, YourExercise::class], version = 1 )
abstract class CircFitDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun tagDao(): TagDao
    abstract fun workoutDao(): WorkoutDao



}