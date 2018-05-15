package com.example.vadim.newloginandregdiplom;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main extends Activity {

    EditText name, password;
    String Name, Password;
    //Context ctx=this;
    String NAME, PASSWORD, EMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        name = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);
    }

    public void goToReg (View v){
        startActivity(new Intent(this,Register.class));
    }

    public void signIn (View view){
        Name = name.getText().toString();
        Password = password.getText().toString();


        if (Name.length() == 0 | Password.length() == 0) {
            Toast.makeText(getApplicationContext(), "Заполните все поля ввода",
                    Toast.LENGTH_LONG).show();
            return;

        }
        if (Name.length() <= 2) {
            Toast.makeText(getApplicationContext(), "Username должен состоять как минимум из 3 символов",
                    Toast.LENGTH_LONG).show();
            return;

        }
        if (Password.length() <= 4) {
            Toast.makeText(getApplicationContext(), "Password должен состоять как минимум из 5 символов",
                    Toast.LENGTH_LONG).show();
            return;

        }
        else{
            BackGround b = new BackGround();
            b.execute(Name, Password);
        }

    }



    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("https://probdip.000webhostapp.com//Login.php");
                String urlParams = "name="+name+"&password="+password;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }

                is.close();
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

            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");





                NAME = user_data.getString("name");
                PASSWORD = user_data.getString("password");


                Intent intent = new Intent(Main.this, Home.class);
                Main.this.startActivity(intent);





            } catch (JSONException e) {
                e.printStackTrace();

            }




        }
    }
}