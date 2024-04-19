package com.example.project2;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.project2.databinding.ActivityLogsBinding;

import java.util.List;

public class LogsActivity extends AppCompatActivity {

    private ListView  Listlogs;

    private List<Log> logs ;

    private LibraryDataBase db ;

    private ArrayAdapter<Log> logAdapter;
    ActivityLogsBinding binding ;

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityLogsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LibraryDataBase.getInstance(this);


        Listlogs = binding.logList;
        logs = db.log().getAll();

        logAdapter = new ArrayAdapter<>(this, R.layout.item_log, R.id.log_item, logs);
        Listlogs.setAdapter(logAdapter);


        binding.Blogok.setOnClickListener(  this::opendialog   );
/*
        binding.Badduser.setOnClickListener(this::onClick);
        binding.Badduser.setText("Login");
        Bundle data  = this.getIntent().getExtras()  ;
*/

    }

    private void opendialog (View v){

        dialogFragment =  AddBookDialog.newInstance("");
        dialogFragment.show(getSupportFragmentManager(), "");
    }

}
