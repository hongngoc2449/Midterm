package com.example.doanhongngoc;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface QuizDao {
    @Query("SELECT * FROM Quiz")
    List<Quiz> getAll();
    @Insert
    void insert(Quiz... quizzes);
}
