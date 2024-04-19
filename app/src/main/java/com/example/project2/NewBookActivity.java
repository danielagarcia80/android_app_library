package com.example.project2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.project2.databinding.ActivityNewbookBinding;

public class NewBookActivity extends AppCompatActivity {

    private ActivityNewbookBinding binding;

    private LibraryDataBase db ;

    ArrayAdapter<String> adapter ;

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityNewbookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LibraryDataBase.getInstance(this);

        binding.Bcreatebook.setOnClickListener(this::onClick);

        Spinner spinergenre = binding.spinnergenre ;
        String  genres []  = new String[ ] {  "Memoir" , "Computer Science" ,"Fiction"} ;



        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item ,genres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinergenre.setAdapter(adapter);

    }


    private void onClick(View v) {

        String title = binding.qTitle.getText().toString();
        String  author = binding.qAuthor.getText().toString();
        String genre = binding.spinnergenre.getSelectedItem().toString();


        if (title.isEmpty()){

            dialogFragment = ConfirmDialog.newInstance(" title is empty "  , "Error ");
            dialogFragment.show(getSupportFragmentManager(), "");

            return ;
        }
        if (author.isEmpty() ){

            dialogFragment = ConfirmDialog.newInstance(" author is empty "  , "Error ");
            dialogFragment.show(getSupportFragmentManager(), "");
            return ;
        }
        Book exist  = db.book().findByTitle(title) ;

        if (exist == null ) {

            Book book = new Book(title, author, genre);

            db.book().addBook(book);
            String mensaje = "Book added Successfully!";

            Log log = new Log(" New Book ", title);
            db.log().addLog(log);

            dialogFragment = ConfirmDialog.newInstance(" Title: " + title +" - Author:"+ author +" - Genre:" +genre  , "Book added Successfully! ");
            dialogFragment.show(getSupportFragmentManager(), "");


        }else {

            String mensaje = "Book already Exists!";
            dialogFragment = ConfirmDialog.newInstance(" Title: " + title +" Already Exists!" , "Book already Exists! ");
            dialogFragment.show(getSupportFragmentManager(), "");
        }



    }
}
