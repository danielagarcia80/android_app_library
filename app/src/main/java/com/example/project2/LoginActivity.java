package com.example.project2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.project2.databinding.ActivityAddUserBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityAddUserBinding binding;

    private LibraryDataBase db ;


    private DialogFragment dialogFragment;

    private int intentos =  0 ;

    String bookname ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityAddUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LibraryDataBase.getInstance(this);

        binding.Badduser.setOnClickListener(this::onClick);
        binding.Badduser.setText("Login");
        Bundle data  = this.getIntent().getExtras()  ;
        bookname = data.getString("book") ;


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
            if (us.getPassword().equals(pass)){
                String mensaje  = "Access  " ;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(this  , mensaje, duration);
                toast.show();

                Book book =  db.book().findByTitle(bookname)  ;
                Reservation res = new Reservation(book.getId() ,us.getUsername() ) ;
                long nres =  db.reservation().addReservation(res);

                Log log = new Log( " Place Hold " , us.getUsername()   ) ;
                log.setIdreservation( Integer.parseInt( nres +""));
                db.log().addLog(log);

                dialogFragment = ConfirmDialog.newInstance(" - Customer username: "+us.getUsername()+ " \n - Title:  "+bookname+  " \n - Reservation Number:" + nres , "Login Succesful");
                dialogFragment.show(getSupportFragmentManager(), "");


            }else{
                String mensaje  = "Incorrect Password " ;

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


            }

        }



    }
}
