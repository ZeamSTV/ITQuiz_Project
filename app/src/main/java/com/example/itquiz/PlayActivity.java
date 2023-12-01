package com.example.itquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    Button ansA_button,ansB_button,ansC_button,ansD_button,next_button;
    ImageButton skip_button;

    //    Global variables
    public static String FILENAME = "";
    public static int score = 0;
    public int numofquestions = 0;
    TextView totalQuestionsTextView;
    TextView questionTextView;
    TextView textScore, timeRemainingTextView;
    String selectedAnswer, rightAnswer;
    public static int questionIndex = 0;
    ArrayList<Question> questionList = new ArrayList<>();
    ArrayList<String> listans = new ArrayList<>();
    static TypeOfAnswer countAnswer = new TypeOfAnswer();
    private CountDownTimer countDownTimer, countDownTimerAll;
    boolean startCountDownEachEnabled;
    boolean startCountDownAllEnabled;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        questionList = getListQuestion(questionList, FILENAME);

        SharedPreferences editor = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        numofquestions = editor.getInt("NumOfQuestion", 5);

        init();
        loadQuestions(questionIndex);
        countAnswer.clearResult();

        startCountDownAllTimer();
    }

    public void init(){
        QuestionShuffler temp = new QuestionShuffler();
        questionList = temp.shufflerQuestions(questionList);

        textScore = findViewById(R.id.textIndex);
        questionTextView= findViewById(R.id.textViewQuestion);
        ansA_button = findViewById(R.id.ansA_button);
        ansB_button = findViewById(R.id.ansB_button);
        ansC_button = findViewById(R.id.ansC_button);
        ansD_button = findViewById(R.id.ansD_button);
        next_button = findViewById(R.id.next_button);
        skip_button = findViewById(R.id.buttonskip);

        timeRemainingTextView = findViewById(R.id.timeRemainingTextView);

        ansA_button.setOnClickListener(this);
        ansB_button.setOnClickListener(this);
        ansC_button.setOnClickListener(this);
        ansD_button.setOnClickListener(this);
        next_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        ansA_button.setBackgroundColor(Color.rgb(239,174,62));
        ansB_button.setBackgroundColor(Color.rgb(239,174,62));
        ansC_button.setBackgroundColor(Color.rgb(239,174,62));
        ansD_button.setBackgroundColor(Color.rgb(239,174,62));

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isMusicAnswerEnabled = sharedPreferences.getBoolean("isMusicAnswerEnabled", false);
        startCountDownEachEnabled = sharedPreferences.getBoolean("startCountDownEnabled", true);
        startCountDownAllEnabled = sharedPreferences.getBoolean("startCountDownAllEnabled", false);

        if (clickedButton.getId() == R.id.next_button){
            if (selectedAnswer == rightAnswer){
                score++;
                countAnswer.score++;
                countAnswer.rightAnswer++;
                if (isMusicAnswerEnabled) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.correct);
                    mediaPlayer.start();
                }
            }else{
                countAnswer.wrongAnswer++;
                if(isMusicAnswerEnabled) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.negative);
                    mediaPlayer.start();
                }
            }
            questionIndex++;
            if(startCountDownEachEnabled){
                countDownTimer.cancel();
            }
            if(questionIndex >= numofquestions){
                finishQuiz();
            }else{
                loadQuestions(questionIndex);
            }
        }
        else {
            clickedButton.setBackgroundColor(Color.GREEN);
            selectedAnswer = clickedButton.getText().toString().trim();
            questionList.get(questionIndex).setSelectAnswer(selectedAnswer);
        }

        // Hiện Điểm
        textScore.setText("Score:"+ String.valueOf(score));
    }

    public void loadQuestions(int index){

        De_Invisible();

        Question question = questionList.get(index);

        if(question.getIDquestion().equals("MC")) {
            loadMCQuestion(question);
        }
        else if (question.getIDquestion().equals("TF")) {
            loadTFQuestion(question);
        }

        questionList.get(questionIndex).setSelectAnswer(selectedAnswer);
        ReviewActivity.LISTQUESTION.add(questionList.get(questionIndex));

        startCountDownTimerEach();

        listans.clear();
    }

    public void loadMCQuestion(Question question){
        QuestionShuffler temp = new QuestionShuffler();
        selectedAnswer = null;

        listans = temp.shufflerAnswerMC(question);

        rightAnswer = question.getAnswer();
        questionTextView.setText("Question"+(questionIndex+1)+"/ " +numofquestions+": "
                                 +question.getQuestion());

        ansA_button.setText(listans.get(0));
        ansB_button.setText(listans.get(1));
        ansC_button.setText(listans.get(2));
        ansD_button.setText(listans.get(3));


        skip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countAnswer.skipAnswer++;
                questionIndex++;
                if(startCountDownEachEnabled){
                    countDownTimer.cancel();
                }
                if (questionIndex >= numofquestions){
                    finishQuiz();
                } else {
                    loadQuestions(questionIndex);
                }
            }
        });

    }

    public void loadTFQuestion(Question question){
        QuestionShuffler temp = new QuestionShuffler();
        selectedAnswer = null;

        listans = temp.shufflerAnswerTF(question);

        rightAnswer = question.getAnswer();
        questionTextView.setText("Question"+(questionIndex+1)+"/ " +numofquestions+": "
                                 +question.getQuestion());

        ansA_button.setText(listans.get(0));
        ansB_button.setText(listans.get(1));
        ansC_button.setVisibility(View.INVISIBLE);
        ansD_button.setVisibility(View.INVISIBLE);

        skip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countAnswer.skipAnswer++;
                questionIndex++;
                if(startCountDownEachEnabled){
                    countDownTimer.cancel();
                }
                if (questionIndex >= numofquestions){
                    finishQuiz();
                } else {
                    loadQuestions(questionIndex);
                }
            }
        });
    }
    public void De_Invisible(){
        ansA_button.setVisibility(View.VISIBLE);
        ansB_button.setVisibility(View.VISIBLE);
        ansC_button.setVisibility(View.VISIBLE);
        ansD_button.setVisibility(View.VISIBLE);
    }

    public void startCountDownTimerEach() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean startCountDownEachEnabled = sharedPreferences.getBoolean("startCountDownEnabled", false);

        if (startCountDownEachEnabled) {
            countDownTimer = new CountDownTimer(10000, 1000) {
                long millisUntilFinished;

                public void onTick(long millisUntilFinished) {
                    int minutes = (int) (millisUntilFinished / 1000) / 60;
                    int seconds = (int) (millisUntilFinished / 1000) % 60;
                    timeRemainingTextView.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));

                }
                public void onFinish() {
                    countAnswer.timeoutAnswer++;
                    questionIndex++;
                    if (questionIndex >= numofquestions) {
                        countDownTimer.cancel();
                        finishQuiz();
                    } else {
                        countDownTimer.cancel();
                        loadQuestions(questionIndex);
                    }
                }
            };
            countDownTimer.start();
        }

    }


    public void startCountDownAllTimer() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean startCountDownAllEnabled = sharedPreferences.getBoolean("startCountDownAllEnabled", false);

        if (startCountDownAllEnabled) {
            countDownTimerAll = new CountDownTimer(60000 * numofquestions, 1000) {
                long millisUntilFinished;

                public void onTick(long millisUntilFinished) {
                    int minutes = (int) (millisUntilFinished / 1000) / 60;
                    int seconds = (int) (millisUntilFinished / 1000) % 60;
                    timeRemainingTextView.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
                }
                public void onFinish() {
                        countDownTimerAll.cancel();
                        finishQuiz();
                }
            };
            countDownTimerAll.start();
        }

    }

    public void finishQuiz(){
        Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
        ResultActivity.RESULTANWSER = countAnswer;
        questionIndex = 0;
        score = 0;
        startActivity(intent);
    }

    public ArrayList<Question> getListQuestion(ArrayList<Question> questionList, String filename){
        String json = getJson(filename);
        questionList = convertJsonToQuestions(json);
        return questionList;
    }

    public String getJson(String fileName){ //Nhận vào tên tệp JSON và trả về nội dung của tệp dưới dạng chuỗi
        String json = "";
        try {
            InputStream is = getAssets().open(fileName); //InputStream dùng để đọc tệp
            int size = is.available();
            byte[] buffer = new byte[size];// chuyển đổi dữ liệu thành chuỗi
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);//chuyển đổi dữ liệu thành chuỗi
        } catch (Exception ex){
            Log.e("TAG", "Load json error: " + ex.getMessage());
        }
        return json;
    }

    public ArrayList convertJsonToQuestions(String json){ //Nhận vào một chuỗi JSON và chuyển đổi nó thành danh sách câu hỏi
        ArrayList questionList = new ArrayList<Question>();
        try {
            JSONArray jsonArr = new JSONArray(json);
            int size = jsonArr.length();
            for (int i = 0; i < size; i++){
                JSONObject jsonObject = jsonArr.getJSONObject(i);
                Question question = new Question();
                question.setIDquestion(jsonObject.getString("IDquestion"));
                question.setQuestion(jsonObject.getString("question"));
                question.setAnswer(jsonObject.getString("answer"));
                question.setOption1(jsonObject.getString("option1"));
                question.setOption2(jsonObject.getString("option2"));
                question.setOption3(jsonObject.getString("option3"));
                questionList.add(question);
            }
        } catch (Exception ex){
            Log.e("TAG", "Load json error: " + ex.getMessage());
        }
        return questionList;
    }
//Hàm này sử dụng các lớp JSONArray và JSONObject để phân tích cú pháp chuỗi JSON và tạo đối tượng Question tương
//ứng cho mỗi câu hỏi, sau đó thêm đối tượng này vào danh sách và trả về danh sách đó.

}