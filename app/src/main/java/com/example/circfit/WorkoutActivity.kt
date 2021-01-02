package com.example.circfit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.circfit.database.CirFitDatabase
import com.example.circfit.entities.ExerciseSet
import com.example.circfit.entities.Workout
import com.example.circfit.entities.WorkoutWithYourExercise
import com.example.circfit.entities.WorkoutYourExerciseAdapter
import com.example.circfit.util.DateListener
import java.time.LocalDate

class WorkoutActivity : BaseMenuActivity() {
    val tag: String? = this::class.simpleName

    lateinit var workout: WorkoutWithYourExercise

    lateinit var dateText: EditText
    lateinit var notesText: EditText
    lateinit var workoutExerciseRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)
        dateText =  findViewById(R.id.workout_date_input)
        notesText = findViewById(R.id.workout_notes_input)
        workoutExerciseRecyclerView = findViewById(R.id.workout_exercise_recycler_view)

        val id = intent.getLongExtra("id", -1L)
        workout = if( id > 0) {
            Log.d(tag, "");
            CirFitDatabase.getDBInstance(applicationContext).workoutDao().findById(id);
        } else{
            val v = Workout(null, LocalDate.now(), "")
            WorkoutWithYourExercise(v, listOf())
        }

        DateListener(dateText, this) { localDate -> workout.workout.date = localDate }
        notesText.setText(workout.workout.notes)

        Log.d(tag, workout.exercises.toTypedArray().contentToString())
        val workoutYourExerciseAdapter = WorkoutYourExerciseAdapter(workout.exercises, this)
        workoutExerciseRecyclerView.layoutManager = LinearLayoutManager(this)
        workoutExerciseRecyclerView.adapter = workoutYourExerciseAdapter;
        //recyclerView.layoutManager(LinearLayoutManager(this))


    }

    fun focusSetItem(
        v: View,
        item: ExerciseSet,
        myExerciseSetRecyclerViewAdapter: MyExerciseSetRecyclerViewAdapter
    ){
        Log.d(tag, "smd")
        Log.d(tag, v.parent.toString())
        Log.d(tag, item.toString())
        Log.d(tag, myExerciseSetRecyclerViewAdapter.values.toTypedArray().contentToString())



    }

    fun foo(v: View, item: ExerciseSet){
        Log.d(tag, "smd")
        Log.d(tag, v.parent.toString())
        Log.d(tag, item.toString())



    }

    fun save(v: View){


    }


    fun delete(v: View) {

    }
}