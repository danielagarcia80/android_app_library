package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.ActivityAddUserBinding;

import java.util.List;

public class LoginAdminActivity extends AppCompatActivity {


    private int intentos ;
    private LibraryDataBase db ;


    ActivityAddUserBinding binding ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityAddUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LibraryDataBase.getInstance(this);

        binding.Badduser.setOnClickListener(this::onClick);
        binding.Badduser.setText("Login");






    }


    private void onClick(View v) {
        String pass =  binding.qPassword.getText().toString() ;
        String user =  binding.qUsername.getText().toString() ;

        User us = db.user().findByUsername(user) ;



        if (us == null ){
            String mensaje  = "User not exits" ;

            if (intentos > 0 ){
                mensaje += " TRY AGAIN !" ;
            }


            if (intentos > 1 ){
                mensaje  = " exceeded number of attempts" ;
            }

            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this  , mensaje, duration);
            toast.show();

            if (intentos  >=2 ) {

                startActivity(new Intent(this, MainActivity.class));
            }



        }else {
            if (pass.equals("!admin2") && user.equals("!admin2")  ) {
                String mensaje = "information is valid.  ";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(this, mensaje, duration);
                toast.show();
                startActivity(new Intent(this, LogsActivity.class));

            } else {
                String mensaje = "Incorrect Password ";

                if (intentos > 0) {
                    mensaje += " TRY AGAIN !";
                }
                if (intentos > 1) {
                    mensaje = " exceeded number of attempts";
                }

                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(this, mensaje, duration);
                toast.show();

                if (intentos >= 2) {

                    startActivity(new Intent(this, MainActivity.class));
                }


            }
        }

    }




}
