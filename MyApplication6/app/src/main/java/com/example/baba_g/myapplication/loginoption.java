package com.example.baba_g.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class loginoption extends AppCompatActivity implements View.OnClickListener {
    Button student,faculty;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginoption);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        student =(Button) findViewById(R.id.student);
        faculty = (Button) findViewById(R.id.faculty);
        student.setOnClickListener(this);
        faculty.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String one="0";
        String one1="0";
        switch(v.getId())
        {
            case R.id.student:
                Intent i = new Intent(this, Login.class);
                one="1";
                Bundle bundle = new Bundle();
                bundle.putString("num",one);
                i.putExtras(bundle);
                startActivity(i);



        /*    case R.id.faculty:
                Intent i1 = new Intent(this, Login.class);
                one1="2";
                Bundle bundle1 = new Bundle();
                bundle1.putString("num1",one1);
                i1.putExtras(bundle1);
                startActivity(i1);*/

        }
    }
}
