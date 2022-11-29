package com.example.mynotesdz;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class NotesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initNotes(view.findViewById(R.id.notes_container));
    }

    private void initNotes(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        for (int i = 0; i < Note.getNotes().length; i++) {
            TextView textViewNote = new TextView(getContext());
            textViewNote.setTextSize(24);
            textViewNote.setText(Note.getNotes()[i].getTitle());
            linearLayout.addView(textViewNote);

            final int index = i;
            textViewNote.setOnClickListener(v -> {
                    showNoteDetail(index);
            });
        }

    }

    private void showNoteDetail(int index) {
        if (isPortrait()){
            NoteDetailFragment noteDetailFragment = NoteDetailFragment.newInstanse(index);
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_description, noteDetailFragment)
                    .addToBackStack("")
                    .commit();
        }
        if (isLandscape()){
            NoteDetailFragment noteDetailFragment = NoteDetailFragment.newInstanse(index);
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_description, noteDetailFragment)
                    .commit();
        }
    }
    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private boolean isPortrait(){
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

}
