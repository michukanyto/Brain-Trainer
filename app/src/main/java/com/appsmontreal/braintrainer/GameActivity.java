package com.appsmontreal.braintrainer;

import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import Model.MathOperation;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private final String WINMSG = "SCORE : ";
    private final String STOPMSG = "You finished the game, press Exit to go to the Main Menu";
    Button [] buttons = new Button[7];
    int [] buttonsId = {R.id.buttonResult1,R.id.buttonResult2,R.id.buttonResult3,R.id.buttonResult4,R.id.buttonReset,R.id.buttonStop,R.id.buttonExit};
    int points;
    int totalQuestion;
    int score;
    int seconds;
    int indexCorrectAnswer;
    TextView operationTextView;
    TextView secondsTextView;
    TextView scoreTextView;
    TextView finalScoreTextView;
    TextView resultMessageTextView;
    MathOperation mathOperation;
    boolean continueGame;
    Random random;
    CountDownTimer countDownTimer;

    private enum Message{
        POINT,
        FAILURE
    }

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
        resultMessageTextView = findViewById(R.id.textViewResult);
        finalScoreTextView = findViewById(R.id.textViewFinalScore);
        indexCorrectAnswer = 0;
        points = 0;
        score = 0;
        totalQuestion = 0;
        seconds = 20;
        random = new Random();
        disableButtons(true);
        playGame();
    }

    private void playGame(){
        mathOperation = new MathOperation();
        mathOperation.generateOperation();
        gameTimer();
        operationTextView.setText(mathOperation.toString());
        scoreTextView.setText(String.valueOf(points) + " / " + String.valueOf(totalQuestion));
        fillUpResults();

    }

    private void fillUpResults() {
        indexCorrectAnswer = random.nextInt(4);
        for (int i = 0; i < 4; i++){
            if (i == indexCorrectAnswer){
                buttons[indexCorrectAnswer].setText(String.valueOf(mathOperation.getResult()));
            }else {
                buttons[i].setText(String.valueOf(random.nextInt(100)));
            }
        }
    }

    private void disableButtons(boolean action){
        for (int i = 0; i < 5; i++){
            buttons[i].setEnabled(action);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonReset:
                countDownTimer.cancel();
                initialize();
                break;
            case R.id.buttonStop:
                countDownTimer.cancel();
                finalScoreTextView.setText(WINMSG + points + " / " + totalQuestion);
                disableButtons(false);
                Snackbar.make(v,STOPMSG, Snackbar.LENGTH_LONG).show();
                break;
            case R.id.buttonExit:
                finish();
                break;
            case R.id.buttonResult1:
                validateAnswer(buttons[0].getText().toString());
                playGame();
                break;
            case R.id.buttonResult2:
                validateAnswer(buttons[1].getText().toString());
                playGame();
                break;
            case R.id.buttonResult3:
                validateAnswer(buttons[2].getText().toString());
                playGame();
                break;
            case R.id.buttonResult4: validateAnswer(buttons[3].getText().toString());
                playGame();
                break;
            default:
                countDownTimer.cancel();
                break;
        }
    }

    private void validateAnswer(String userAnswer) {
        countDownTimer.cancel();
        if (userAnswer.equals(buttons[indexCorrectAnswer].getText().toString())){
            points++;
            resultMessageTextView.setText(Message.POINT.name());
        }else{
            resultMessageTextView.setText(Message.FAILURE.name());
        }
        totalQuestion++;
    }

    private void gameTimer(){
        countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                secondsTextView.setText(String.valueOf(--seconds));
            }

            @Override
            public void onFinish() {

            }
        }.start();
        seconds = 20;
        secondsTextView.setText(String.valueOf(seconds));
    }
}
