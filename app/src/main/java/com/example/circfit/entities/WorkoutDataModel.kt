package com.example.circfit.entities

class WorkoutDataModel(
    var workout: Workout,
    var yourExerciseList: MutableList<YourExerciseWithSets>
)
{
    fun setExercises(list: MutableList<YourExercise>) {
        yourExerciseList.clear()
        list.map { e -> YourExerciseWithSets(e) }.toCollection(yourExerciseList)
    }
}
