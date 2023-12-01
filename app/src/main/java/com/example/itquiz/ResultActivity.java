package com.example.itquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    public static TypeOfAnswer RESULTANWSER = null;
    PlayActivity Play = new PlayActivity();
    private ImageButton homeButton_Result;
    private Button reviewbutton;
    private ImageView imaWin, imaLost;
    private TextView textResult ;

    TextView textRightAnswer, textWrongAnswer, textSkipAnswer, textTimeOutAnswer, textScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        homeButton_Result = findViewById(R.id.homeButton_Result);
        reviewbutton = findViewById(R.id.reviewbutton);


        init();
        loadResult();
        reviewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReview();
            }
        });
        homeButton_Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomebutton();
            }
        });

        invisibleIma();
        visibleIma();
    }
    public void init(){
        textRightAnswer = findViewById(R.id.textRightAnswer);
        textWrongAnswer = findViewById(R.id.textWrongAnswer);
        textSkipAnswer = findViewById(R.id.textSkipAnswer);
        textTimeOutAnswer = findViewById(R.id.textTimeOutAnswer);
        textScore = findViewById(R.id.textScore);

        imaWin = findViewById(R.id.imawin);
        imaLost = findViewById(R.id.imalost);
        textResult = findViewById(R.id.textResult);
    }

    public void loadResult(){
        textRightAnswer.setText(String.format("Right: %d", RESULTANWSER.rightAnswer));
        textWrongAnswer.setText(String.format("Wrong: %d", RESULTANWSER.wrongAnswer));
        textSkipAnswer.setText(String.format("Skip: %d", RESULTANWSER.skipAnswer));
        textTimeOutAnswer.setText(String.format("Timeout: %d", RESULTANWSER.timeoutAnswer));
        textScore.setText(String.format("%d",RESULTANWSER.score*10));
    }
    public void invisibleIma(){
        imaWin.setVisibility(View.INVISIBLE);
        imaLost.setVisibility(View.INVISIBLE);
    }
    public void visibleIma(){
        int temp = RESULTANWSER.rightAnswer - (RESULTANWSER.wrongAnswer + RESULTANWSER.skipAnswer + RESULTANWSER.timeoutAnswer);
        if(temp >= 0){
            imaWin.setVisibility(View.VISIBLE);
            textResult.setText("AMAZING, GOOD JOB!!");
        }else {
            imaLost.setVisibility(View.VISIBLE);
            textResult.setText("YOU CAN DO BETTER!!");
        }
    }
    public void openHomebutton(){
        Intent intent = new Intent(this, MainActivity.class);
        ReviewActivity.questionIndex = 0;
        ReviewActivity.LISTQUESTION.clear();
        RESULTANWSER.clearResult();
        startActivity(intent);

    }
    public void openReview(){
        //Play.score = 0;
        Play.questionIndex = 0;
        Intent intent = new Intent(this, ReviewActivity.class);
        RESULTANWSER.clearResult();
        startActivity(intent);
    }
}