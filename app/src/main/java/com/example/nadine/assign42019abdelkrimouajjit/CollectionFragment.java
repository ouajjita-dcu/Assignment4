package com.example.nadine.assign42019abdelkrimouajjit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.os.ParcelFileDescriptor.MODE_APPEND;


public class CollectionFragment extends Fragment {

    public SharedPreferences prefs;

    public CollectionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_collection, container, false);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ArrayList<CollectionsList> collectionList = new ArrayList<CollectionsList>();

      // List of collection added on strings xml file
        collectionList.add(new CollectionsList(getResources().getString(R.string.store_swords), getResources().getString(R.string.swords_address), getResources().getString(R.string.swords_phone)));
        collectionList.add(new CollectionsList(getResources().getString(R.string.store_dublin1), getResources().getString(R.string.dublin1_address), getResources().getString(R.string.dublin1_phone)));
        collectionList.add(new CollectionsList(getResources().getString(R.string.store_dublin2), getResources().getString(R.string.dublin2_address), getResources().getString(R.string.dublin2_phone)));
        collectionList.add(new CollectionsList(getResources().getString(R.string.store_dublin3), getResources().getString(R.string.dublin3_address), getResources().getString(R.string.dublin3_phone)));
        collectionList.add(new CollectionsList(getResources().getString(R.string.store_dublin14), getResources().getString(R.string.dublin14_address), getResources().getString(R.string.dublin14_phone)));
        collectionList.add(new CollectionsList(getResources().getString(R.string.store_dublin15), getResources().getString(R.string.dublin15_address), getResources().getString(R.string.dublin15_phone)));


        CollectionsAdapter collectionAdapter = new CollectionsAdapter(getActivity(), collectionList);
        final ListView listView = (ListView) getView().findViewById(R.id.listview_fragment_collection);
        listView.setAdapter(collectionAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int position, long id) {

                TextView itemClicked=(TextView)view.findViewById(R.id.store_name);
                view.setBackgroundColor(R.color.colorAccent);


                SharedPreferences prefs=getActivity().getSharedPreferences("myprefs",Context.MODE_PRIVATE);
                SharedPreferences.Editor edit=prefs.edit();
                edit.putString("store", itemClicked.getText().toString());
                edit.apply();
                Toast.makeText(getContext(),"You Selected "+itemClicked.getText(),Toast.LENGTH_LONG).show();

            }


        });
    }

}
