package com.example.circfit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.circfit.adapter.ExerciseReportAdapter
import com.example.circfit.database.CirFitDatabase
import com.example.circfit.util.MyDateUtil.dtf2
import java.time.LocalDateTime

class ExerciseReportActivity : BaseMenuActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var exerciseName: TextView
    lateinit var reportDate: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_exercise_report)
        recyclerView = findViewById(R.id.exercise_report_recyclerview)
        exerciseName = findViewById(R.id.exercise_report_exercise_name)

        reportDate = findViewById(R.id.exercise_report_date)
        recyclerView.layoutManager  = LinearLayoutManager(this)

        val exerciseId = intent.getLongExtra("exerciseId", 0L)
        Log.d(tag, "ExerciseId: $exerciseId")
        val list = CirFitDatabase.getDBInstance(applicationContext).yourExerciseDao().findByExerciseId(exerciseId)
        val exercise = CirFitDatabase.getDBInstance(applicationContext).exerciseDao().findById(exerciseId)

        exerciseName.text = exercise.name
        recyclerView.adapter = ExerciseReportAdapter(list, applicationContext)

        reportDate.text = LocalDateTime.now().format(dtf2)


    }
}