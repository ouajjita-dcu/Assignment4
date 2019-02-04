package com.example.nadine.assign42019abdelkrimouajjit;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import android.widget.Spinner;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import static android.app.Activity.RESULT_OK;

public class OrdersFragment extends Fragment {
    Fragment OrdersFragment = this;
    private static final String TAG = "OrdersFragment";
    Spinner mSpinner;
    EditText mCustomerName;
    EditText meditOptional;
    Uri mPhotoURI;           //declare the filename and the Uri for use below
    File mPhotoFile = null;
    ImageView mImageView;
    boolean imageTaken = false;
    static final int REQUEST_TAKE_PHOTO = 2;
    public OrdersFragment() {
        // Required empty public constructor
    }
    @Override
    //  Retrieve data from SharedPreferences before the order fragment is active By using the override onResume method(The lifecycle of a fragment).
    // The onResume event is called when the fragment starts and when the user comes back from another fragment,
    // in that case the SettingsActivity we created to manage the preferences.
    public void onResume() {
        super.onResume();
        getPrefs();
    }

    /**
     * The onCreateView method is called when Fragment should create its View object hierarchy,
     * either dynamically or via XML layout inflation.
     * @param inflater: Inflate the layout for this fragment.Defines the xml file for the fragment
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Override
    // Inflate the layout
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment.The resource ID of the layout that i want to inflate.
        // The ViewGroup to be the parent of the inflated layout. Passing the container is important in order for the system to apply layout parameters to the root view of the inflated layout
        // specified by the parent view in which it's going.
        // A boolean indicating whether the inflated layout should be attached to the ViewGroup
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        // Creating a log that indicate the fragment is onCreateView .
        Log.d(TAG, "onCreateView: started.");
        mImageView = view.findViewById(R.id.imageView);
        // Calling two methods when the image is clicked
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTempFile();
                dispatchTakePictureIntent();
            }

        });
        // Handling the button when is clicked.
        Button send = view.findViewById(R.id.sendId);
        send.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view: Taking view as parameter.
             */
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        // Returning the view.
        return view;
    }
    // Creating a file to hold a image.
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
    // Taking a photo with a camera app.
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
                // start the image capture Intent.
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
    // Handling the results
    /**
     *
     * @param requestCode : The request code that to startActivityForResult().
     * @param resultCode : This is either RESULT_OK if the operation was successful or RESULT_CANCELED
     * @param imageReturnedIntent: An Intent that carries the result data.
     */
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
        // Building Alert Dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.notification_title).setMessage(R.string.image_confirm).setPositiveButton("OK", null).show();
        imageTaken = true;
        // Glide is a fast and efficient image loading library for Android focused.
        // Reference:https://bumptech.github.io/glide/.
        Glide.with(OrdersFragment)
                .load(mPhotoURI)
                .into(mImageView);

    }
    // Handling sending the email .
    public void sendEmail()
    {
        mCustomerName = getView().findViewById(R.id.editCustomer);
        String customerName=mCustomerName.getText().toString();
        EditText mdeliveryAddress = (EditText) getView().findViewById(R.id.editOptional);
        String deliveryAddress = mdeliveryAddress.getText().toString();

        SharedPreferences prefs=getActivity().getSharedPreferences("myprefsGames",Context.MODE_PRIVATE);
        String mySelectedGame=prefs.getString("itemSelected","none");


        if (customerName.matches("")) {
            Toast.makeText(getContext(), "Please enter your Name", Toast.LENGTH_SHORT).show();

        } else if  (deliveryAddress.matches(""))
        {
            Toast.makeText(getContext(), "Please enter your delivery address", Toast.LENGTH_SHORT).show();
        }else if (!imageTaken)
        {
            Toast.makeText(getContext(), "Please take a picture ", Toast.LENGTH_SHORT).show();
        }
//        else if (mPhotoURI.equals(Uri.EMPTY)&& (mPhotoURI == null))
//        {
//            Toast.makeText(getContext(), "No picture was taken,Please re-take your picture ", Toast.LENGTH_SHORT).show();
//        }
        else
        {

            String orderMessage = getString(R.string.customer_name) + " " + customerName;
            orderMessage += "\n" + "\n" + getString(R.string.order_message_1);
            orderMessage += "\n" + "Your selected item is : "+ mySelectedGame ;
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

        meditOptional = view.findViewById(R.id.editOptional);
        meditOptional.setImeOptions(EditorInfo.IME_ACTION_DONE);
        meditOptional.setRawInputType(InputType.TYPE_CLASS_TEXT);
        mSpinner =  view.findViewById(R.id.spinner);
        mCustomerName = view.findViewById(R.id.editCustomer);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.ui_time_entries, R.layout.spinner_days);
                mSpinner.setAdapter(adapter);

    }
    // Managing the SharedPrefences by using the method getPrefs().
    private void getPrefs() {
        // Create SharedPreferences.
        final SharedPreferences prefs=getActivity().getSharedPreferences("myprefs",Context.MODE_PRIVATE);
        final String myStore=prefs.getString("store","Delivery Address");
        final EditText mdeliveryAddress =getView().findViewById(R.id.editOptional);
        mdeliveryAddress.setText(myStore);
    }

}
