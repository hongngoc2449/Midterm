package com.example.doanhongngoc;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = Quiz.class,version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuizDao quizDao();
    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,AppDatabase.class,"quizzes").build();
        }return instance;
    }
}
