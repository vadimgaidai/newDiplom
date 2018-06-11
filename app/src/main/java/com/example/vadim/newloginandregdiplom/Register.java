package com.example.vadim.newloginandregdiplom;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import es.dmoral.toasty.Toasty;

public class Register extends Activity {

    EditText name, password;
    String Name, Password;
    Context ctx=this;
    String str="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        name = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);

    }

    public void signOut(View view){
        Name = name.getText().toString();
        Password = password.getText().toString();

        if (Name.length() == 0 | Password.length() == 0) {

            es.dmoral.toasty.Toasty.warning(getApplicationContext(), "Заполните все поля ввода.",
            Toast.LENGTH_SHORT, true).show();

            return;
        }
        if (Name.length() <= 2) {

             es.dmoral.toasty.Toasty.warning(getApplicationContext(), "Имя должно состоять как минимум из 3 символов.",
             Toast.LENGTH_SHORT, true).show();

            return;
        }
        if (Password.length() <= 4) {

             es.dmoral.toasty.Toasty.warning(getApplicationContext(), "Пароль должен состоять как минимум из 5 символов",
             Toast.LENGTH_SHORT, true).show();

            return;
        }

        if (Name.length() >= 16 | Password.length() >= 16) {

             es.dmoral.toasty.Toasty.warning(getApplicationContext(), "Значения имени и пароля не должны привышать 16 символов",
             Toast.LENGTH_SHORT, true).show();

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

                URL url = new URL("https://diplomandroid.000webhostapp.com/Rogin.php");
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

       /* @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
                s="Регистрация прошла успешно.";
                es.dmoral.toasty.Toasty.success(ctx, s, Toast.LENGTH_LONG).show();
            }
            else{
                es.dmoral.toasty.Toasty.error(ctx, s, Toast.LENGTH_LONG).show();
            }
        }*/
       @Override
       protected void onPostExecute(String s) {
           if(s.equals("")){
               s="Регистрация прошла успешно.";
           }
           Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();

         /*  if(str.equals("")){
               Toasty.error(getApplicationContext(), "Неправильный логин или пароль!",
                       Toast.LENGTH_SHORT, true).show();

           }else{
               Toasty.info(getApplicationContext(), str,
                       Toast.LENGTH_SHORT, true).show();
           }*/


       }
    }

}