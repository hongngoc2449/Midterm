package com.example.doanhongngoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizAdapter extends ArrayAdapter<Quiz> {
    private Context context;
    private ArrayList<Quiz> quizArrayList;
    public QuizAdapter(Context context,ArrayList<Quiz> quizArrayList){
        super(context,0,quizArrayList);
        this.context = context;
        this.quizArrayList = quizArrayList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,parent,false);
        }
        Quiz quiz = quizArrayList.get(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(quiz.getQuestion() +"(" + quiz.getAnswer() + ")");
        return convertView;
    }
}
