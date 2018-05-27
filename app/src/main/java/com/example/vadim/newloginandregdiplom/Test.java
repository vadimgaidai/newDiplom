package com.example.vadim.newloginandregdiplom;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Random;
import java.nio.charset.Charset;



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

    String singleParsed ;

    public  static TextView data;




    private final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    String decodeUTF8(byte[] bytes) {
        return new String(bytes, UTF8_CHARSET);
    }

    byte[] encodeUTF8(String string) {
        return string.getBytes(UTF8_CHARSET);
    }


    class BackGround extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(Test.this);



        @Override
        protected String doInBackground(String ... params) {

            String data="";


            int tmp;

            try {


              /*  String json = ("https://diplomandroid.000webhostapp.com/test.php") ;
                final String encodedURL = URLEncoder.encode(json, "UTF-8");
                URL url = new URL(encodedURL);*/



                URL url = new URL("https://diplomandroid.000webhostapp.com/test.php");




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



          //  s = URLDecoder.decode(URLEncoder.encode(s, "iso8859-1"),"UTF-8");


                try {




                    JSONArray root = new JSONArray(s);


                   // String json = root.toString();
                    //s.getBytes(StandardCharsets.UTF_8);


                   data.setText(s);




                }

                catch (JSONException e) {
                    e.printStackTrace();

                }

        }
    }


    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();


 String quizData[][] = {
            // {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
            {"Какой препроцессор максимально минимизирует написание кода CSS ", "Stylus", "Sass", "Less"},
            {"Какое свойство не поддерживается устаревшими версиями браузеров? ", "flex", "padding", "font-decoration"},
            {"До скольки столбцов максимально масштабируется система разметки Bootstrap?", "12", "6", "3"},
            {"Для каких размеров экрана предназначен класс префикса \"sm\" в Bootstrap?", "<768px", ">=768px", ">=992px"},
            {"Какой атрибут имеет самый высокий приоритет?", "style", "class", "id"},
            {"С какого символа обьявляются переменные в препроцессоре Sass?", "$", "@", "#"},
            {"Что используется в Jade для определения вложенности тегов?", "Табуляция", "Символ \"-\"", "Фигурные скобки"},
            {"Каким образом принято вызывать миксины в Jade?", "Символ \"+\"", "Символ \"@\"", "Ключевое слово \"mixin\""},
            {"Выберите правильный способ задания имени БЭМ-модификатору", "block__item-red", "block=item/red", "block__item/red"},
            {"Каково назначение плагина gulp-watch?", "Отслеживание изменений", "Конкатенация файлов", "Очистка каталога"}

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);


        BackGround b = new BackGround();
        b.execute();


        data = (TextView)findViewById(R.id.data);


        countLabel = (TextView)findViewById(R.id.countLabel);
        questionLabel = (TextView)findViewById(R.id.questionLabel);
        answerBtn1 = (Button)findViewById(R.id.answerBtn1);
        answerBtn2 = (Button)findViewById(R.id.answerBtn2);
        answerBtn3 = (Button)findViewById(R.id.answerBtn3);
        // answerBtn4 = (Button)findViewById(R.id.answerBtn4);

        // Create quizArray from quizData.
        for (int i = 0; i < quizData.length; i++) {
            // Prepare array.
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);  // Country
            tmpArray.add(quizData[i][1]);  // Right Answer
            tmpArray.add(quizData[i][2]);  // Choice1
            tmpArray.add(quizData[i][3]);  // Choice2
            //tmpArray.add(quizData[i][4]);  // Choice3

            // Add tmpArray to quizArray.
            quizArray.add(tmpArray);
        }

        showNextQuiz();

    }

    public void showNextQuiz() {

        // Update quizCountLabel.
        countLabel.setText("Вопрос " + quizCount);

        // Generate random number between 0 and 14 (quizArray's size - 1).
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // Pick one quiz set.
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
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
            startActivity(intent);

        } else {
            quizCount++;
            showNextQuiz();
        }



    }
}