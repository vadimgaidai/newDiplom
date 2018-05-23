package com.example.vadim.newloginandregdiplom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);


        TextView resultLabel = (TextView) findViewById(R.id.resultLabel);
        TextView totalScoreLabel = (TextView) findViewById(R.id.totalScoreLabel);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        SharedPreferences settings = getSharedPreferences("Diplom", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore", 0);


        totalScore += score * 10;

        resultLabel.setText(score + " / 10");

        totalScoreLabel.setText("Количество баллов: " + totalScore);

        //Update total score.
       // SharedPreferences.Editor editor = settings.edit();
        //editor.putInt("totalScore", totalScore);
        //editor.commit();
    }

    public void returnTop(View view) {
        Intent intent = new Intent(getApplicationContext(), Test.class);
        startActivity(intent);
    }




}