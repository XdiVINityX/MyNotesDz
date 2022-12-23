package com.example.mynotesdz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class NotesRecycleFragment extends Fragment {
    NotesAdapter notesAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_recycle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        InitNotesAdapter();
        initRecycler(view);


    }

    private void InitNotesAdapter(){
        notesAdapter = new NotesAdapter();
        notesAdapter.setArrayListNotesData(getData());
    }


    private void initRecycler(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(notesAdapter);
    }


     private ArrayList<Note> getData(){
        ArrayList<Note> data = Note.getNotes();
        return data;
    }




}
