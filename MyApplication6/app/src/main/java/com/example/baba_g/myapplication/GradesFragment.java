package com.example.baba_g.myapplication;
//shows grades of all courses while grades tab is on.
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GradesFragment extends Fragment {

    TextView name;
    ArrayList<String> data;
    String response2,url,current_sem,current_year,course_code,course_name,temp;
    ListView Mygrade;
    String[] course_data;
    JSONObject subject;
    JSONArray courses;
    List<String> list = new ArrayList<String>();
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment1.
     */
    public static GradesFragment newInstance() {
        return new GradesFragment();
    }

    public GradesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_grades, container, false);

        list.clear();
        View v = inflater.inflate(R.layout.fragment_grades, container, false);
        String[] Mygrades;
        url = "http://10.243.145.177:8000/default/grades.json";

        RequestQueue queue = Volley.newRequestQueue(getContext());
        Mygrade = (ListView)v. findViewById(R.id.Mygrade);

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

                            Mygrade.setAdapter(arrayAdapter);
                            Toast.makeText(getContext(), list.get(1), Toast.LENGTH_SHORT).show();

                            Mygrade.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    int itemPosition = position;
                                    String itemValue = (String) Mygrade.getItemAtPosition(position);
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