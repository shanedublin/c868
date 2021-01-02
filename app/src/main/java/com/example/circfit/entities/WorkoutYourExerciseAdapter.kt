package com.example.circfit.entities

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.circfit.MyExerciseSetRecyclerViewAdapter
import com.example.circfit.R
import com.example.circfit.R.id.exercise_select_input
import com.example.circfit.WorkoutActivity
import com.example.circfit.database.CirFitDatabase

class WorkoutYourExerciseAdapter(
    private val list: List<YourExercise>,
    private val workoutActivity: WorkoutActivity

    )
    : RecyclerView.Adapter<WorkoutYourExerciseAdapter.ViewHolder>() {

    val tag: String? = this::class.simpleName

    class ViewHolder(view: View, context: Context): RecyclerView.ViewHolder(view) {
        val notes: EditText = view.findViewById(R.id.set_notes_input)
        val blah: AppCompatAutoCompleteTextView = view.findViewById(exercise_select_input)
        val exercises =  CirFitDatabase.getDBInstance(context).exerciseDao().getAll();
        val strings = exercises.map { o -> o.name }
        val context = context
        val yourExerciseSetsRecyclerView: RecyclerView = view.findViewById(R.id.your_exercise_sets_recycler_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_workout_your_exercise, parent,false)
        return ViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blo = list[position]
        holder.notes.setText(list[position].notes)
        val array: ArrayAdapter<String> = ArrayAdapter(holder.context, android.R.layout.select_dialog_item, holder.strings)
        holder.blah.setAdapter(array)
        holder.blah.threshold = 1
        var exercise = holder.exercises.find { e -> e.exerciseId == list[position].exerciseId}
        holder.blah.setText(exercise?.name)

        Log.d(tag, list[position].toString())
        var sets = CirFitDatabase.getDBInstance(holder.context).exerciseSetDao().findById(list[position].yourExerciseId)
        Log.d(tag, sets.toTypedArray().contentToString())
        while (sets.size < 10)  {
            sets =  sets.plus(ExerciseSet(null, blo.yourExerciseId, null,null))
        }

        holder.yourExerciseSetsRecyclerView.adapter = MyExerciseSetRecyclerViewAdapter(sets.toMutableList(), workoutActivity,holder.yourExerciseSetsRecyclerView)


    }

    override fun getItemCount() = list.size
}