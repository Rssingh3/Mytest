package com.example.baba_g.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
//shows the list of all TAs
public class teaching_assistants extends Activity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teaching_assistants);

        listView = (ListView) findViewById(R.id.listView);
        String[] values = new String[] {
                "Prathmesh Kallurkar (2012CSZ8280)",
                "Abhishek Bansal (2011CS50271)",
                "Jasmeet Singh (2011CS50281)",
                "Shubham Jindal (2011CS50300)",
                "Ankit Rohilla (2014MCS2118)",
                "Megha Gautam (2014MCS2125)",
                "Preeti Rani (2014MCS2131)",
                "Surbhi Jain (2014MCS2803)"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int itemPosition = position;
                String itemValue = (String) listView.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(),
                        itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });
    }





}
