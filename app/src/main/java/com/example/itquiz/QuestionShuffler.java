package com.example.itquiz;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;


public class QuestionShuffler extends AppCompatActivity {
    public ArrayList<Question> shufflerQuestions(ArrayList<Question>questionList){
        Collections.shuffle(questionList);
        return questionList;
    }
    public ArrayList<String> shufflerAnswerMC(Question question){
        ArrayList<String> listans = new ArrayList<>();
        listans.add(question.getAnswer());
        listans.add(question.getOption1());
        listans.add(question.getOption2());
        listans.add(question.getOption3());

        Collections.shuffle(listans);
        return listans;
    }

    public ArrayList<String> shufflerAnswerTF(Question question){
        ArrayList<String> listans = new ArrayList<>();
        listans.add(question.getAnswer());
        listans.add(question.getOption1());

        Collections.shuffle(listans);
        return listans;
    }


}
