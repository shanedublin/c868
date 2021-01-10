package com.example.circfit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.circfit.adapter.ExerciseAdapter
import com.example.circfit.database.CirFitDatabase

class ExerciseListActivity : BaseMenuActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var exerciseSearch: TextView
    lateinit var exerciseDataModel: ExerciseDataModel
    lateinit var exerciseAdapter: ExerciseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_list)

        recyclerView = findViewById(R.id.exercise_list_recycler_view)
        exerciseSearch = findViewById(R.id.exercise_list_search_text)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val list = CirFitDatabase.getDBInstance(applicationContext).exerciseDao()
            .findByExerciseByName("")
        exerciseDataModel = ExerciseDataModel(list.toMutableList())

        exerciseAdapter = ExerciseAdapter(exerciseDataModel,this)
        recyclerView.adapter = exerciseAdapter


        exerciseSearch.doAfterTextChanged { text ->
            Log.d(tag, "text: $text")
            val findByExercise = CirFitDatabase.getDBInstance(applicationContext).exerciseDao()
                .findByExerciseByName(text.toString())
            Log.d(tag, "exercise $findByExercise")
            exerciseDataModel.list.clear()
            exerciseDataModel.list.addAll(findByExercise)
            exerciseAdapter.notifyDataSetChanged()
        }

    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "On resume")
        val list = CirFitDatabase.getDBInstance(applicationContext).exerciseDao()
            .findByExerciseByName("")
        exerciseDataModel.list.clear()
        exerciseDataModel.list.addAll(list)
        exerciseAdapter.notifyDataSetChanged()
    }

    fun newExercise(v:View){
        val intent  = Intent(applicationContext, ExerciseActivity::class.java)
        startActivity(intent);
    }
}