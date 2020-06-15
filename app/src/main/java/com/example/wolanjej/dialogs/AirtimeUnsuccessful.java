package com.example.wolanjej.dialogs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.wolanjej.R;
import com.example.wolanjej.Top_up;
import com.example.wolanjej.models.ServicesModel;

import java.util.Collections;
import java.util.List;

public class AirtimeUnsuccessful extends AppCompatDialogFragment {

    private TextView textView1, textView2, textView3;
    private List<ServicesModel> data= Collections.emptyList();
    private ServicesModel current;
    LayoutInflater inflater;
    private Button button;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        inflater = requireActivity().getLayoutInflater();
        View content =  inflater.inflate(R.layout.transfer_unsuccessful_popup, null);
        builder.setView(content);


        button = content.findViewById(R.id.try_again_unsuccessful);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Top_up.class);startActivity(intent);
            }
        });
        textView1 = (TextView) content.findViewById(R.id.reference_number);
        textView2 = (TextView) content.findViewById(R.id.amount_sent_note);
        textView3 = (TextView) content.findViewById(R.id.balance_note);

        ServicesModel servicesModel = new ServicesModel();
//        textView1.setText(servicesModel.product_name);
//        textView2.setText(servicesModel.amountTransaction);
//        textView3.setText(servicesModel.amountTransaction);


        return builder.create();
    }
}