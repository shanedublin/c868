package com.example.circfit

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment


class ExerciseSetFragment : Fragment(R.layout.fragment_exercise_set) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(tag, "Created")
        super.onViewCreated(view, savedInstanceState)
    }
    fun foo(v: View){
        Log.d(tag,"smd" )
    }
}