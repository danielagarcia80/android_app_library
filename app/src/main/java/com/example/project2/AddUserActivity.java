package com.example.project2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.ActivityAddUserBinding;




public class AddUserActivity extends AppCompatActivity {

    private ActivityAddUserBinding binding;

    private LibraryDataBase db ;


    private int intentos =  0 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityAddUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LibraryDataBase.getInstance(this);

        binding.Badduser.setOnClickListener(this::onClick);


    }


    private void onClick(View v) {
        String username = binding.qUsername.getText().toString();
        String pass = binding.qPassword.getText().toString();

        String mensaje  = "" ;
        boolean add = true ;
        if (username.isEmpty()){
            add = false ;
            mensaje = "username is blank. " ;
            if (intentos > 0 ){
                mensaje += " TRY AGAIN !" ;
            }
            if (intentos > 1 ){
                mensaje  = " exceeded number of attempts" ;
            }



        }
        if (pass.isEmpty()){
            add = false ;
            mensaje += "password is blank. " ;
            if (intentos > 0 ){
                mensaje += " TRY AGAIN !" ;
            }
            if (intentos > 1 ){
                mensaje  = " exceeded number of attempts" ;
            }

        }
        if (username.equals("!admin2")){
            add = false ;
            mensaje = "you cannot use it as a username." ;
        }

        User user1 = db.user().findByUsername(username) ;
        if (user1 != null ){
            add = false ;
            binding.qUsername.setText("");
            binding.qPassword.setText("");

            mensaje = "username already exists" ;
            if (intentos > 0 ){
                mensaje += " TRY AGAIN !" ;
            }
            if (intentos > 1 ){
                mensaje  = " exceeded number of attempts" ;
            }


        }

        if (add){

            User user = new User(username , pass);
            db.user().addUser(user);
            mensaje = "Account Created Successfully!" ;

            Log log = new Log( " New Account " , username) ;
            db.log().addLog(log);

        }

        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this  , mensaje, duration);
        toast.show();

        if (add  || intentos  >=2 ) {

            startActivity(new Intent(this, MainActivity.class));
        }else{
            intentos ++ ;
        }








    }
}
