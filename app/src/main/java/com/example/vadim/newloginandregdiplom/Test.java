package com.example.vadim.newloginandregdiplom;



import android.app.ProgressDialog;
import android.content.Intent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Random;




public class Test extends AppCompatActivity {

    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    // private Button answerBtn4;

    private String rightAnswer;

    private String str = "";


    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 10;



    String vopr1;
    String vopr2;
    String vopr3;
    String vopr4;
    String vopr5;
    String vopr6;
    String vopr7;
    String vopr8;
    String vopr9;
    String vopr10;

    String otv1_1;
    String otv1_2;
    String otv1_3;

    String otv2_1;
    String otv2_2;
    String otv2_3;

    String otv3_1;
    String otv3_2;
    String otv3_3;

    String otv4_1;
    String otv4_2;
    String otv4_3;

    String otv5_1;
    String otv5_2;
    String otv5_3;

    String otv6_1;
    String otv6_2;
    String otv6_3;

    String otv7_1;
    String otv7_2;
    String otv7_3;

    String otv8_1;
    String otv8_2;
    String otv8_3;

    String otv9_1;
    String otv9_2;
    String otv9_3;

    String otv10_1;
    String otv10_2;
    String otv10_3;




    public  static TextView data;




    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();



    class BackGround extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(Test.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String ... params) {

            String data="";


            int tmp;

            try {

                URL url = new URL("https://diplomandroid.000webhostapp.com/vadik.php");


                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while((tmp=bufferedReader.read())!=-1){
                    data+= (char)tmp;
                }

                bufferedReader.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Ошибка: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Ошибка: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {


            pdLoading.dismiss();
            try {


                JSONObject root = new JSONObject(s);

                JSONObject vopr_1 = root.getJSONObject("1");
                JSONObject vopr_2 = root.getJSONObject("2");
                JSONObject vopr_3 = root.getJSONObject("3");
                JSONObject vopr_4 = root.getJSONObject("4");
                JSONObject vopr_5 = root.getJSONObject("5");
                JSONObject vopr_6 = root.getJSONObject("6");
                JSONObject vopr_7 = root.getJSONObject("7");
                JSONObject vopr_8 = root.getJSONObject("8");
                JSONObject vopr_9 = root.getJSONObject("9");
                JSONObject vopr_10 = root.getJSONObject("10");

                vopr1 = vopr_1.getString("vopr");
                otv1_1 = vopr_1.getString("otv1");
                otv1_2 = vopr_1.getString("otv2");
                otv1_3 = vopr_1.getString("otv3");

                vopr2 = vopr_2.getString("vopr");
                otv2_1 = vopr_2.getString("otv1");
                otv2_2 = vopr_2.getString("otv2");
                otv2_3 = vopr_2.getString("otv3");


                vopr3 = vopr_3.getString("vopr");
                otv3_1 = vopr_3.getString("otv1");
                otv3_2 = vopr_3.getString("otv2");
                otv3_3 = vopr_3.getString("otv3");


                vopr4 = vopr_4.getString("vopr");
                otv4_1 = vopr_4.getString("otv1");
                otv4_2 = vopr_4.getString("otv2");
                otv4_3 = vopr_4.getString("otv3");

                vopr5 = vopr_5.getString("vopr");
                otv5_1 = vopr_5.getString("otv1");
                otv5_2 = vopr_5.getString("otv2");
                otv5_3 = vopr_5.getString("otv3");

                vopr6 = vopr_6.getString("vopr");
                otv6_1 = vopr_6.getString("otv1");
                otv6_2 = vopr_6.getString("otv2");
                otv6_3 = vopr_6.getString("otv3");


                vopr7 = vopr_7.getString("vopr");
                otv7_1 = vopr_7.getString("otv1");
                otv7_2 = vopr_7.getString("otv2");
                otv7_3 = vopr_7.getString("otv3");


                vopr8 = vopr_8.getString("vopr");
                otv8_1 = vopr_8.getString("otv1");
                otv8_2 = vopr_8.getString("otv2");
                otv8_3 = vopr_8.getString("otv3");

                vopr9 = vopr_9.getString("vopr");
                otv9_1 = vopr_9.getString("otv1");
                otv9_2 = vopr_9.getString("otv2");
                otv9_3 = vopr_9.getString("otv3");

                vopr10 = vopr_10.getString("vopr");
                otv10_1 = vopr_10.getString("otv1");
                otv10_2 = vopr_10.getString("otv2");
                otv10_3 = vopr_10.getString("otv3");




                String quizData[][] = {
                        { vopr1, otv1_1, otv1_2 ,otv1_3 },
                        { vopr2, otv2_1, otv2_2 ,otv2_3 },
                        { vopr3, otv3_1, otv3_2 ,otv3_3 },
                        { vopr4, otv4_1, otv4_2 ,otv4_3 },
                        { vopr5, otv5_1, otv5_2 ,otv5_3 },
                        { vopr6, otv6_1, otv6_2 ,otv6_3 },
                        { vopr7, otv7_1, otv7_2 ,otv7_3 },
                        { vopr8, otv8_1, otv8_2 ,otv8_3 },
                        { vopr9, otv9_1, otv9_2 ,otv9_3 },
                        { vopr10, otv10_1, otv10_2 ,otv10_3 }

                };

                for (int j = 0; j < quizData.length; j++) {
                    // Prepare array.
                    ArrayList<String> tmpArray = new ArrayList<>();
                    tmpArray.add(quizData[j][0]);  // Country
                    tmpArray.add(quizData[j][1]);  // Right Answer
                    tmpArray.add(quizData[j][2]);  // Choice1
                    tmpArray.add(quizData[j][3]);  // Choice2
                    //tmpArray.add(quizData[i][4]);  // Choice3

                    // Add tmpArray to quizArray.
                    quizArray.add(tmpArray);
                }



                showNextQuiz();

            }

            catch (JSONException e) {
                e.printStackTrace();

            }


        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);


        BackGround b = new BackGround();
        b.execute();


        countLabel = (TextView)findViewById(R.id.countLabel);
        questionLabel = (TextView)findViewById(R.id.questionLabel);
        answerBtn1 = (Button)findViewById(R.id.answerBtn1);
        answerBtn2 = (Button)findViewById(R.id.answerBtn2);
        answerBtn3 = (Button)findViewById(R.id.answerBtn3);
        // answerBtn4 = (Button)findViewById(R.id.answerBtn4);

        // Create quizArray from quizData.


    }

    public void showNextQuiz() {

        // Update quizCountLabel.
        countLabel.setText("Вопрос " + quizCount);

        // Generate random number between 0 and 14 (quizArray's size - 1).



       Random random =  new Random();
       int randomNum = random.nextInt(quizArray.size());


        ArrayList<String> quiz = quizArray.get(randomNum);



        // Set question and right answer.
        // Array format: {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        // Remove "Country" from quiz and Shuffle choices.
        quiz.remove(0);
        Collections.shuffle(quiz);

        // Set Choices.
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        //answerBtn4.setText(quiz.get(3));

        // Remove this quiz from quizArray.
        quizArray.remove(randomNum);

    }

    public void checkAnswer(View view) {

        // Get pushed button.
        Button answerBtn = (Button) findViewById(view.getId());
        String btnText = answerBtn.getText().toString();



        if(btnText.equals(rightAnswer)) {
            rightAnswerCount++;
        }


        // Create Dialog.

        if (quizCount == QUIZ_COUNT) {
            // Show Result.
            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
            startActivity(intent);

        } else {
            quizCount++;
            showNextQuiz();
        }




    }
}