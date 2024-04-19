package com.example.project2;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.example.project2.databinding.DialogConfirmBinding ;

public class ConfirmDialog extends DialogFragment {


    private DialogConfirmBinding binding;
    private LibraryDataBase db;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogConfirmBinding.inflate(getLayoutInflater().from(getContext()));

        db = LibraryDataBase.getInstance(getActivity());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(binding.getRoot());

        String   title  = getArguments().getString("title");
        builder.setTitle(title);

        String   reser  = getArguments().getString("reservation");


        binding.EMessage.setText(reser ) ;


        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                Intent intent = new Intent( getContext() , MainActivity.class ) ;
                getContext().startActivity(intent);
            }

        });

        return builder.create();





    }


    public static ConfirmDialog newInstance(String mensaje , String title) {
        ConfirmDialog etd = new ConfirmDialog();
        Bundle args = new Bundle();
        args.putString("reservation", mensaje);
        args.putString("title", title);
        etd.setArguments(args);
        return etd;
    }
}
