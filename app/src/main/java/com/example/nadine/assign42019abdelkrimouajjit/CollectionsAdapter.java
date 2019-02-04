package com.example.nadine.assign42019abdelkrimouajjit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
/**
 * @author The original code was written by Colette Kirwan(DCU Open Education)and was updated by Abdelkrim Ouajjit.
 * @version 14.01.2019.
 * @since 1.0.
 * {@link CollectionsAdapter is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source.
 */
public class CollectionsAdapter extends ArrayAdapter<CollectionsList> {
    TextView nameTextView;
    TextView addressTextView;
    TextView phoneTextView;
    /**
     *
     * @param context:The current context. Used to inflate the layout file.
     * @param productsLists:A List of CollectionList objects to display in a list.
     */
    public CollectionsAdapter(Activity context, ArrayList<CollectionsList> productsLists) {

        super(context, 0, productsLists);
    }
    /**
     *
     * @param position:The position in the list of data that should be displayed in the list item view.
     * @param convertView:The recycled view to populate.
     * @param parent :he parent ViewGroup that is used for inflation.
     * @return: The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view.
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_collection, parent, false);
        }
        // Check if the existing view is being reused, otherwise inflate the view.
        CollectionsList currentCollection = getItem(position);
        // Get the name of the game from the current CollectionsList object and
        // set this text on the name TextView.
        nameTextView = listItemView.findViewById(R.id.store_name);
        nameTextView.setText(currentCollection. getStoreName());
        // Get the store address from the current CollectionsList object and
        // set this text on the store_Address TextView.
        addressTextView = listItemView.findViewById(R.id.store_address);
        addressTextView.setText(currentCollection.getStoreAddress());
        // Get the store phone from the current CollectionsList object and
        // set this text on the store_phone TextView.
        phoneTextView = listItemView.findViewById(R.id.store_phone);
        phoneTextView.setText(currentCollection.getStorePhone());
        // Return the whole list item layout (containing 3 TextViews)
        // so that it can be shown in the ListView.
        return listItemView;
    }

}

