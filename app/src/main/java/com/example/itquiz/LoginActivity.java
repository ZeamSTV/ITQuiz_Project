package com.example.itquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private Button buttonGo;
    boolean isFirst = true;
    String userName = "";
    public static String NAME ="";
    public static MediaPlayer MUSICBACKGROUND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_home);
        EditText textUserName = findViewById(R.id.username);

        MUSICBACKGROUND = MediaPlayer.create(this, R.raw.begin);

        buttonGo = (Button) findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = textUserName.getText().toString().trim();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                setNAME(userName);
                startActivity(intent);

            }
        });
        textUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (isFirst){
                    textUserName.setText("");
                    isFirst = false;
                }
            }
        });
    }
    public static void setNAME(String userName) {
        LoginActivity.NAME = userName;
    }


}