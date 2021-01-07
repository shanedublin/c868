package com.example.circfit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.circfit.adapter.WorkoutYourExerciseAdapter
import com.example.circfit.database.CirFitDatabase
import com.example.circfit.entities.*
import com.example.circfit.util.DateListener
import com.example.circfit.util.MyDateUtil.dtf
import java.time.LocalDate

class WorkoutActivity : BaseMenuActivity() {
    val tag: String? = this::class.simpleName

    var workoutDataModel = WorkoutDataModel( Workout(null, LocalDate.now(), ""), ArrayList())

    lateinit var dateText: EditText
    lateinit var notesText: EditText
    lateinit var workoutExerciseRecyclerView: RecyclerView
    lateinit var workoutYourExerciseAdapter: WorkoutYourExerciseAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)
        dateText =  findViewById(R.id.workout_date_input)
        notesText = findViewById(R.id.workout_notes_input)
        workoutExerciseRecyclerView = findViewById(R.id.workout_exercise_recycler_view)
        val id = intent.getLongExtra("id", -1L)
        Log.d(tag, "id: $id");
        if( id > 0) {
            Log.d(tag, "");
            workoutDataModel.workout =  CirFitDatabase.getDBInstance(applicationContext).workoutDao().findWorkoutById(id)
            workoutDataModel.setExercises(CirFitDatabase.getDBInstance(applicationContext).yourExerciseDao().findById(id))
        }


        val date = intent.getSerializableExtra("date")

        if(date != null ) {
            workoutDataModel.workout.date = date  as LocalDate
        }

        var workout = workoutDataModel.workout

        DateListener(dateText, this) { localDate -> workout.date = localDate }
        notesText.setText(workout.notes)

        Log.d(tag, workoutDataModel.yourExerciseList?.toTypedArray().contentToString())
        workoutYourExerciseAdapter = WorkoutYourExerciseAdapter(workoutDataModel)
        workoutExerciseRecyclerView.layoutManager = LinearLayoutManager(this)
        workoutExerciseRecyclerView.adapter = workoutYourExerciseAdapter;

        if(workoutDataModel.workout.date!= null) {
            dateText.setText(workoutDataModel.workout.date.format(dtf))
        }

    }


    fun addYourExercise(v: View) {
        var ye = YourExercise(null, workoutDataModel.workout.workoutId,null,null);
        val id = CirFitDatabase.getDBInstance(applicationContext).yourExerciseDao().insert(ye);
        ye.yourExerciseId = id;
        Log.d(tag, ye.toString())

        var sets = ArrayList<ExerciseSet>()
        while (sets.size < 10)  {
            sets.add(ExerciseSet(null, id, sets.size.toLong(),null))
        }

        var yes = YourExerciseWithSets(ye);
        yes.sets = sets

        workoutYourExerciseAdapter.dataModel.yourExerciseList.add(yes)
        workoutYourExerciseAdapter.notifyDataSetChanged();
    }

    fun save(v: View){

        val dbInstance = CirFitDatabase.getDBInstance(applicationContext)
        Log.d(tag, workoutDataModel.toString())
        workoutDataModel.workout.notes = notesText.text.toString()
        dbInstance.workoutDao().insert(workoutDataModel.workout)
        dbInstance.yourExerciseDao().insertAll(workoutDataModel.yourExerciseList)

        workoutYourExerciseAdapter.dataModel.yourExerciseList.forEach{ e -> dbInstance.exerciseSetDao().insertAll(e.sets)}

        finish()
    }


    fun delete(v: View) {
        val dbInstance = CirFitDatabase.getDBInstance(applicationContext)
        workoutDataModel.yourExerciseList
            .forEach{yourExercise ->
                yourExercise.sets.forEach{s ->
                    dbInstance.exerciseSetDao().delete(s)
                }
                dbInstance.yourExerciseDao().delete(yourExercise.yourExercise)
            }
        dbInstance.workoutDao().delete(workoutDataModel.workout)
        finish()
    }
}