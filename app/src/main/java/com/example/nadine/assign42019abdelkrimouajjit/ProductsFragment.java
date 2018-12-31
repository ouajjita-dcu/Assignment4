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


public class ProductsFragment extends Fragment {

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View view =inflater.inflate(R.layout.fragment_products, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ArrayList<ProductsList> gamesList = new ArrayList<ProductsList>();


        gamesList.add(new ProductsList("Yoscki Game", "Adventure", R.drawable.yoschi));
        gamesList.add(new ProductsList("Cute Game", "Draw And Fill", R.drawable.cute));
        gamesList.add(new ProductsList("Friend Game", "Join your Friend", R.drawable.friend));
        gamesList.add(new ProductsList("Elfe Game", "Strategy", R.drawable.elfe));
        gamesList.add(new ProductsList("Mario Game", "Jump with mario", R.drawable.mario));
        gamesList.add(new ProductsList("Lego Game", "Strategy", R.drawable.lego));
        gamesList.add(new ProductsList("Fun Game", "Family time", R.drawable.fun));
        gamesList.add(new ProductsList("Mushroom Game", "Puzzle", R.drawable.mushroom));
        gamesList.add(new ProductsList("Minion Game", "Action", R.drawable.minion));
        gamesList.add(new ProductsList("Casino Game", "Real Time Strategy", R.drawable.casino));
        gamesList.add(new ProductsList("Fight Game", "Simulation", R.drawable.fight));
        gamesList.add(new ProductsList("Chess Game", "Strategy", R.drawable.chess));
        gamesList.add(new ProductsList("Ship Game", "Adventure", R.drawable.ship));
        gamesList.add(new ProductsList("Snooker Game", "Real play", R.drawable.snooker));
        gamesList.add(new ProductsList("Santa Game", "Adventure", R.drawable.santa));




        ProductsAdapter gameAdapter = new ProductsAdapter(getActivity(), gamesList);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) getView().findViewById(R.id.listview_fragment);
        listView.setAdapter(gameAdapter);
    }
}
