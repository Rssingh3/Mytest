package com.example.baba_g.myapplication;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.lang.String;
import java.net.CookieHandler;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText username,password;
    ImageButton login;
    String busername,bpassword,url,response1,firstname,lastname,entry_number,emailid,key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (ImageButton) findViewById(R.id.login);
        login.setOnClickListener(this);

        java.net.CookieManager cookie_manager = new java.net.CookieManager();
        CookieHandler.setDefault(cookie_manager);
        cookie_manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);



    }
    void user_login() {
        busername = username.getText().toString();
        bpassword = password.getText().toString();


        url = "http://10.243.145.177:8000/default/login.json?userid=" + busername + "&password=" + bpassword;


        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest jsObjRequest = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response1 = response.toString();
                        try {

                            JSONObject user = new JSONObject(response1);
                            String user_info = user.getString("user");
                            JSONObject user1 = new JSONObject(user_info);
                            lastname = user1.getString("last_name");
                            firstname = user1.getString("first_name");
                            entry_number = user1.getString("entry_no");
                            emailid= user1.getString("email");
                          //  String cookies = CookieManager.getInstance().getCookie(url);
                            //Toast.makeText(Login.this,lastname,Toast.LENGTH_SHORT).show();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(Login.this, "error", Toast.LENGTH_LONG).show();
                        String response2 = ("Response: " + error.toString());
                        Toast.makeText(Login.this, response2, Toast.LENGTH_LONG).show();

                    }
                })

        {

           };

        queue.add(jsObjRequest);

    }
// listens for click of login button
// shows the after login screen
@Override
public void onClick(View v) {


    if(v==login)
        {   Bundle bundle = getIntent().getExtras();
            String text = bundle.getString("num");
            String text1 = bundle.getString("num1");


            user_login();

           ArrayList<String> array = new ArrayList<String>();
            Intent intent = new Intent(this, MainActivity.class);
            array.add(firstname);
            array.add(lastname);
            intent.putStringArrayListExtra("strings", array);
            startActivity(intent);
        }
    }






}
