package com.example.circfit.database

import android.content.Context
import com.example.circfit.entities.*
import java.time.LocalDate

class FakeData {

    companion object {

        lateinit var tagDao: TagDao
        lateinit var workoutDao: WorkoutDao
        lateinit var exerciseDao: ExerciseDao
        lateinit var yourExerciseDao: YourExerciseDao
        fun prepopulateDatabase(c: Context) {
            val db = CirFitDatabase.getDBInstance(c)
            tagDao = db.tagDao()
            workoutDao = db.workoutDao()
            exerciseDao = db.exerciseDao()
            yourExerciseDao = db.yourExerciseDao()

            createTag(1,"Drill")
            createTag(2,"Skill")
            createTag(3,"Exercise")

            createExercise(1,"Pushups","do a pushup")
            createWorkout(1)

        }

        fun createTag(id: Long, name: String) {
            val t = Tag(id, name)
            tagDao.insert(t);
        }

        fun createExercise(id:Long , name: String, description: String) {
            val e = Exercise(id,name,description)
            exerciseDao.insert(e)
        }

        fun createWorkout(id:Long ){
            val w = Workout(id, LocalDate.now(),"Test")
            val yourExercise = YourExercise(id,1,1,"Testing Workout")

            yourExerciseDao.insert(yourExercise);
            workoutDao.insert(w)
        }

    }
}