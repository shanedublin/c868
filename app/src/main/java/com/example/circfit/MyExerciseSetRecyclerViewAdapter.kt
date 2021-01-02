package com.example.circfit

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.circfit.entities.ExerciseSet


/**
 * [RecyclerView.Adapter] that can display a [ExerciseSet].
 * TODO: Replace the implementation with code for your data type.
 */
class MyExerciseSetRecyclerViewAdapter(
    val values: MutableList<ExerciseSet>,
    private val workoutActivity: WorkoutActivity,
    val yourExerciseSetsRecyclerView: RecyclerView
) : RecyclerView.Adapter<MyExerciseSetRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_exercise_set, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.setInput.text = item.reps?.toString()

        holder.setInput.onFocusChangeListener =
            OnFocusChangeListener { v, hasFocus ->
                if(!hasFocus){
                    workoutActivity.focusSetItem(v, item,this)
                }
            }


    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val setInput: TextView = view.findViewById(R.id.exercise_set_input)

        override fun toString(): String {
            return super.toString() + " '" + setInput.text + "'"
        }
    }

    override fun toString(): String {
        return "What in tornation";
    }
}