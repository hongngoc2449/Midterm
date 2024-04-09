package com.example.doanhongngoc;

import androidx.lifecycle.MutableLiveData;

public class ViewModel extends androidx.lifecycle.ViewModel {
private MutableLiveData<String> question;

    public MutableLiveData<String> getQuestion() {
        if(question == null){
            question = new MutableLiveData<>();
        }
        return question;
    }
}
