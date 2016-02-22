package com.example.baba_g.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.baba_g.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class NotificationFragment extends Fragment {
    TextView name;
    ArrayList<String> data;
    String url,courses1,grades1,course_code,score,weight,out_of,temp;
    ListView notify;
    String[] course_data;
    JSONObject subject,grade;
    JSONArray courses,grades;
    List<String> list = new ArrayList<String>();
    float abs_marks;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment1.
     */
    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    public NotificationFragment() {
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
//        return inflater.inflate(R.layout.fragment_notification, container, false);

        list.clear();
        View v = inflater.inflate(R.layout.fragment_grades, container, false);
        String[] notifys;
        url = "http://10.243.145.177:8000/default/grades.json";

        RequestQueue queue = Volley.newRequestQueue(getContext());
        notify = (ListView)v. findViewById(R.id.notify);

//        Toast.makeText(getContext(), "yupp", Toast.LENGTH_SHORT).show();

        StringRequest jsObjRequest = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String response1 = response.toString();
                        try {



                            JSONObject user = new JSONObject(response1);
//                            current_sem = user.getString("current_sem");
//                            current_year = user.getString("current_year");
                            courses = user.getJSONArray("courses");
                            grades = user.getJSONArray("grades");
                            courses1=courses.toString();
//                            Toast.makeText(getContext(), courses1, Toast.LENGTH_LONG).show();

                            course_data = new String[courses.length()];
                            String s="S_no"+"   "+"Course"+"    "+"Grade Item"+"    "+"Score"+"     "+"Weight"+"    "+"Absolute Marks";
                            list.add(s);
                            int s_no=1;
                            for(int i = 0;i<courses.length();i++) {
                                subject = courses.getJSONObject(i);
                                grade = grades.getJSONObject(i);
                                course_code = subject.getString("code");
                                grades1 = grade.getString("name");
                                score =grade.getString("score");
                                weight =grade.getString("weightage");
                                out_of=grade.getString("out_of");
                                abs_marks=(Float.parseFloat(score)*Float.parseFloat(weight))/Float.parseFloat(out_of);
                                temp = s_no+"     "+course_code + "   " + grades1+ "   " + score+"/"+out_of+"   "+weight+"    "+abs_marks;

                                list.add(temp);
                                s_no+=1;


                            }



                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,list);

                            notify.setAdapter(arrayAdapter);
//                            Toast.makeText(getContext(), list.get(1), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(getContext(), "yupp", Toast.LENGTH_SHORT).show();

//                            notify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                                @Override
//                                public void onItemClick(AdapterView<?> parent, View view,
//                                                        int position, long id) {
//                                    int itemPosition = position;
//                                    String itemValue = (String) notify.getItemAtPosition(position);
//                                    Intent intent_teaching = new Intent(getContext(), teaching_assistants.class);
//                                    startActivity(intent_teaching);
//
//                                }
//
//                            });

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