package com.example.nadine.assign42019abdelkrimouajjit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CollectionsAdapter extends ArrayAdapter<CollectionsList> {

    public CollectionsAdapter(Activity context, ArrayList<CollectionsList> productsLists) {

        super(context, 0, productsLists);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_collection, parent, false);
        }
        CollectionsList currentCollection = getItem(position);

        TextView nameTextView = ( TextView)listItemView.findViewById(R.id.store_name);
        nameTextView.setText(currentCollection. getStoreName());

        TextView addressTextView = (TextView) listItemView.findViewById(R.id.store_address);
        addressTextView.setText(currentCollection.getStoreAddress());

        TextView phoneTextView = (TextView) listItemView.findViewById(R.id.store_phone);
        phoneTextView.setText(currentCollection.getStorePhone());

        return listItemView;
    }

}

