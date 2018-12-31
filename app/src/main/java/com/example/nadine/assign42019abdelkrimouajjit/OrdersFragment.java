package com.example.nadine.assign42019abdelkrimouajjit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdersFragment extends Fragment  {

    Uri mPhotoURI;
    Spinner mSpinner;
    EditText mCustomerName;
    EditText meditOptional;
    EditText mdeliveryAddress;
    boolean imageTaken= false;
    ImageView mImageView;
    static final int REQUEST_TAKE_PHOTO = 2;
    private static final String TAG = "Assign3";

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =inflater.inflate(R.layout.fragment_orders, container, false);
        meditOptional = (EditText) view.findViewById(R.id.editOptional);
        meditOptional.setImeOptions(EditorInfo.IME_ACTION_DONE);
        meditOptional.setRawInputType(InputType.TYPE_CLASS_TEXT);
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        mCustomerName = (EditText) view.findViewById(R.id.editCustomer);
        mImageView=view.findViewById(R.id.imageView);
        mImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,
                        REQUEST_TAKE_PHOTO );
            }
        });
        return view;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.ui_time_entries, R.layout.spinner_days);
        mSpinner.setAdapter(adapter);
    }


}


