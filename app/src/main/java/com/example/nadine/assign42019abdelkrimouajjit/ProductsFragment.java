package com.example.nadine.assign42019abdelkrimouajjit;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ProductsFragment extends Fragment {

    private String gameName="";

    public ProductsFragment() {
        // Required empty public constructor
    }
    Dialog d;

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
        //final ArrayList<String> trackItems = new ArrayList<>();

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

        final ProductsAdapter gameAdapter = new ProductsAdapter(getActivity(), gamesList);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) getView().findViewById(R.id.listview_fragment);
        listView.setAdapter(gameAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int position, long id) {
                TextView itemClicked=(TextView)view.findViewById(R.id.version_name);
                gameName =itemClicked.getText().toString();
                displayInputDialog(gameName);

            }

            private void displayInputDialog(final String gameName) {
                d=new Dialog(getActivity());
                d.setTitle("Hello");
                d.setContentView(R.layout.dialogue_window);
                final TextView nameEditTxt= (TextView) d.findViewById(R.id.nameEditText);
                Button addBtn= (Button) d.findViewById(R.id.addBtn);
                Button updateBtn= (Button) d.findViewById(R.id.updateBtn);
                nameEditTxt.setText(gameName+" was selected");
                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trackItems.save(gameName);
                        nameEditTxt.setText("");
                        d.dismiss();
                    }
                });
                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //GET DATA
                        nameEditTxt.setText("");
                        d.dismiss();
                    }
                });

                d.show();
            }

        });

    };

}
