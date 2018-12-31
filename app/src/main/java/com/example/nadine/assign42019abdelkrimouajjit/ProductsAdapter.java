package com.example.nadine.assign42019abdelkrimouajjit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductsAdapter extends ArrayAdapter<ProductsList> {

    public ProductsAdapter(Activity context, ArrayList<ProductsList> androidFlavors) {

        super(context, 0, androidFlavors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        ProductsList currentAndroidFlavor = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.version_name);
        nameTextView.setText(currentAndroidFlavor.getVersionName());

        TextView numberTextView = (TextView) listItemView.findViewById(R.id.version_number);
        numberTextView.setText(currentAndroidFlavor.getVersionNumber());
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        iconView.setImageResource(currentAndroidFlavor.getImageResourceId());
        return listItemView;
    }

}