package com.example.baba_g.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MycourseFragment extends Fragment {
    TextView name;
    ArrayList<String> data;
    String response2,url,current_sem,current_year,course_code,course_name,temp;
    ListView Mycourse;
    String[] course_data;
    JSONObject subject;
    JSONArray courses;
    List<String> list = new ArrayList<String>();


    public static MycourseFragment newInstance() {
        return new MycourseFragment();
    }

    public MycourseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(final LayoutInflater inflater,final ViewGroup container,
                             Bundle savedInstanceState) {
        list.clear();
        View v = inflater.inflate(R.layout.fragment_mycourse, container, false);
        String[] mycourses;
        url = "http://10.243.145.177:8000/courses/list.json";

        RequestQueue queue = Volley.newRequestQueue(getContext());
        Mycourse = (ListView)v. findViewById(R.id.Mycourse);

        StringRequest jsObjRequest = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String response1 = response.toString();
                        try {



                            JSONObject user = new JSONObject(response1);
                            current_sem = user.getString("current_sem");
                            current_year = user.getString("current_year");
                            courses = user.getJSONArray("courses");
                            course_data = new String[courses.length()];
                            for(int i = 0;i<courses.length();i++) {
                                subject = courses.getJSONObject(i);
                                course_code = subject.getString("code");
                                course_name = subject.getString("name");
                                temp = course_code + "  " + course_name;

                                list.add(temp);


                            }



                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,list);

                            Mycourse.setAdapter(arrayAdapter);
                            Toast.makeText(getContext(), list.get(1), Toast.LENGTH_SHORT).show();

                            Mycourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    int itemPosition = position;
                                    String itemValue = (String) Mycourse.getItemAtPosition(position);
                                    Intent intent_teaching = new Intent(getContext(), teaching_assistants.class);
                                    startActivity(intent_teaching);

                                }

                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        String response2 = ("Response: " + error.toString());
                        Toast.makeText(getContext(), response2, Toast.LENGTH_LONG).show();

                    }
                });

        queue.add(jsObjRequest);






        MainActivity activity = (MainActivity) getActivity();
        data = activity.getMyData();



        String a = data.get(0)+" "+data.get(1);
        getActivity().setTitle(a);





        return v;

    }








}