package com.example.circfit.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.circfit.*

class CompletedExerciseAdapter(
    val exerciseDataModel: ExerciseDataModel,
    val exerciseListActivity: ExerciseListActivity
) :
    RecyclerView.Adapter<CompletedExerciseAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_completed_exercise, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return exerciseDataModel.list.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val exerciseLabel: TextView = view.findViewById(R.id.exercise_fragment_type_name_label)
        val exerciseDescription: TextView = view.findViewById(R.id.exercise_type_description_label)
        val constraintLayout: ConstraintLayout = view.findViewById(R.id.exercise_fragment_ConstraintLayout)

        override fun toString(): String {
            return super.toString() + " '" + exerciseLabel.text + "'"
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.exerciseLabel.text = exerciseDataModel.list[position].name
        holder.exerciseDescription.text = exerciseDataModel.list[position].description

        holder.constraintLayout.setOnClickListener{
            val intent  = Intent(exerciseListActivity.applicationContext, ExerciseReportActivity::class.java)
            intent.putExtra("exerciseId", exerciseDataModel.list[position].exerciseId)
            exerciseListActivity.startActivity(intent);
        }
    }

}
