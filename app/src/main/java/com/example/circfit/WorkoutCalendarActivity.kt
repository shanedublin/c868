package com.example.circfit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import com.example.circfit.R.id
import com.example.circfit.database.CirFitDatabase
import java.time.LocalDate


class WorkoutCalendarActivity : BaseMenuActivity() {


    //val tag: String? = this::class.simpleName
    lateinit var calendarView: CalendarView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_calendar)
        calendarView = findViewById(id.calendarView)

        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            Log.d(tag, "year: $year month: $month day: $day")

            val date = LocalDate.of(year,month + 1,day);
            val workout = CirFitDatabase.getDBInstance(applicationContext).workoutDao().findWorkoutByDate(date)
            val intent  = Intent(this, WorkoutActivity::class.java)
            if(workout != null) {
                intent.putExtra("id",workout.workoutId)
            } else {
                intent.putExtra("date", date)
            }
                startActivity(intent)
        }
    }


}