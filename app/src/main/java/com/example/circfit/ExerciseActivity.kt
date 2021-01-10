package com.example.circfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.circfit.database.CirFitDatabase
import com.example.circfit.entities.Exercise
import java.lang.Exception

class ExerciseActivity : BaseMenuActivity() {


    lateinit var exerciseNameText: EditText
    lateinit var exerciseDescriptionText: EditText
    var exercise: Exercise = Exercise(null,"","")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        exerciseDescriptionText= findViewById(R.id.exercise_description_text)
        exerciseNameText= findViewById(R.id.exercise_name_text)

        val id = intent.getLongExtra("exerciseId", -1L)
        Log.d(tag, "id: $id");
        if( id > 0) {
            Log.d(tag, "");
            exercise =  CirFitDatabase.getDBInstance(applicationContext).exerciseDao().findById(id)
            exerciseNameText.setText(exercise.name)
            exerciseDescriptionText.setText( exercise.description)
        }



    }


    fun save(v: View){

        exercise.name = exerciseNameText.text.toString()
        exercise.description = exerciseDescriptionText.text.toString()
        CirFitDatabase.getDBInstance(applicationContext).exerciseDao().insert(exercise)

        finish()


    }

    fun delete(v: View){


        try {
            val list = CirFitDatabase.getDBInstance(applicationContext).yourExerciseDao()
                .findByExerciseId(exercise.exerciseId!!)
            if(list.size > 0) {
                val toast =
                    Toast.makeText(applicationContext, "You have workouts with this exercise.\n Delete those first.", Toast.LENGTH_SHORT)
                toast.show()

            } else {
                CirFitDatabase.getDBInstance(applicationContext).exerciseDao().delete(exercise)
                finish()
            }
        } catch (e: Exception){

            // just means that the you are trying to delete and exercise that hasn't been saved yet
            finish()
        }

    }
}