package com.example.circfit.database

import android.content.Context
import com.example.circfit.entities.*
import java.time.LocalDate
import java.util.concurrent.atomic.AtomicLong

class FakeData {

    companion object {

        lateinit var tagDao: TagDao
        lateinit var workoutDao: WorkoutDao
        lateinit var exerciseDao: ExerciseDao
        lateinit var exerciseSetDao: ExerciseSetDao
        lateinit var yourExerciseDao: YourExerciseDao
        val tagInt = AtomicLong(1)
        val exerciseInt = AtomicLong(1)
        val workoutInt = AtomicLong(1)
        val yourExerciseInt = AtomicLong(1)
        val setInt = AtomicLong(1)

        fun prepopulateDatabase(c: Context) {
            val db = CirFitDatabase.getDBInstance(c)
            tagDao = db.tagDao()
            exerciseSetDao = db.exerciseSetDao()
            workoutDao = db.workoutDao()
            exerciseDao = db.exerciseDao()
            yourExerciseDao = db.yourExerciseDao()
            
            createTag("Drill")
            createTag("Skill")
            createTag("Exercise")

            createExercise("Push Ups","Do a push up")
            createExercise("Pull Ups","Grab the bar with your knuckles facing towards your face and pull your chest to the bar")
            createExercise("Handstand Wall Hold","Hold a handstand with your belly facing the wall")
            createExercise("Handstand Tuck-Up","Tuck up into a handstand")
            createExercise("Leg lifts","With straight arm, lift your toes up to the bar or w/e you are holding")
            createExercise("Shrugs","Hold onto the bar and shrug your shoulders down")
            createExercise("Skin the cat","Wrap wrists in straps and invert backwards till you reach end of motion")

            createWorkout(
                LocalDate.of(2021,1,1),
                arrayOf(1L,2L,3L), arrayOf(1L,1L,1L))

            createWorkout(
                LocalDate.of(2021,1,2),
                arrayOf(4L,5L,6L), arrayOf(1L,1L,1L))

            createWorkout(
                LocalDate.of(2021,1,3),
                arrayOf(1L,2L,3L), arrayOf(2L,2L,2L,2L))

            createWorkout(
                LocalDate.of(2021,1,4),
                arrayOf(4L,5L,6L), arrayOf(2L,2L,2L,2L))

            createWorkout(
                LocalDate.of(2021,1,5),
                arrayOf(1L,2L,3L), arrayOf(3L,3L,3L,3L,3L))

            createWorkout(
                LocalDate.of(2021,1,6),
                arrayOf(4L,5L,6L), arrayOf(3L,3L,3L,3L,3L))

        }

        fun createTag( name: String) {
            val id = tagInt.getAndIncrement()
            val t = Tag(id, name)
            tagDao.insert(t);
        }

        fun createExercise(name: String, description: String) {
            val e = Exercise(exerciseInt.getAndIncrement(), name,description)
            exerciseDao.insert(e)
        }

        fun createSet(yourExerciseId: Long ,order:Long, reps: Long){
            val setId = setInt.getAndIncrement()
            val s = ExerciseSet(setId, yourExerciseId, order, reps)
            exerciseSetDao.insert(s)
        }

        fun createWorkout(date: LocalDate, exercises: Array<Long>, reps: Array<Long>){
            val workoutId = workoutInt.getAndIncrement()
            val w = Workout(workoutId, date,"Test")
            workoutDao.insert(w)

            exercises.forEach { exerciseId ->  createYourExercise(workoutId, exerciseId, reps)}
        }

        fun createYourExercise( workoutId: Long, exerciseId:Long, reps:Array<Long> ){
            val yourExerciseId = yourExerciseInt.getAndIncrement()
            val yourExercise = YourExercise(yourExerciseId, workoutId, exerciseId,"notes...")
            yourExerciseDao.insert(yourExercise)

            reps.forEachIndexed{ index, reps -> createSet(yourExerciseId, index.toLong(), reps) }

        }


    }
}