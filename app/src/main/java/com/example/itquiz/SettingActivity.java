package com.example.itquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;


public class SettingActivity extends AppCompatActivity {
    private Switch startCountDownEachSwitch, musicBeginSwitch, musicAnswerSwitch,startCountDownAllSwitch ;
    public static boolean isMusicEnabled = false;
    boolean isMusicAnswerEnabled = true;
    static boolean startCountDownEachEnabled = true, startCountDownAllEnabled = false;
    MediaPlayer mediaMusicBegin;
    static EditText editTextNumQuestions;
    private SharedPreferences sharedPreferences;
    private ImageButton buttonBack;
    public int  numOfQuestions = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        editTextNumQuestions =  findViewById(R.id.editTextNumQuestions);
        buttonBack =  findViewById(R.id.buttonBack);
        musicBeginSwitch = findViewById(R.id.musicSwitch);
        musicAnswerSwitch = findViewById(R.id.musicAnswerSwitch);
        startCountDownEachSwitch = findViewById(R.id.startCountDownSwitch);
        startCountDownAllSwitch = findViewById(R.id.startCountDownAllSwitch);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //MusicBegin
        mediaMusicBegin = MediaPlayer.create(SettingActivity.this, R.raw.begin);


        musicBeginSwitch.setChecked(isMusicEnabled);
        // Thêm đoạn mã kiểm tra trạng thái của mediaPlayer và phản hồi đúng với sự thay đổi của musicSwitch
        musicBeginSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkMusicBegin(isChecked);
                isMusicEnabled = isChecked;
            }

        });


        isMusicAnswerEnabled = sharedPreferences.getBoolean("isMusicAnswerEnabled", false);
        musicAnswerSwitch.setChecked(isMusicAnswerEnabled);
        musicAnswerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferences.edit().putBoolean("isMusicAnswerEnabled", isChecked).apply();
            }
        });

        startCountDownEachEnabled = sharedPreferences.getBoolean("startCountDownEnabled", false);
        startCountDownEachSwitch.setChecked(startCountDownEachEnabled);
        startCountDownEachSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferences.edit().putBoolean("startCountDownEnabled", isChecked).apply();
                startCountDownEachEnabled = isChecked;
                startCountDownAllSwitch.setChecked(!startCountDownEachEnabled);
                startCountDownAllEnabled = !isChecked;
            }
        });


        startCountDownAllEnabled = sharedPreferences.getBoolean("startCountDownAllEnabled", false);
        startCountDownAllSwitch.setChecked(startCountDownAllEnabled);
        startCountDownAllSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferences.edit().putBoolean("startCountDownAllEnabled", isChecked).apply();
                startCountDownAllEnabled = isChecked;
                startCountDownEachSwitch.setChecked(!startCountDownAllEnabled);
                startCountDownEachEnabled = !isChecked;
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);

        numOfQuestions = Integer.parseInt(editTextNumQuestions.getText().toString());
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
        editor.putInt("NumOfQuestion",numOfQuestions);
        editor.apply();
        editTextNumQuestions.setText(String.valueOf(numOfQuestions));
        startActivity(intent);
    }
    public void checkMusicBegin(boolean isMusicEnabled){
        if(isMusicEnabled){
            LoginActivity.MUSICBACKGROUND.start();
            LoginActivity.MUSICBACKGROUND.setLooping(true);
        }else if(!isMusicEnabled){
            if(LoginActivity.MUSICBACKGROUND.isPlaying()){
                LoginActivity.MUSICBACKGROUND.setLooping(false);
                LoginActivity.MUSICBACKGROUND.pause();
                LoginActivity.MUSICBACKGROUND.seekTo(0);
            }
        }else if(isMusicEnabled && !LoginActivity.MUSICBACKGROUND.isPlaying()){
            LoginActivity.MUSICBACKGROUND.setLooping(true);
            LoginActivity.MUSICBACKGROUND.start();
        }

    }

}




