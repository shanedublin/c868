package com.example.circfit.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.circfit.R
import com.example.circfit.R.id.exercise_select_input
import com.example.circfit.database.CirFitDatabase
import com.example.circfit.entities.ExerciseSet
import com.example.circfit.entities.WorkoutDataModel

class WorkoutYourExerciseAdapter(
    val dataModel: WorkoutDataModel
    )
    : RecyclerView.Adapter<WorkoutYourExerciseAdapter.ViewHolder>() {

    val tag: String? = this::class.simpleName

    class ViewHolder(view: View, context: Context): RecyclerView.ViewHolder(view) {
        val notes: EditText = view.findViewById(R.id.set_notes_input)
        val exerciseAutoText: AppCompatAutoCompleteTextView = view.findViewById(exercise_select_input)
        val exercises =  CirFitDatabase.getDBInstance(context).exerciseDao().getAll();
        val strings = exercises.map { o -> o.name }
        val context = context
        val yourExerciseSetsRecyclerView: RecyclerView = view.findViewById(R.id.your_exercise_sets_recycler_view)
        lateinit var thing: MyExerciseSetRecyclerViewAdapter

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_workout_your_exercise, parent,false)
        return ViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val yourExerciseWithSets = dataModel.yourExerciseList[position]
        holder.notes.setText(yourExerciseWithSets.yourExercise.notes)

        holder.notes.doAfterTextChanged { text-> yourExerciseWithSets.yourExercise.notes = text.toString() }

        val exerciseListArrayAdapter: ArrayAdapter<String> = ArrayAdapter(holder.context, android.R.layout.select_dialog_item, holder.strings)
        holder.exerciseAutoText.setAdapter(exerciseListArrayAdapter)
        holder.exerciseAutoText.threshold = 1
        var exercise = holder.exercises.find { e -> e.exerciseId == yourExerciseWithSets.yourExercise.exerciseId}
        holder.exerciseAutoText.setText(exercise?.name)


        holder.exerciseAutoText.doAfterTextChanged { text ->
            val t = text.toString()
            var exercise =  holder.exercises.find { e -> e.name == t}
            if(exercise != null) {
                yourExerciseWithSets.yourExercise.exerciseId = exercise.exerciseId
            } else{
                yourExerciseWithSets.yourExercise.exerciseId = null
            }
            Log.d(tag, text.toString())
        }




        Log.d(tag, yourExerciseWithSets.toString())
        var id = -1L
        id = yourExerciseWithSets.yourExercise.yourExerciseId!!
        var sets = CirFitDatabase.getDBInstance(holder.context).exerciseSetDao().findById(id)
        Log.d(tag, sets.toTypedArray().contentToString())
        while (sets.size < 10)  {
            sets =  sets.plus(ExerciseSet(null, yourExerciseWithSets.yourExercise.yourExerciseId, sets.size.toLong(),null))
        }

        yourExerciseWithSets.sets = sets;
        holder.thing = MyExerciseSetRecyclerViewAdapter(sets.toMutableList(), dataModel)
        holder.yourExerciseSetsRecyclerView.adapter = holder.thing


    }

    fun getExercise(holder: ViewHolder, name: String) {
        holder.exercises.find { e -> e.name == name}
    }

    override fun getItemCount() = dataModel.yourExerciseList.size
}