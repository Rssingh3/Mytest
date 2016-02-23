package com.example.baba_g.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class course_info extends AppCompatActivity {

    ArrayList<String> course_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabsFromPagerAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        course_code = getIntent().getStringArrayListExtra("code");


    }
    public ArrayList<String> gettext()
    {
        return course_code;
    }
    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return assign_list_fragment.newInstance();
                case 1: return course_grades_fragment.newInstance();
                case 2: return  course_threads_fragment.newInstance();
                default: return assign_list_fragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
        //Naming the tabs
        @Override
        public String getPageTitle(int position) {
            //naming the tabs
            switch (position){
                case 0: return "Assignments"  ;
                case 1: return  "Grade";
                case 2: return  "Threads";
                default: return  "Assignments";
            }

        }
    }

}
