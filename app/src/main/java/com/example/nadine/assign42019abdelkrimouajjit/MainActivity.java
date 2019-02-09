package com.example.nadine.assign42019abdelkrimouajjit;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

/**
 * @author The original code was written by Colette Kirwan(DCU Open Education)and was updated by Abdelkrim Ouajjit(Source:The demo actionTabFragPager.zip (located in the SDA Github directory).).
 * @version 14.01.2019
 * @since 1.0
 */
public class MainActivity extends AppCompatActivity  {

    private static final String TAG = "MainActivity";

    /**
     *
     * @param savedInstanceState:Saving the state of the application in a bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating a log that indicate the MainActivity is onCreate() .
        Log.d(TAG, "onCreateView: started.");

        // TabLayout provides a horizontal layout to display tabs.Population of the tabs to display is done through TabLayout.Tab instances.
        // To display the tab by adding the layout via one of the addTab(Tab) methods.
        // Build.gradle file contains a dependency on com.android.support:design.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Using addTab(Tab) methods to populate the tabs.
        TabLayout tabLayout =  findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        //  Setup the ViewPager.
        ViewPager mypager =  findViewById(R.id.pager);
         ViewPagerAdapter myadapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
         mypager.setAdapter(myadapter);
        // Setup the TabLayout and connect it to the ViewPager.
        tabLayout.setupWithViewPager(mypager);

    }
}

