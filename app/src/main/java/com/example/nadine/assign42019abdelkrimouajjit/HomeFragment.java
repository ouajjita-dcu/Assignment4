package com.example.nadine.assign42019abdelkrimouajjit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    /**
     * Reference : https://developer.android.com/guide/components/fragments?
     * To provide a layout for a fragment, it must implement the onCreateView() callback method
     * Implementation of this method must return a View that is the root of the fragment's layout.
     *
     * @param inflater onCreateView() provides a LayoutInflater object called inflater.
     * @param container parameter passed to onCreateView() is the parent ViewGroup (from the activity's layout)
     *         in which the fragment layout is inserted.
     * @param savedInstanceState parameter is a Bundle that provides data about the previous instance of the fragment
     *         if the fragment is being resumed
     * @return return a View that is the root of the fragment's layout by using
     *         The inflate() method takes three arguments:
               The resource ID of the layout that i want to inflate.
               The ViewGroup to be the parent of the inflated layout. Passing the container is important in order
               For the system to apply layout parameters to the root view of the inflated layout
               Specified by the parent view in which it's going.
               A boolean indicating whether the inflated layout should be attached to the ViewGroup
              (In this case, this is false because the system is already inserting the inflated layout into the container
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


}