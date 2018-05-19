package com.example.vadim.newloginandregdiplom;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home extends Activity {

    String name, password, Err;
    TextView nameTV, passwordTV, err;


    public void onClick(View view){
        Intent intent = new Intent(Home.this, Test.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);




        nameTV = (TextView) findViewById(R.id.tvName);
        //emailTV = (TextView) findViewById(R.id.tvEmail);
        //passwordTV = (TextView) findViewById(R.id.tvPassword);
        err = (TextView) findViewById(R.id.err);

       /* name = getIntent().getStringExtra("name");
        password = getIntent().getStringExtra("password");
       */
        name = getIntent().getStringExtra("name");
        password = getIntent().getStringExtra("password");

        Err = getIntent().getStringExtra("err");

        nameTV.setText("Привет "+name);
        //passwordTV.setText("Your password is "+password);
        err.setText(Err);



    }
}
