package com.appsmontreal.braintrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    Button buttonG0;
    Button buttonMainExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
         buttonG0 = findViewById(R.id.buttonGo);
         buttonG0.setOnClickListener(this);
        buttonMainExit = findViewById(R.id.buttonMainExit);
        buttonMainExit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonGo:
                intent = new Intent(this,GameActivity.class);
                startActivity(intent);
                break;

            case R.id.buttonMainExit:
                finish();
                break;
            default:
                break;
        }
    }

}
