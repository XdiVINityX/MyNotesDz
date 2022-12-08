package com.example.mynotesdz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class NoteDetailFragment extends Fragment {

    final static String SELECTED_NOTE = "note";
    Note note;


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
            note = arguments.getParcelable(SELECTED_NOTE);
            String data  = note.getCreationData();
            TextView textViewTitle = view.findViewById(R.id.title);
            TextView textViewDescription = view.findViewById(R.id.description);
            TextView textViewCreationData = view.findViewById(R.id.creationData);

            textViewTitle.setText(note.getTitle());
            textViewDescription.setText(note.getDescription());
            textViewCreationData.setText(data);
        }
    }

    public static NoteDetailFragment newInstanceSer(Note note){
        NoteDetailFragment noteDetailFragment = new NoteDetailFragment();
        Bundle arg = new Bundle();
        arg.putParcelable(SELECTED_NOTE,note);
        noteDetailFragment.setArguments(arg);
        return noteDetailFragment;
    }
}