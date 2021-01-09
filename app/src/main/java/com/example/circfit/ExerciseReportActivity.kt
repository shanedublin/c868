package com.example.circfit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.circfit.adapter.ExerciseReportAdapter
import com.example.circfit.database.CirFitDatabase

class ExerciseReportActivity : BaseMenuActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var exereciseName: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_report)
        recyclerView = findViewById(R.id.exercise_report_recyclerview)
        exereciseName = findViewById(R.id.exercise_report_exercise_name)
        recyclerView.layoutManager  = LinearLayoutManager(this)

        val exerciseId = intent.getLongExtra("exerciseId", 0L)
        Log.d(tag, "ExerciseId: $exerciseId")
        val list = CirFitDatabase.getDBInstance(applicationContext).yourExerciseDao().findByExerciseId(exerciseId)

        val exercise = CirFitDatabase.getDBInstance(applicationContext).exerciseDao().findById(exerciseId)

        exereciseName.text = exercise.name

        recyclerView.adapter = ExerciseReportAdapter(list,applicationContext)

    }
}