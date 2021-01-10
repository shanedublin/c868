package com.example.circfit.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.circfit.*
import com.example.circfit.database.CirFitDatabase
import com.example.circfit.entities.YourExercise
import com.example.circfit.entities.YourExerciseWithSets
import com.example.circfit.util.MyDateUtil.dtf

class ExerciseReportAdapter(
    val list: List<YourExercise>,
    val context: Context
) :
    RecyclerView.Adapter<ExerciseReportAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_exercise_report, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val reportDate: TextView = view.findViewById(R.id.report_date)
        val reportSets: TextView = view.findViewById(R.id.report_sets)
        val reportReps: TextView = view.findViewById(R.id.report_reps)
        val reportNotes: TextView = view.findViewById(R.id.report_note)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        if(item.workoutId != null) {
            val workout = CirFitDatabase.getDBInstance(context).workoutDao().findWorkoutById(item.workoutId!!)
            holder.reportDate.text = workout.date.format(dtf);
        }

        holder.reportNotes.text = item.notes

        if(item.yourExerciseId != null) {
            val sets = CirFitDatabase.getDBInstance(context).exerciseSetDao().findById(item.yourExerciseId!!)
            holder.reportSets.text = sets
                .filter { s -> s.reps != null }
                .filter { s -> s.reps!! > 0L }
                .count().toString();
            holder.reportReps.text = sets
                .filter { s -> s.reps != null }
                .filter { s -> s.reps!! > 0L }
                .sumBy { it.reps!!.toInt() }
                .toString()
        }

    }

}
