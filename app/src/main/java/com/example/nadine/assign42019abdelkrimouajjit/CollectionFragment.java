package com.example.nadine.assign42019abdelkrimouajjit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;



public class CollectionFragment extends Fragment {
    private static final String TAG = "CollectionFragment";
    ListView listView;
    SharedPreferences prefs;
    TextView itemClicked;

    /**
     * @author The original code was written by Colette Kirwan(DCU Open Education)and was updated by Abdelkrim Ouajjit(Source:The demo actionTabFragPager.zip (located in the SDA Github directory).).
     * @version 14.01.2019
     * @since 1.0
     *{@link CollectionFragment} shows a list of Android platform releases.
     * For each release, display the store location, The store address, and phone number.
     */

    public CollectionFragment() {
        // Required empty public constructor.
    }

    /**
     *
     * @param inflater:onCreateView() provides a LayoutInflater object called inflater.
     * @param container:parameter passed to onCreateView() is the parent ViewGroup (from the activity's layout)in which the fragment layout is inserted.
     * @param savedInstanceState:parameter is a Bundle that provides data about the previous instance of the fragment,if the fragment is being resumed
     * @return:return a View that is the root of the fragment's layout by using the inflate() method that takes three arguments.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Creating a log that indicate the fragment is onCreateView .
        Log.d(TAG, "onCreateView: started.");
        // Inflate the layout for this fragment.The resource ID of the layout that i want to inflate.
        // The ViewGroup to be the parent of the inflated layout. Passing the container is important in order for the system to apply layout parameters to the root view of the inflated layout
        // specified by the parent view in which it's going.
        // A boolean indicating whether the inflated layout should be attached to the ViewGroup
        return inflater.inflate(R.layout.fragment_collection, container, false);

    }
    /**
     *
     * @param savedInstanceState:parameter is a Bundle that provides data about the previous instance of the fragment if the fragment is being resumed.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Declaring ArrayList named collectionList that contains all the collection list of stores .
        final ArrayList<CollectionsList> collectionList = new ArrayList<CollectionsList>();
        // List of collection added on strings xml file
        collectionList.add(new CollectionsList(getResources().getString(R.string.store_swords), getResources().getString(R.string.swords_address), getResources().getString(R.string.swords_phone)));
        collectionList.add(new CollectionsList(getResources().getString(R.string.store_dublin1), getResources().getString(R.string.dublin1_address), getResources().getString(R.string.dublin1_phone)));
        collectionList.add(new CollectionsList(getResources().getString(R.string.store_dublin2), getResources().getString(R.string.dublin2_address), getResources().getString(R.string.dublin2_phone)));
        collectionList.add(new CollectionsList(getResources().getString(R.string.store_dublin3), getResources().getString(R.string.dublin3_address), getResources().getString(R.string.dublin3_phone)));
        collectionList.add(new CollectionsList(getResources().getString(R.string.store_dublin14), getResources().getString(R.string.dublin14_address), getResources().getString(R.string.dublin14_phone)));
        collectionList.add(new CollectionsList(getResources().getString(R.string.store_dublin15), getResources().getString(R.string.dublin15_address), getResources().getString(R.string.dublin15_phone)));

        // Creating collectionsAdapter object called collectionAdapter.
        CollectionsAdapter collectionAdapter = new CollectionsAdapter(getActivity(), collectionList);
        // Finding the listView.
        listView = getView().findViewById(R.id.listview_fragment_collection);
        // Setting the adapter .
        listView.setAdapter(collectionAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int position, long id) {
                itemClicked=view.findViewById(R.id.store_name);
                view.setBackgroundColor(R.color.colorAccent);
                // Reference :https://developer.android.com/training/data-storage/shared-preferences.
                // Set Preference : By getting the instance of SharedPreferences .
                prefs=getActivity().getSharedPreferences("myprefs",Context.MODE_PRIVATE);
                // Saving the values in the SharedPreferences.
                // Creating a new Editor for these preferences, through which you can make modifications to the data in the preferences and atomically
                // apply those changes back to the SharedPreferences object.
                SharedPreferences.Editor edit=prefs.edit();
                edit.putString("store", itemClicked.getText().toString());
                edit.apply();
                Toast.makeText(getContext(),"You Selected "+itemClicked.getText(),Toast.LENGTH_LONG).show();

            }


        });
    }

}
