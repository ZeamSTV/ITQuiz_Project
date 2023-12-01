package com.example.itquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity implements View.OnClickListener {
    Button ansA_button,ansB_button,ansC_button,ansD_button,next_button,pre_button;
    TextView questionTextView;
    TextView textIndex;
    public static int questionIndex = 0;

    public static ArrayList<Question> LISTQUESTION = new ArrayList<>();
    ArrayList<String> listans= new ArrayList();
    String selectedAnswer, rightAnswer;
    int numQuestions = LISTQUESTION.size();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        init();
        loadReview(questionIndex);
    }

    public void init(){
        textIndex = findViewById(R.id.textIndex);
        questionTextView= findViewById(R.id.textViewQuestion);
        ansA_button = findViewById(R.id.ansA_button);
        ansB_button = findViewById(R.id.ansB_button);
        ansC_button = findViewById(R.id.ansC_button);
        ansD_button = findViewById(R.id.ansD_button);
        next_button = findViewById(R.id.next_button);
        pre_button = findViewById(R.id.pre_button);

        ansA_button.setOnClickListener(this);
        ansB_button.setOnClickListener(this);
        ansC_button.setOnClickListener(this);
        ansD_button.setOnClickListener(this);
        next_button.setOnClickListener(this);
        pre_button.setOnClickListener(this);

        ansA_button.setEnabled(false);
        ansB_button.setEnabled(false);
        ansC_button.setEnabled(false);
        ansD_button.setEnabled(false);

    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.next_button)
            questionIndex++;
        if(clickedButton.getId() == R.id.pre_button)
            questionIndex--;
        if(questionIndex >= LISTQUESTION.size())
            finishReview();
        else  loadReview(questionIndex);

        if(questionIndex == 0)  pre_button.setVisibility(View.INVISIBLE);

    }

    public void loadReview(int questionIndex){
        De_Invisible();
        textIndex.setText("Question:"+ String.valueOf(questionIndex+1)+ "/"+numQuestions);
        ansA_button.setBackgroundColor(Color.rgb(239,174,62));
        ansB_button.setBackgroundColor(Color.rgb(239,174,62));
        ansC_button.setBackgroundColor(Color.rgb(239,174,62));
        ansD_button.setBackgroundColor(Color.rgb(239,174,62));


        Question question = LISTQUESTION.get(questionIndex);

        if(question.getIDquestion().equals("MC")) {
            loadMCQuestion(question);
        }
        else if (question.getIDquestion().equals("TF")) {
            loadTFQuestion(question);
        }
    }

    public void loadMCQuestion(Question question){
        QuestionShuffler temp = new QuestionShuffler();
        selectedAnswer = null;

        listans = temp.shufflerAnswerMC(question);

        rightAnswer = question.getAnswer();
        questionTextView.setText(question.getQuestion());

        ansA_button.setText(listans.get(0));
        ansB_button.setText(listans.get(1));
        ansC_button.setText(listans.get(2));
        ansD_button.setText(listans.get(3));

        String rightAnswer = question.getAnswer();
        String selectedAnswer = LISTQUESTION.get(questionIndex).getSelectAnswer();
        if(ansA_button.getText().equals(rightAnswer) ) ansA_button.setBackgroundColor(Color. GREEN);
        else if(ansB_button.getText().equals(rightAnswer) ) ansB_button.setBackgroundColor(Color. GREEN);
        else if(ansC_button.getText().equals(rightAnswer) ) ansC_button.setBackgroundColor(Color. GREEN);
        else if(ansD_button.getText().equals(rightAnswer) ) ansD_button.setBackgroundColor(Color. GREEN);

        if(ansA_button.getText().equals(selectedAnswer) ) ansA_button.setBackgroundColor(Color. RED);
        else if(ansB_button.getText().equals(selectedAnswer) ) ansB_button.setBackgroundColor(Color. RED);
        else if(ansC_button.getText().equals(selectedAnswer) ) ansC_button.setBackgroundColor(Color. RED);
        else if(ansD_button.getText().equals(selectedAnswer) ) ansD_button.setBackgroundColor(Color. RED);

    }

    public void loadTFQuestion(Question question){
        QuestionShuffler temp = new QuestionShuffler();
        selectedAnswer = null;

        listans = temp.shufflerAnswerTF(question);

        rightAnswer = question.getAnswer();
        questionTextView.setText(question.getQuestion());

        ansA_button.setText(listans.get(0));
        ansB_button.setText(listans.get(1));
        ansC_button.setVisibility(View.INVISIBLE);
        ansD_button.setVisibility(View.INVISIBLE);

        String rightAnswer = question.getAnswer();
        String selectedAnswer = LISTQUESTION.get(questionIndex).getSelectAnswer();
        if(ansA_button.getText().equals(rightAnswer) ) ansA_button.setBackgroundColor(Color. GREEN);
        else if(ansB_button.getText().equals(rightAnswer) ) ansB_button.setBackgroundColor(Color. GREEN);
        else if(ansC_button.getText().equals(rightAnswer) ) ansC_button.setBackgroundColor(Color. GREEN);
        else if(ansD_button.getText().equals(rightAnswer) ) ansD_button.setBackgroundColor(Color. GREEN);

        if(ansA_button.getText().equals(selectedAnswer) ) ansA_button.setBackgroundColor(Color. RED);
        else if(ansB_button.getText().equals(selectedAnswer) ) ansB_button.setBackgroundColor(Color. RED);
        else if(ansC_button.getText().equals(selectedAnswer) ) ansC_button.setBackgroundColor(Color. RED);
        else if(ansD_button.getText().equals(selectedAnswer) ) ansD_button.setBackgroundColor(Color. RED);
    }
    public void De_Invisible(){
        pre_button.setVisibility(View.VISIBLE);
        ansA_button.setVisibility(View.VISIBLE);
        ansB_button.setVisibility(View.VISIBLE);
        ansC_button.setVisibility(View.VISIBLE);
        ansD_button.setVisibility(View.VISIBLE);
    }

    public void finishReview(){
        new AlertDialog.Builder(this)
                .setTitle("FinishReview")
                .setPositiveButton("RestartQuiz", ((dialogInterface, i) -> restartQuiz()))
                .setNegativeButton("BackMenu", ((dialogInterface, i) -> backMenu()))
                .show();
    }

    public void restartQuiz(){
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
        questionIndex = 0;
        LISTQUESTION.clear();
    }

    public void backMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}