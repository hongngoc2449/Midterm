package com.example.doanhongngoc;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.doanhongngoc.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Quiz> questions;
    private ArrayList<Quiz> quizSubmitList;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getQuestion().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.questTv.setText(s);
            }
        });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        questions = new ArrayList<>();
        questions.add(new Quiz("Chỉ cần 100 tín chỉ để tốt nghiệp ngành CSE", "False"));
        questions.add(new Quiz("52 Lê Duẩn là địa chỉ trường VNUK","True"));
        questions.add(new Quiz("Mùa Xuân là Spring","True"));
        questions.add(new Quiz("Mùa hạ Winter","False"));
        questions.add(new Quiz("Tia chớp được nhìn thấy trước khi nó được nghe thấy vì ánh sáng truyền nhanh hơn âm thanh.","True"));
        questions.add(new Quiz("Mùa xuân là Summer","False"));
        questions.add(new Quiz("Melbourne là thủ đô của Úc.","False"));
        questions.add(new Quiz("Núi Phú Sĩ là ngọn núi cao nhất ở Nhật Bản.","True"));
        questions.add(new Quiz("Google ban đầu được gọi là BackRub.","False"));
        questions.add(new Quiz("Cà chua là trái cây","True"));


        binding.questTv.setText(questions.get(0).getQuestion());

        quizSubmitList = new ArrayList<>();

        binding.falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quest = binding.questTv.getText().toString();
                boolean isCorrect = checkAnswer(quest,"False");
                if(isCorrect){
                    quizSubmitList.add(new Quiz(quest,"Correct"));
                    binding.falseButton.setBackgroundColor(0xFF00FF00);
                }else{
                    quizSubmitList.add(new Quiz(quest,"Incorrect"));
                    binding.falseButton.setBackgroundColor(Color.rgb(255,0,0));
                }
            }
        });
        binding.trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quest = binding.questTv.getText().toString();
                boolean isCorrect = checkAnswer(quest,"True");
                if(isCorrect){
                    quizSubmitList.add(new Quiz(quest,"Correct"));
                    binding.trueButton.setBackgroundColor(0xFF00FF00);
                }else{
                    quizSubmitList.add(new Quiz(quest,"Incorrect"));
                    binding.trueButton.setBackgroundColor(Color.rgb(255,0,0));
                }
            }
        });
        binding.submutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putParcelableArrayListExtra("answerList",quizSubmitList);
                startActivity(intent);
            }
        });
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreviousQuestion();
            }
        });
        binding.forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextQuestion();
            }
        });
    }
    private void showNextQuestion() {
        for (int i = 0; i < questions.size() - 1; i++) {
            if (questions.get(i).getQuestion().equals(binding.questTv.getText())) {
                binding.questTv.setText(questions.get(i + 1).getQuestion());
                binding.falseButton.setBackgroundColor(0xFF673AB7);
                binding.trueButton.setBackgroundColor(0xFF673AB7);
                break;
            }
        }
    }
    private void showPreviousQuestion() {
        for (int i = 0; i < questions.size() - 1; i++) {
            if (questions.get(i).getQuestion().equals(binding.questTv.getText())) {
                binding.questTv.setText(questions.get(i - 1).getQuestion());
                binding.falseButton.setBackgroundColor(0xFF673AB7);
                binding.trueButton.setBackgroundColor(0xFF673AB7);
                break;
            }
        }
    }
    private boolean checkAnswer(String question,String answer){
        for(Quiz q : questions){
            if(q.getQuestion().equals(question)){
                if(q.getAnswer().equals(answer)){
                    return true;
                }
            }
        }return false;
    }
}