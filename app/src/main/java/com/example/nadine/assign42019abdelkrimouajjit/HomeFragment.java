package com.example.nadine.assign42019abdelkrimouajjit;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    public HomeFragment() {
        // Required empty public constructor
    }
    /**
     * Reference : https://developer.android.com/guide/components/fragments?
     * To provide a layout for a fragment, it must implement the onCreateView() callback method
     * Implementation of this method must return a View that is the root of the fragment's layout.
     *
     * @param inflater onCreateView() provides a LayoutInflater object called inflater.
     * @param container parameter passed to onCreateView() is the parent ViewGroup (from the activity's layout)in which the fragment layout is inserted.
     * @param savedInstanceState parameter is a Bundle that provides data about the previous instance of the fragment,if the fragment is being resumed
     * @return return a View that is the root of the fragment's layout by using the inflate() method that takes three arguments:
     *The resource ID of the layout that i want to inflate.The ViewGroup to be the parent of the inflated layout. Passing the container is important in order
     *For the system to apply layout parameters to the root view of the inflated layout.Specified by the parent view in which it's going.
     *A boolean indicating whether the inflated layout should be attached to the ViewGroup
     *(In this case, this is false because the system is already inserting the inflated layout into the container
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Creating a log that indicate the fragment is onCreateView .
        Log.d(TAG, "onCreateView: started.");

         View rootView=inflater.inflate(R.layout.fragment_home, container, false);
        final SharedPreferences prefs=getActivity().getSharedPreferences("myprefsGames",Context.MODE_PRIVATE);
        final SharedPreferences prefStore=getActivity().getSharedPreferences("myprefs",Context.MODE_PRIVATE);
        final String myStore=prefStore.getString("store","Please select your store from collection tap");
        String myGame=prefs.getString("itemSelected","Please select your game from product tap");
        final TextView dataStore=(TextView)rootView.findViewById(R.id.textView5);
        final TextView dataGame=(TextView)rootView.findViewById(R.id.textView3);
        dataStore.setText(myStore);
        dataGame.setText("Your game selected is : "+myGame);
        ImageView deleteGame=rootView.findViewById(R.id.deleteGame);
       deleteGame.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               prefs.edit().remove("store").apply();
               dataGame.setText("Please select your game from products tap");
               Toast toast = Toast.makeText(getContext(), "Your item was removed from the basket", Toast.LENGTH_LONG);
               toast.show();
           }
       });
        ImageView deleteStore=rootView.findViewById(R.id.deleteStore);
       deleteStore.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               prefs.edit().remove("itemSelected").apply();
               dataStore.setText("Please select your store from collection tap");
               Toast toast = Toast.makeText(getContext(), "Your store collection was removed.", Toast.LENGTH_LONG);
               toast.show();
           }
       });
        return rootView;

    }

}
