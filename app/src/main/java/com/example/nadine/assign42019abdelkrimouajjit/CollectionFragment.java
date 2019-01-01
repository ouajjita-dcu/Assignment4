package com.example.nadine.assign42019abdelkrimouajjit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class CollectionFragment extends Fragment {


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


        collectionList.add(new CollectionsList("Store:Swords", "Address:Retail Park,Co.Dublin", "Phone number: (01)63882619"));
        collectionList.add(new CollectionsList("Store:Dublin15", "Address:Retail Park,Co.Dublin", "Phone number: (01)63882619"));
        collectionList.add(new CollectionsList("Store:Dublin14", "Address:Retail Park,Co.Dublin", "Phone number: (01)63882619"));
        collectionList.add(new CollectionsList("Store:Dublin13", "Address:Retail Park,Co.Dublin", "Phone number: (01)63882619"));
        collectionList.add(new CollectionsList("Store:Swords", "Address:Retail Park,Co.Dublin", "Phone number: (01)63882619"));
        collectionList.add(new CollectionsList("Store:Swords", "Address:Retail Park,Co.Dublin", "Phone number: (01)63882619"));
        collectionList.add(new CollectionsList("Store:Swords", "Address:Retail Park,Co.Dublin", "Phone number: (01)63882619"));
        collectionList.add(new CollectionsList("Store:Swords", "Address:Retail Park,Co.Dublin", "Phone number: (01)63882619"));
        collectionList.add(new CollectionsList("Store:Swords", "Address:Retail Park,Co.Dublin", "Phone number: (01)63882619"));
        collectionList.add(new CollectionsList("Store:Swords", "Address:Retail Park,Co.Dublin", "Phone number: (01)63882619"));
        collectionList.add(new CollectionsList("Store:Swords", "Address:Retail Park,Co.Dublin", "Phone number: (01)63882619"));

        CollectionsAdapter collectionAdapter = new CollectionsAdapter(getActivity(), collectionList);
        ListView listView = (ListView) getView().findViewById(R.id.listview_fragment_collection);
        listView.setAdapter(collectionAdapter);
    }

}
