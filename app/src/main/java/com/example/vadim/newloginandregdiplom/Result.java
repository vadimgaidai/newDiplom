package com.example.vadim.newloginandregdiplom;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);


        TextView resultLabel = (TextView) findViewById(R.id.resultLabel);
        TextView totalScoreLabel = (TextView) findViewById(R.id.totalScoreLabel);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        resultLabel.setText(score + " / 10");

        if( score >= 0 && score <= 4){
            totalScoreLabel.setText("Минимальный уровень знаний");
        }
        if( score >= 5 && score <= 8){
            totalScoreLabel.setText("Средний уровень знаний");
        }
        if( score >= 9){
            totalScoreLabel.setText("Высокий уровень знаний");
        }

    }

    public void returnTop(View view) {
        Intent intent = new Intent(getApplicationContext(), Test.class);
        startActivity(intent);
    }




}