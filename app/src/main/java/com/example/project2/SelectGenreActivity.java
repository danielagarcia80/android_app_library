package com.example.project2;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



import com.example.project2.databinding.ActivitySelectGenreBinding ;
public class SelectGenreActivity extends AppCompatActivity {

    private ActivitySelectGenreBinding binding;
    ArrayAdapter<String> adapter ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivitySelectGenreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Spinner spinergenre = binding.spinnergenre ;
        String  genres []  = new String[ ] {  "Memoir" , "Computer Science" ,"Fiction"} ;



        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item ,genres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinergenre.setAdapter(adapter);


        binding.Bselectgenre.setOnClickListener(v ->  {
            String genreselec =  spinergenre.getSelectedItem().toString();
            Log.d("select " , genreselec);
            Bundle data = new Bundle() ;
            data.putString("genre" , genreselec);
            Intent intent = new Intent( this , SelectBookActivity.class) ;
            intent.putExtras(data) ;
            startActivity(intent);
        });



    }
}
