package com.example.itquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button buttonplay,buttoncreate, buttonquit, settingButton;
    public String userName = "ITER";


    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView1 = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);

//        SharedPreferences editor = getSharedPreferences("Login", MODE_PRIVATE);
//        NAME = editor.getString("UserName", "ITER");
        userName = LoginActivity.NAME;

        textView1.setText("WELCOME " + userName);
        textView2.setText("WELCOME " + userName);

        buttonplay = (Button) findViewById(R.id.buttonplay);
        buttoncreate = (Button) findViewById(R.id.buttoncreate);
        buttonquit = (Button) findViewById(R.id.buttonquit);
        settingButton = (Button) findViewById(R.id.settingButton);


        buttonplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuPlay();
            }
        });
        buttoncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityCreate();
            }
        });
        buttonquit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityQuit();
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitySetting();
            }
        });
    }

    public void openMenuPlay() {
        Intent intent = new Intent(this, MenuPlayActivity.class);
        startActivity(intent);
    }
    public void openActivityCreate(){
        Intent intent = new Intent(this, ChooseSubjectCreateActivity.class);
        startActivity(intent);
    }

    public void openActivityQuit(){
        Intent intent = new Intent(this, QuitActivity.class);
        startActivity(intent);
    }

    public void openActivitySetting(){
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }


}