package com.example.doanhongngoc;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    private ArrayList<Quiz> submitList;
    private AppDatabase appDatabase;
    private QuizDao quizDao;
    private ArrayAdapter arrayAdapter;
    private ListView idList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        idList = findViewById(R.id.idList);
        submitList = getIntent().getParcelableArrayListExtra("answerList");
        arrayAdapter = new QuizAdapter(this,submitList);
        idList.setAdapter(arrayAdapter);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase = AppDatabase.getInstance(getApplicationContext());
                quizDao = appDatabase.quizDao();
                for(Quiz quiz : submitList){
                    quizDao.insert(new Quiz(quiz.getQuestion(),quiz.getAnswer()));
                }
            }
        });
    }
}