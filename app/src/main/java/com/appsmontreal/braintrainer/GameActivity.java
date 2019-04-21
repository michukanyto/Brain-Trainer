package com.appsmontreal.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import Model.MathOperation;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    Button [] buttons = new Button[6];
    int [] buttonsId = {R.id.buttonResult1,R.id.buttonResult2,R.id.buttonResult3,R.id.buttonResult4,R.id.buttonStop,R.id.buttonReset,R.id.buttonExit};
    int correctAnswer;
    int totalQuestion;
    int score;
    int seconds;
    int indexCorrectAnswer;
    TextView operationTextView;
    TextView secondsTextView;
    TextView scoreTextView;
    MathOperation mathOperation;
    boolean continueGame;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initialize();
    }

    private void initialize() {
        for (int x = 0; x < buttons.length; x++){
            buttons[x] = findViewById(buttonsId[x]);
            buttons[x].setOnClickListener(this);
        }
        operationTextView = findViewById(R.id.textViewOperation);
        secondsTextView = findViewById(R.id.textViewTime);
        scoreTextView = findViewById(R.id.textViewScore);
        indexCorrectAnswer = 0;
        correctAnswer = 0;
        score = 0;
        totalQuestion = 0;
        seconds = 20;
        continueGame = true;
        random = new Random();
        playGame();
    }

    private void playGame(){
//        while (continueGame){
        mathOperation = new MathOperation();
        mathOperation.generateOperation();
            totalQuestion++;
            gameTime();
            operationTextView.setText(mathOperation.toString());
            scoreTextView.setText(String.valueOf(correctAnswer) + " / " + String.valueOf(totalQuestion));
            fillUpResults();
//        }


    }

    private void fillUpResults() {
        indexCorrectAnswer = random.nextInt(4);
        Log.i("Begin",String.valueOf(indexCorrectAnswer));
        for (int i = 0; i < 4; i++){
            Log.i("i ",String.valueOf(i));
            if (i == indexCorrectAnswer){
                Log.i("inside",String.valueOf(indexCorrectAnswer));
                buttons[correctAnswer].setText(String.valueOf(mathOperation.getResult()));
            }else {
                buttons[i].setText(String.valueOf(random.nextInt(100)));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonReset: initialize();break;
            case R.id.buttonStop: break;
            case R.id.buttonExit: finish();break;
            default:break;
        }

    }

    private void gameTime(){
        CountDownTimer countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                secondsTextView.setText(String.valueOf(--seconds));
            }

            @Override
            public void onFinish() {
                seconds = 20;
                secondsTextView.setText(String.valueOf(seconds));
            }
        }.start();
    }
}
