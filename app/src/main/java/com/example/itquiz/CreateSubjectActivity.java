package com.example.itquiz;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CreateSubjectActivity extends AppCompatActivity {
    private ImageButton homeButton;
    private Button submitButton;
    EditText textViewQuestion, optionA, optionB, optionC, optionD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subject);

        homeButton = findViewById(R.id.homeButton);

        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        submitButton = findViewById(R.id.submitButton);

        textViewQuestion = findViewById(R.id.textViewQuestion);


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id, question, answer, option1, option2, option3, selectAnswer;
                id = "idtest";
                question = textViewQuestion.getText().toString();
                answer = optionA.getText().toString();
                option1 = optionB.getText().toString();
                option2 = optionC.getText().toString();
                option3 = optionD.getText().toString();
                selectAnswer = "Answer is NULL";
                JSONObject obj = makeJSONObject(id, question, answer, option1, option2, option3, selectAnswer);
                saveToFile("test.txt", obj.toString()+"\n");
                loadFromFile("test.txt");
//                openResult();
            }
        });

    }
    public void openHome(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public  void openResult(){
        new AlertDialog.Builder(this)
                .setTitle("Notice")
                .setMessage(String.format("Questions: "+textViewQuestion.getText()+"\n"
                                            + "Option 1: "+ optionA.getText()+ "\n"
                                            + "Option 2: "+ optionB.getText()+ "\n"
                                            + "Option 3: "+ optionC.getText()+ "\n"
                                            + "Option 4: "+ optionD.getText()+ "\n"
                ))

                .show();
    }

    public JSONObject makeJSONObject (String id, String question, String answer, String option1,String option2, String option3, String selectAnswer ) {
        JSONObject obj = new JSONObject() ;
        try {
            obj.put("id", id);
            obj.put("question", question);
            obj.put("answer", answer);
            obj.put("option1", option1);
            obj.put("option2", option2);
            obj.put("option3", option3);
            obj.put("selectAnswer", selectAnswer);
        } catch (JSONException e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return obj;
    }
    public void saveToFile(String fileName, String content){
        // Internal Storage
        File path = getApplicationContext().getFilesDir();
        File f = new File(path, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
//            FileOutputStream fos = new FileOutputStream(f);
            fos.write(content.getBytes());
            fos.close();
            //Toast.makeText(getApplicationContext(), "Content saved", Toast.LENGTH_LONG).show();
            openResult();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void loadFromFile(String fileName){
        File path = getApplicationContext().getFilesDir();
        File f = new File(path, fileName);
        byte[] content = new byte[(int)f.length()];
        try {
            FileInputStream fis = new FileInputStream(f);
            fis.read(content);
            fis.close();
//            tvContent.setText(new String(content)); xuat ra
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}