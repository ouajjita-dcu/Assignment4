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

    /**
     * @param position: The position of the listItemView.
     * @param convertView :This reused View is the convertView.
     * @param parent:The parent is provided that can inflate your view into that for proper layout parameters.
     * @return : returning the listItemView.
     */
    @Override
    //The adapters are built to reuse Views, when a View is scrolled so that is no longer visible,
    // it can be used for one of the new Views appearing.
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        ProductsList currentAndroidFlavor = getItem(position);

        TextView nameTextView = listItemView.findViewById(R.id.version_name);
        nameTextView.setText(currentAndroidFlavor.getVersionName());

        TextView numberTextView = listItemView.findViewById(R.id.version_number);
        numberTextView.setText(currentAndroidFlavor.getVersionNumber());
        ImageView iconView =listItemView.findViewById(R.id.list_item_icon);
        iconView.setImageResource(currentAndroidFlavor.getImageResourceId());
        //The LayoutInflater takes your layout XML-files and creates different View-objects from its contents.
        return listItemView;
    }

}
