package com.example.baba_g.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public class course_threads_fragment extends Fragment {



    public course_threads_fragment() {

    }


    // TODO: Rename and change types and number of parameters
    public static course_threads_fragment newInstance() {

        return new course_threads_fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_course_threads_fragment, container, false);
    }

}
