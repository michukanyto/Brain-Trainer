package com.appsmontreal.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Model.MathOperation;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    Button [] buttons = new Button[6];
    int [] buttonsId = {R.id.buttonStop,R.id.buttonReset,R.id.buttonExit,R.id.buttonResult1,R.id.buttonResult2,R.id.buttonResult3};
    int correctAnswer;
    TextView operationTextView;
    MathOperation mathOperation;

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
        correctAnswer = 0;
        operationTextView = findViewById(R.id.textViewOperation);
        mathOperation = new MathOperation();
        playGame();
    }

    private void playGame(){
        operationTextView.setText(mathOperation.toString());

    }

    @Override
    public void onClick(View v) {

    }
}
