package com.example.mynotesdz;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class NotesFragment extends Fragment {


    static final String SELECTED_NOTE = "note";
    Note note;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SELECTED_NOTE,note);
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
        if (savedInstanceState != null){
            note = (Note)savedInstanceState.getSerializable(SELECTED_NOTE);
        }

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
                showNoteDetailSer(Note.getNotes()[index]);
            });
        }

    }

    private void showNoteDetailSer(Note note) {
        //Если портретная
        if (isPortrait()) {
            addDeteilFragmendSer(note);
        }
        //если ландшафная
        if (isLandscape()) {
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            if (fm.getBackStackEntryCount() >= 1) {
                fm.popBackStack();
            }
            addDeteilFragmendSer(note);
        }
    }

    private void addDeteilFragmendSer(Note note){
        NoteDetailFragment noteDetailFragment = NoteDetailFragment.newInstanceSer(note);
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_description, noteDetailFragment)
                .addToBackStack("")
                .commit();

    }


    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private boolean isPortrait() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }


}
