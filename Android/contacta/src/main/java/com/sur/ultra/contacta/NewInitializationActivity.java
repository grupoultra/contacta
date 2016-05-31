package com.sur.ultra.contacta;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class NewInitializationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_initialization);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void acceptInitialization(View view){
        Toast.makeText(NewInitializationActivity.this, "Accept", Toast.LENGTH_SHORT).show();
    }

    public void cancelInitialization(View view){
        Toast.makeText(NewInitializationActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
    }

}
