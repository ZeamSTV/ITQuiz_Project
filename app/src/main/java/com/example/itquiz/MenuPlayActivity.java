package com.example.itquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPlayActivity extends AppCompatActivity {

    Button buttonDS, buttonOOP, buttonCPS;
    ImageButton homeButton2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_play);

        buttonDS = (Button) findViewById(R.id.buttonDS);
        buttonOOP = findViewById(R.id.buttonOOP);
        buttonCPS = findViewById(R.id.buttonCPS);
        homeButton2 = (ImageButton) findViewById(R.id.homeButton);

        buttonDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPlay_DS();
            }
        });

        buttonOOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPlay_OOP();
            }
        });

        buttonCPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityPlay_CPS();
            }
        });

        homeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });
    }

    public void openActivityPlay_DS(){
        Intent intent = new Intent(this, IntroDTActivity.class);
        PlayActivity.FILENAME = "question_ds.json";
        startActivity(intent);
    }

    public void openActivityPlay_OOP(){
        Intent intent = new Intent(this, IntroOOPActivity.class);
        PlayActivity.FILENAME = "question_oop.json";
        startActivity(intent);

    }

    public void openActivityPlay_CPS(){
        Intent intent = new Intent(this, IntroCSActivity.class);
        PlayActivity.FILENAME = "question_cps.json";
        startActivity(intent);

    }
    public void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}