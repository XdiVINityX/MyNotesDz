package com.example.mynotesdz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.Calendar;


public class NoteDetailFragment extends Fragment {

    final static String ARG_INDEX = "index";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_detail, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null){
            int index = arguments.getInt(ARG_INDEX);
            String data  = Note.getNotes()[index].getCreationData().toString();
            TextView textViewTitle = view.findViewById(R.id.title);
            TextView textViewDescription = view.findViewById(R.id.description);
            TextView textViewCreationData = view.findViewById(R.id.creationData);

            textViewTitle.setText(Note.getNotes()[index].getTitle());
            textViewDescription.setText(Note.getNotes()[index].getDescription());
            textViewCreationData.setText(data);
        }
    }

    public static NoteDetailFragment newInstanse(int index){
        NoteDetailFragment noteDetailFragment = new NoteDetailFragment();
        Bundle arg = new Bundle();
        arg.putInt(ARG_INDEX,index);
        noteDetailFragment.setArguments(arg);
        return noteDetailFragment;
    }
}