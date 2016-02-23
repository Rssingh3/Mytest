package com.example.baba_g.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class assign_list_fragment extends Fragment {

    ArrayList<String> coursecode;
    String assign_list_response, project_name;
    List<String> assign_list = new ArrayList<String>();
    ListView course_assignment;
    TextView notification;

    public assign_list_fragment() {
        // Required empty public constructor
    }


    public static assign_list_fragment newInstance() {

        return new assign_list_fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myassign = inflater.inflate(R.layout.fragment_assign_list_fragment, container, false);
        course_info activity = (course_info) getActivity();
        coursecode = activity.gettext();
        course_assignment = (ListView)myassign.findViewById(R.id.course_assignment);
        notification = (TextView)myassign.findViewById(R.id.notification);
        String url = "http://10.243.145.177:8000/courses/course.json/"+ coursecode.get(0)+"/assignments";


        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest jsObjRequest = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        assign_list_response = response.toString();
                        try {

                            JSONObject user = new JSONObject(assign_list_response);
                            JSONArray assign_json = user.getJSONArray("assignments");

                            if (assign_json.length()!=0)
                            {
                                int length = assign_json.length();
                                for (int i = 0; i < length; i++) {
                                    project_name = assign_json.getJSONObject(i).getString("name");
                                    assign_list.add(project_name);
                                }

                                ArrayAdapter<String> arrayAdapter_assign = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, assign_list);

                                course_assignment.setAdapter(arrayAdapter_assign);
                            }


                            else
                            {
                                notification.setVisibility(View.VISIBLE);
                            }
                           // ArrayAdapter<String> arrayAdapter_assign = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,assign_list);
                           //course_assignment.setAdapter(arrayAdapter_assign);

                          /*  course_assignment.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    int itemPosition = position;
                                    String itemValue = (String) course_assignment.getItemAtPosition(position);
                                    // Intent intent_teaching = new Intent(getContext(), teaching_assistants.class);
                                    //startActivity(intent_teaching);
                                    ArrayList<String> array = new ArrayList<String>();
                                    Intent intent = new Intent(getContext(), course_info.class);
                                    array.add(itemValue);
                                    intent.putStringArrayListExtra("code", array);
                                    startActivity(intent);

                                }

                            });*/

                           // Toast.makeText(getContext(), assign_list.get(1), Toast.LENGTH_SHORT).show();



                            } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();
                        String response2 = ("Response: " + error.toString());
                        Toast.makeText(getContext(), response2, Toast.LENGTH_LONG).show();

                    }
                })

        {

        };

        queue.add(jsObjRequest);




        return myassign;
    }



}