package com.example.baba_g.myapplication;
//main login screen
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class homescreen extends AppCompatActivity implements View.OnClickListener {

    String response1,res;
    Button signup,teaching;
    ImageButton imageButton,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        login = (ImageButton) findViewById(R.id.login);
        login.setOnClickListener(this);
        signup = (Button) findViewById(R.id.bsignup);
        signup.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.icon);
        imageButton.setOnClickListener(this);


    }
     //listens for any click on login or signup button
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.login:
                Intent intent_login = new Intent(this, loginoption.class);
                startActivity(intent_login);
                break;

            case R.id.icon:

               Intent intent_teaching = new Intent(this, teaching_assistants.class);
                startActivity(intent_teaching);
                break;

        }

    }

}
