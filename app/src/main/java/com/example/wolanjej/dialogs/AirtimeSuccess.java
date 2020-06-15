package com.example.wolanjej.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.wolanjej.R;
import com.example.wolanjej.models.ServicesModel;

import java.util.Collections;
import java.util.List;


public class AirtimeSuccess extends AppCompatDialogFragment {
    private TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7,textView8;
    private List<ServicesModel> data= Collections.emptyList();
    private ServicesModel current;
    LayoutInflater inflater;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        inflater = requireActivity().getLayoutInflater();
        View content =  inflater.inflate(R.layout.transfer_success_popup, null);
        builder.setView(content);
        textView1 = (TextView) content.findViewById(R.id.trasferStatus);
        textView2 = (TextView) content.findViewById(R.id.trasferStatus);
        textView3 = (TextView) content.findViewById(R.id.trasferStatus);
        textView4 = (TextView) content.findViewById(R.id.trasferStatus);
        textView5 = (TextView) content.findViewById(R.id.trasferStatus);
        textView6 = (TextView) content.findViewById(R.id.trasferStatus);
        textView7 = (TextView) content.findViewById(R.id.trasferStatus);
        textView8 = (TextView) content.findViewById(R.id.trasferStatus);

//
//        ((TextView)popupWindow.getContentView().findViewById(R.id.refFee)).setText(sendfee);
//        ((TextView)popupWindow.getContentView().findViewById(R.id.transBalance)).setText(myBalance);
//        ((TextView)popupWindow.getContentView().findViewById(R.id.refncNumber)).setText(sendIDReference);
//        ((TextView)popupWindow.getContentView().findViewById(R.id.amoutSent)).setText(sendAmount);
//        ((TextView)popupWindow.getContentView().findViewById(R.id.recpName)).setText(phoneName);
//        ((TextView)popupWindow.getContentView().findViewById(R.id.recpNumber)).setText("+"+phoneNumber);
//        ((TextView)popupWindow.getContentView().findViewById(R.id.trasferStatus)).setText("Transfer Done!");
//        ((ImageView) popupWindow.getContentView().findViewById(R.id.imageTrasfer)).setImageResource(R.mipmap.button_rounded);
        return builder.create();
    }
}
