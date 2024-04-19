package com.example.project2;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.ActivitySelectBookBinding;

import java.util.List;


public class SelectBookActivity extends AppCompatActivity {

    private ActivitySelectBookBinding binding;
    private LibraryDataBase db;
    Spinner spinerbook ;

    private
    ArrayAdapter<Book> adapter ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle data  = this.getIntent().getExtras()  ;
        String genre = data.getString("genre") ;

        binding.tittleselectbook.setText( binding.tittleselectbook.getText() + " - " + genre);
        db = LibraryDataBase.getInstance(this);

        spinerbook = binding.spinnerbook ;
        List<Book> libros  =   db.book().getAllDisponibleByGenre(genre);

        if (libros.size() == 0 ){
            binding.menssageselectbook.setText( "No book available for the genre ");
            binding.Bselectbook.setText("EXIT!");
            binding.Bselectbook.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        }else {
            binding.Bselectbook.setOnClickListener(this::toLogin);
        }

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item ,libros);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerbook.setAdapter(adapter);



    }

    private void toLogin(View v) {
        Book bookselect = (Book) spinerbook.getSelectedItem()  ;
        Log.d("book" , bookselect.getId() + " - "+ bookselect.getTitle());
        Bundle data = new Bundle() ;
        data.putString("book" , bookselect.getTitle());
        Intent intent = new Intent( this , LoginActivity.class) ;
        intent.putExtras(data) ;
        startActivity(intent);

    }
}
