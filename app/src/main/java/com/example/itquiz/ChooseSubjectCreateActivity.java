package com.example.itquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ChooseSubjectCreateActivity extends AppCompatActivity {
    private ImageButton homeButtonCreate;
    private Button buttonDSCreate,buttonOOPCreate,buttonCPSCreate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        buttonCPSCreate = findViewById(R.id.buttonCPSCreate);
        buttonOOPCreate = findViewById(R.id.buttonOOPCreate);
        buttonDSCreate = findViewById(R.id.buttonDSCreate);
        homeButtonCreate = findViewById(R.id.homeButtonCreate);
        homeButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });
        buttonDSCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateSubject();
            }
        });
        buttonCPSCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateSubject();
            }
        });
        buttonOOPCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateSubject();
            }
        });

    }
    public void openHome(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public  void openCreateSubject(){
        Intent intent = new Intent(this,CreateSubjectActivity.class);
        startActivity(intent);
    }
}