package com.example.circfit.database

import android.content.Context
import com.example.circfit.entities.*
import java.time.LocalDate

class FakeData {

    companion object {

        lateinit var tagDao: TagDao
        lateinit var workoutDao: WorkoutDao
        lateinit var exerciseDao: ExerciseDao
        lateinit var exerciseSetDao: ExerciseSetDao
        lateinit var yourExerciseDao: YourExerciseDao

        fun prepopulateDatabase(c: Context) {
            val db = CirFitDatabase.getDBInstance(c)
            tagDao = db.tagDao()
            exerciseSetDao = db.exerciseSetDao()
            workoutDao = db.workoutDao()
            exerciseDao = db.exerciseDao()
            yourExerciseDao = db.yourExerciseDao()

            createTag(1,"Drill")
            createTag(2,"Skill")
            createTag(3,"Exercise")

            createExercise(1,"Push Ups","do a pushup")
            createExercise(2,"Pull Ups","Grab the bar with your knuckles facing towards your face and pull your chest to the bar ")
            createExercise(3,"Handstand Wall Hold","Hold a handstand with your belly facing the wall")
            createExercise(4,"Handstand Tuck-Up","tuck up into a handstand")
            createExercise(5,"Leg lifts","with straight arm, lift your toes up to the bar or w/e you are holding")
            createExercise(6,"Shrugs","hold onto the bar and shrug your shoulders down")

            createWorkout(1)

            createYourExercise(1,1,1)
            createYourExercise(2,1,2)
            createYourExercise(3,1,5)

            createSet(1, 1,1,5)
            createSet(2, 1,2,4)
            createSet(3, 1,3,6)

        }

        fun createTag(id: Long, name: String) {
            val t = Tag(id, name)
            tagDao.insert(t);
        }

        fun createExercise(id:Long , name: String, description: String) {
            val e = Exercise(id,name,description)
            exerciseDao.insert(e)
        }

        fun createSet(setId:Long, yourExerciseId: Long ,order:Long, reps: Long){
            val s = ExerciseSet(setId, yourExerciseId, order, reps)
            exerciseSetDao.insert(s)
        }

        fun createWorkout(id:Long ){
            val w = Workout(id, LocalDate.now(),"Test")
            workoutDao.insert(w)
        }

        fun createYourExercise(yourExerciseId:Long, workoutId: Long, exerciseId:Long ){
            val yourExercise = YourExercise(yourExerciseId, workoutId, exerciseId,"notes...")
            yourExerciseDao.insert(yourExercise);
        }


    }
}