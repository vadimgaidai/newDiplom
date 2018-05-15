package com.example.vadim.newloginandregdiplom;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends Activity {

    String name, password, Err;
    TextView nameTV, passwordTV, err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        nameTV = (TextView) findViewById(R.id.tvName);
        //emailTV = (TextView) findViewById(R.id.tvEmail);
        passwordTV = (TextView) findViewById(R.id.tvPassword);
        err = (TextView) findViewById(R.id.err);

       /* name = getIntent().getStringExtra("name");
        password = getIntent().getStringExtra("password");
        email = getIntent().getStringExtra("email");
        Err = getIntent().getStringExtra("err");*/

        nameTV.setText("Welcome "+name);
        passwordTV.setText("Your password is "+password);
       // emailTV.setText("Your email is "+email);
        err.setText(Err);
    }
}