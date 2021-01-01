package com.example.circfit.entities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.circfit.R

class WorkoutYourExerciseAdapter(private val list: List<YourExercise>)
    : RecyclerView.Adapter<WorkoutYourExerciseAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val notes: EditText = view.findViewById(R.id.set_notes_input)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_workout_your_exercise, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.notes.setText(list[position].notes)
    }

    override fun getItemCount() = list.size
}