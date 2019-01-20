package com.example.nadine.assign42019abdelkrimouajjit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class OrdersFragment extends Fragment  {
    /*****************************************************************
     * Initialing the variables
     ******************************************************************
     */

    private static final String TAG = "OrdersFragment";
    Spinner mSpinner;
    EditText mCustomerName;
    EditText meditOptional;
    Uri mPhotoURI;
    ImageView  mImageView;

    EditText mdeliveryAddress;
    boolean imageTaken= false;
    static final int REQUEST_TAKE_PHOTO = 2;
    private static final String TAG1 = "Assign3";
    /*****************************************************************
     * Calling the constructor.
     ******************************************************************
     */
    public OrdersFragment() {
        // Required empty public constructor
    }
    /*****************************************************************
     * Inflate the layout.
     ******************************************************************
     */
    /**
     *
     * The onCreateView method is called when Fragment should create its View object hierarchy,
     * either dynamically or via XML layout inflation.
     * @param inflater : Inflate the layout for this fragment.Defines the xml file for the fragment
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view =inflater.inflate(R.layout.fragment_orders, container, false);
         Log.d(TAG, "onCreateView: started.");
         mCustomerName = view.findViewById(R.id.editCustomer);
         mImageView = view.findViewById(R.id.imageView);
        dispatchTakePictureIntent();
       // createOrderSummary(view);
        Button send=(Button) view.findViewById(R.id.sendId);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
         return view;
    }
    /******************************************************************
     * Capturing images
     ******************************************************************
     */
    private void dispatchTakePictureIntent() {

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "my_tshirt_image_" + timeStamp + ".jpg";
                File file = new File(Environment.getExternalStorageDirectory(), imageFileName);
                mPhotoURI = Uri.fromFile(file);
                Log.i(TAG, mPhotoURI.toString());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI.toString());
                startActivityForResult(intent, REQUEST_TAKE_PHOTO);
            }

        });
    }
    /******************************************************************
     *  The activity returns with the photo.
     ******************************************************************
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK)
        {
            CharSequence text = "Image Taken successfully";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getContext(), text, duration);
            toast.show();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.notification_title).setMessage(R.string.image_confirm).setPositiveButton("OK", null).show();
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        mImageView.setImageBitmap(photo);
    }

    public void sendEmail()
    {

        String customerName=mCustomerName.getText().toString();
        String orderMessage = getString(R.string.customer_name) + " " + customerName;
        orderMessage += "\n" + "\n" + getString(R.string.order_message_1);
//        String optionalInstructions = meditOptional.getText().toString();
        orderMessage += "\n" + getString(R.string.order_collect_message) + ((CharSequence) mSpinner.getSelectedItem()).toString() + " days";
        orderMessage += "\n" + getString(R.string.order_end_message) + "\n" + customerName;

        MyListener myListener= (MyListener) getActivity();
        myListener.sendEmail(orderMessage,mPhotoURI.toString());
//                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    intent.setType("*/*");
//                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.to_email)});
//                    intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
//                    intent.putExtra(Intent.EXTRA_STREAM,mPhotoURI.toString());
//                    intent.putExtra(Intent.EXTRA_TEXT, orderMessage);
//                    if (intent.resolveActivity(getActivity().getPackageManager()) != null)
//                    {
//                        getActivity().startActivity(intent);
//                    }

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
}
/**
 *
 * This method is called after the parent Activity's onCreate() method has completed.
 * Accessing the view hierarchy of the parent activity must be done in the onActivityCreated.
 * At this point, it is safe to search for activity View objects by their ID,
 * @param savedInstanceState
 *
 * getActivity(), which returns the activity associated with a fragment.
 * The activity is a context (since Activity extends Context).
 */
 /*   @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView  mImageView = getView().findViewById(R.id.imageView);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "my_tshirt_image_" + timeStamp + ".jpg";
                File file = new File(Environment.getExternalStorageDirectory(), imageFileName);
                mPhotoURI = Uri.fromFile(file);
                Log.i(TAG, mPhotoURI.toString());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI.toString());
                getActivity().startActivityForResult(intent, REQUEST_TAKE_PHOTO);

            }
        });

    }*/