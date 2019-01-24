package com.example.nadine.assign42019abdelkrimouajjit;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity  {

    private ViewPagerAdapter mViewPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        ViewPager mypager = (ViewPager) findViewById(R.id.pager);
         ViewPagerAdapter myadapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
         mypager.setAdapter(myadapter);
        tabLayout.setupWithViewPager(mypager);


    }


}
// Create the adapter that will return a fragment for each of the three
// primary sections of the activity.
/**
 * getTabCount ()
 * Returns the number of tabs currently registered with the action bar. karim
 * setupWithViewPager (ViewPager viewPager)
 The one-stop shop for setting up this TabLayout with a ViewPager.
 This is the same as calling setupWithViewPager(ViewPager, boolean) with auto-refresh enabled
 */
