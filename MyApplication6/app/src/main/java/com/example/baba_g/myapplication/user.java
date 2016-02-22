package com.example.baba_g.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class user extends Activity {
    ListView user_data;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


      /*  Intent intent = getIntent();
        String [] stringArray = intent.getStringArrayExtra("key");
        name.setText(stringArray[0]);*/

    }




}
