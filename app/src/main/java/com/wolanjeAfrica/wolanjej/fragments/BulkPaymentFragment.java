package com.wolanjeAfrica.wolanjej.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.wolanjeAfrica.wolanjej.ConfirmSingleTransfer40;
import com.wolanjeAfrica.wolanjej.ConfirmTransferToPhone52;
import com.wolanjeAfrica.wolanjej.R;


public class BulkPaymentFragment extends Fragment implements View.OnClickListener {
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    private static final int FILE_SELECT_CODE = 0;
    private static final String TAG = "BulkPaymentsMain";
    private String path;
    private Intent myintent;
    private ImageView imageView1, imageView2;
    private View v;
    private ImageButton imageButton;
    private Button button;
    private EditText editText;


    public BulkPaymentFragment() {
        // Required empty public constructor
    }


    public static BulkPaymentFragment newInstance(String param1, String param2) {
        BulkPaymentFragment fragment = new BulkPaymentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_bulkpayment, container, false);
        imageView1 = (ImageView) v.findViewById(R.id.cancelCsvUpload);
        imageView2 = (ImageView) v.findViewById(R.id.uploadCsvFile);
        imageButton = (ImageButton) v.findViewById(R.id.uploadfilebtn);
        imageButton.setOnClickListener(BulkPaymentFragment.this);
        button = (Button) v.findViewById(R.id.buttonContinuefbulk);
        button.setOnClickListener(BulkPaymentFragment.this);
        editText = (EditText) v.findViewById(R.id.txt_amount_fb);
        return v;
    }

    public void loadCopyOfFile() {
        myintent = new Intent(Intent.ACTION_GET_CONTENT);
        myintent.setType("*/*");
        myintent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(
                    Intent.createChooser(myintent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == getActivity().RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d(TAG, "File Uri: " + uri.toString());
                    // Get the path
                    path = getPath(uri);
                    imageView1.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.INVISIBLE);
                    Log.d(TAG, "File Path: " + path);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getPath(Uri uri) {

        String path = null;
        String[] projection = {MediaStore.Files.FileColumns.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);

        if (cursor == null) {
            path = uri.getPath();
        } else {
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(projection[0]);
            path = cursor.getString(column_index);
            cursor.close();
        }

        return ((path == null || path.isEmpty()) ? (uri.getPath()) : path);
    }



    private void MoveToBulkpayments03() {
        String amount = editText.getText().toString();
        if (!amount.isEmpty() && path != null) {
            button.setBackgroundColor(getResources().getColor(R.color.warm_purple));
            myintent = new Intent(getContext(), ConfirmSingleTransfer40.class);
            myintent.putExtra("Class", "BulkPaymentFrag");
            myintent.putExtra(EXTRA_AMOUNT, amount);
//            myintent.putExtra(EXTRA_PHONENAME, phoneName);
//            myintent.putExtra(EXTRA_PHONENUMBER, phone);
            startActivity(myintent);
        } else {
            button.setEnabled(false);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uploadfilebtn:
                loadCopyOfFile();
                break;
            case R.id.buttonContinuefbulk:
//                MoveToBulkpayments03();
                break;
        }
    }

}

