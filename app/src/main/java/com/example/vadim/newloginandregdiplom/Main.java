package com.example.vadim.newloginandregdiplom;



import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import es.dmoral.toasty.Toasty;


public class Main extends Activity {

    EditText name, password;
    String Name, Password;
    Context ctx=this;
    String NAME, PASSWORD;
    String str="";

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

            es.dmoral.toasty.Toasty.warning(getApplicationContext(), "Заполните все поля ввода.",
            Toast.LENGTH_SHORT, true).show();

            return;

        }else{ BackGround b = new BackGround();
            b.execute(Name, Password);

        }
    }


    class BackGround extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(Main.this);

       @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("https://diplomandroid.000webhostapp.com/login.php");
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

                str = "Возможны неполадки подключения к серверу.";
                return str;

            } catch (IOException e) {

                str= "Возможны неполадки подключения к серверу.";
                return str;
            }



        }

        @Override
        protected void onPostExecute(String s) {

            pdLoading.dismiss();

            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");

                NAME = user_data.getString("name");
                PASSWORD = user_data.getString("password");

                Intent i = new Intent(ctx, Home.class);
                i.putExtra("name", NAME);
                i.putExtra("password", PASSWORD);

                startActivity(i);

            }
            catch (JSONException e) {
                e.printStackTrace();

                if(str.equals("")){
                    Toasty.error(getApplicationContext(), "Неправильный логин или пароль!",
                            Toast.LENGTH_SHORT, true).show();

                }else{
                    Toasty.info(getApplicationContext(), str,
                            Toast.LENGTH_SHORT, true).show();
                }

            }

        }
    }
}