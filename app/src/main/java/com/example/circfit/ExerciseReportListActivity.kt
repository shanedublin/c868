package com.example.circfit

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.circfit.adapter.CompletedExerciseAdapter
import com.example.circfit.database.CirFitDatabase

class ExerciseReportListActivity : BaseMenuActivity() {

    lateinit var searchText: EditText
    lateinit var recyclerView: RecyclerView
    lateinit var exerciseDataModel: ExerciseDataModel
    lateinit var completedExerciseAdapter: CompletedExerciseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_exercise_list)
        searchText = findViewById(R.id.your_exercises_search)
        recyclerView = findViewById(R.id.your_exercises_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val list = CirFitDatabase.getDBInstance(applicationContext).exerciseDao()
            .findByYourExerciseJoin("")
        exerciseDataModel = ExerciseDataModel(list.toMutableList())

        completedExerciseAdapter = CompletedExerciseAdapter(exerciseDataModel,this)
        recyclerView.adapter = completedExerciseAdapter


        searchText.doAfterTextChanged { text ->
            Log.d(tag, "text: $text")
            val findByExercise = CirFitDatabase.getDBInstance(applicationContext).exerciseDao()
                .findByYourExerciseJoin(text.toString());
            Log.d(tag, "exercise $findByExercise")
            exerciseDataModel.list.clear();
            exerciseDataModel.list.addAll(findByExercise)
            completedExerciseAdapter.notifyDataSetChanged()
        }

    }
}