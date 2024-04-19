package com.example.project2;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.project2.databinding.DialogConfirmBinding;

public class AddBookDialog extends DialogFragment {


    private DialogConfirmBinding binding;
    private LibraryDataBase db;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogConfirmBinding.inflate(getLayoutInflater().from(getContext()));

        db = LibraryDataBase.getInstance(getActivity());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(binding.getRoot());
        builder.setTitle("Do you want to add a new book to the System?");




        binding.EMessage.setText("");


        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {






                Intent intent = new Intent(getContext(), NewBookActivity.class);
                getContext().startActivity(intent);
            }

        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                Intent intent = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(intent);
            }

        });

        return builder.create();


    }

    public static AddBookDialog newInstance(String mensaje) {
        AddBookDialog etd = new AddBookDialog();

        return etd;
    }


}