package com.example.nadine.assign42019abdelkrimouajjit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class OrdersFragment extends Fragment {
    /*****************************************************************
     * Initialing the variables
     ******************************************************************
     */
    Fragment OrdersFragment = this;
    private static final String TAG = "OrdersFragment";
    Spinner mSpinner;
    EditText mCustomerName;
    EditText meditOptional;
    Uri mPhotoURI;           //declare the filename and the Uri for use below
    File mPhotoFile = null;
    ImageView mImageView;

    EditText mdeliveryAddress;
    boolean imageTaken = false;
    static final int REQUEST_TAKE_PHOTO = 2;
    private static final String TAG1 = "Assign3";

    /*****************************************************************
     * Calling the constructor.
     ******************************************************************
     */
    public OrdersFragment() {
        // Required empty public constructor
    }
    // Inflate the layout

    /**
     * The onCreateView method is called when Fragment should create its View object hierarchy,
     * either dynamically or via XML layout inflation.
     *
     * @param inflater           : Inflate the layout for this fragment.Defines the xml file for the fragment
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        Log.d(TAG, "onCreateView: started.");

        mdeliveryAddress = (EditText) view.findViewById(R.id.editOptional);
        mImageView = view.findViewById(R.id.imageView);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTempFile();
                dispatchTakePictureIntent();
            }

        });
        Button send = (Button) view.findViewById(R.id.sendId);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        SharedPreferences prefs=getActivity().getSharedPreferences("myprefs",Context.MODE_PRIVATE);
        String myStore=prefs.getString("store","none");
        mdeliveryAddress.setText(myStore);
        return view;
    }
    // Capturing images

    private File createTempFile() {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName = "My_Image_" + timeStamp + "_";
        //we should get a general reference to externalstorage for images.
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES); //declare an image file
        File myImage = null;
        //try catch, ensure it doesn't crash if the file fails to be taken
        try {
        //make an empty file
            myImage = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            String error = String.valueOf(e);
            Log.e(TAG, error);
        //toaster alert to let the user know there's an issue
            Toast toast = Toast.makeText(getContext(), "Please try retaking your photo!", Toast.LENGTH_LONG);
            toast.show();
        }
        return myImage;

    }

    private void dispatchTakePictureIntent() {

        //start the intent.
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // check to see if the phone actually has a camera.
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) // first let’s call our new method to get the photofile
        {
            mPhotoFile = createTempFile();
        // Continue only if the File was successfully created
            if (mPhotoFile != null) {//here we grab the Uri, (note we are using the authority it's our applicationID again)
                mPhotoURI = FileProvider.getUriForFile(getContext(), "com.example.nadine.assign42019abdelkrimouajjit.FileProvider", mPhotoFile); //take the photo replacing the file at the location.
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            } else {
                Toast toast = Toast.makeText(getContext(), "There was a problem saving please retry", Toast.LENGTH_LONG);
                toast.show();
            }
            }else{
        //toaster alert to let the user know there's an issue with the camera
            Toast toast = Toast.makeText(getContext(), "There seems to be an issue with your camera", Toast.LENGTH_LONG);
        }


        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {

        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK)
        {


            CharSequence text = "Image Taken successfully";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getContext(), text, duration);
            toast.show();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.notification_title).setMessage(R.string.image_confirm).setPositiveButton("OK", null).show();
        imageTaken = true;
        Glide.with(OrdersFragment)
                .load(mPhotoURI)
                .into(mImageView);

    }

    public void sendEmail()
    {
        mCustomerName = (EditText) getView().findViewById(R.id.editCustomer);
        String customerName=mCustomerName.getText().toString();
        mdeliveryAddress = (EditText) getView().findViewById(R.id.editOptional);
        String deliveryAddress = mdeliveryAddress.getText().toString();

        if (customerName.matches("")) {
            Toast.makeText(getContext(), "Please enter your Name", Toast.LENGTH_SHORT).show();

        } else if  (deliveryAddress.matches(""))
        {
            Toast.makeText(getContext(), "Please enter your delivery address", Toast.LENGTH_SHORT).show();
        }else if (!imageTaken)
        {
            Toast.makeText(getContext(), "Please take a picture ", Toast.LENGTH_SHORT).show();
        } else

        {

            String orderMessage = getString(R.string.customer_name) + " " + customerName;
            orderMessage += "\n" + "\n" + getString(R.string.order_message_1);
            String optionalInstructions = meditOptional.getText().toString();
            orderMessage += "\n" + getString(R.string.order_collect_message) + ((CharSequence) mSpinner.getSelectedItem()).toString() + " days";
            orderMessage += "\n" + optionalInstructions ;
            orderMessage += "\n" + getString(R.string.order_end_message) + "\n" + customerName;

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.to_email)});
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
            intent.putExtra(Intent.EXTRA_STREAM,mPhotoURI);
            intent.putExtra(Intent.EXTRA_TEXT, orderMessage);
            if (intent.resolveActivity(getActivity().getPackageManager()) != null)
            {
                getActivity().startActivity(intent);
            }
        }

    }

    /**
     * This event is triggered soon after onCreateView().
     * onViewCreated() is only called if the view returned from onCreateView() is non-null.
     * Any view setup should occur here.  E.g., view lookups and attaching view listeners.
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        meditOptional = (EditText) view.findViewById(R.id.editOptional);
        meditOptional.setImeOptions(EditorInfo.IME_ACTION_DONE);
        meditOptional.setRawInputType(InputType.TYPE_CLASS_TEXT);
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        mCustomerName = (EditText) view.findViewById(R.id.editCustomer);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.ui_time_entries, R.layout.spinner_days);
        mSpinner.setAdapter(adapter);


    }
    public void myitems() {
        trackItems mitems=new trackItems();

    }
}
