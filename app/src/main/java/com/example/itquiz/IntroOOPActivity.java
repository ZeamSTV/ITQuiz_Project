package com.example.itquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class IntroOOPActivity extends AppCompatActivity {
    private Button playoop;
    private ImageButton buttonHomeOOP;



    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introoop);
        playoop = findViewById(R.id.playoop);
        buttonHomeOOP = findViewById(R.id.buttonHomeOOP);


        playoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlay();
            }
        });

        buttonHomeOOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });
    }

    public void openPlay() {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }
    public void openHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}