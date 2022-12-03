package com.example.mynotesdz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
        NotesFragment notesFragment = new NotesFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container_list,notesFragment)
                .commit();
        }
    }




}