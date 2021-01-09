package com.example.circfit

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.circfit.entities.Workout

class MainActivity : BaseMenuActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        val intent = Intent(this,WorkoutActivity::class.java).apply {
            this.putExtra("id",1L)
        }
        startActivity(intent)

        */
    }

    fun openNewWorkout(v: View){
        val intent  = Intent(this, WorkoutActivity::class.java)
        //intent.putExtra("id", 1L)
        startActivity(intent)
    }

    fun pastWorkout(v: View){
        val intent  = Intent(this, WorkoutCalendarActivity::class.java)
        startActivity(intent);
    }

    fun exerciseStats(v: View){
        val intent  = Intent(this, ExerciseListActivity::class.java)
        startActivity(intent);
    }
}