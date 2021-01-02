package com.example.circfit.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.circfit.entities.Exercise;
import com.example.circfit.entities.ExerciseSet;
import com.example.circfit.entities.ExerciseTagCrossRef;
import com.example.circfit.entities.Tag;
import com.example.circfit.entities.Workout;
import com.example.circfit.entities.YourExercise;

@Database(entities = {Exercise.class, ExerciseTagCrossRef.class, Tag.class, Workout.class, YourExercise.class, ExerciseSet.class}, version = 1, exportSchema = false )
@TypeConverters({DateConverter.class})
public abstract class CirFitDatabase extends RoomDatabase {

    public abstract ExerciseDao exerciseDao();
    public abstract ExerciseSetDao exerciseSetDao();
    public abstract TagDao tagDao();
    public abstract  WorkoutDao workoutDao();
    public abstract  YourExerciseDao yourExerciseDao();

    private static volatile CirFitDatabase db;

    public static synchronized CirFitDatabase getDBInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, CirFitDatabase.class, "CirFitDatabase")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

            FakeData.Companion.prepopulateDatabase(context);
        }

        return db;
    }
}
