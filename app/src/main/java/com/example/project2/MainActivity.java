package com.example.project2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.ActivityMainBinding;
public class MainActivity  extends AppCompatActivity {

    private ActivityMainBinding binding;

    private LibraryDataBase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.Bcreate.setOnClickListener(v -> startActivity(new Intent(this, AddUserActivity.class)));
        binding.Bplace.setOnClickListener(v -> startActivity(new Intent(this, SelectGenreActivity.class)));
        binding.Bmanage.setOnClickListener(v -> startActivity(new Intent(this, LoginAdminActivity.class)));

        db = LibraryDataBase.getInstance(this);
        db.populateInitialData();

    }


}
