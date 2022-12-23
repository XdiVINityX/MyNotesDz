package com.example.mynotesdz;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;


public class NotesFragment extends Fragment {

    static final String SELECTED_NOTE = "note";
    Note note;
    View dataContainer;

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
            note = (Note)savedInstanceState.getParcelable(SELECTED_NOTE);
        }
        dataContainer = view.findViewById(R.id.notes_container);
        initNotesItem(view.findViewById(R.id.notes_container));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem item = menu.findItem(R.id.menu_about_program);
        if (item != null){
            item.setVisible(false);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
    public void initNotesItem(){
        initNotesItem(dataContainer);
    }

    private void initNotesItem(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        linearLayout.removeAllViews();
        for (int i = 0; i < Note.getNotes().size(); i++) {
            //записываем ссылку результата работы инфлейтера
            View listItem = getLayoutInflater().inflate(R.layout.activity_main_fragment_container_list_item,linearLayout,false);
            linearLayout.addView(listItem);
            TextView textViewNote = listItem.findViewById(R.id.text_view);
            textViewNote.setTextSize(32);
            textViewNote.setText(Note.getNotes().get(i).getTitle());
            final int index = i;
            textViewNote.setOnClickListener(v -> {
                showNoteDetailPar(Note.getNotes().get(index));
            });
        }

    }

    private void showNoteDetailPar(Note note) {
        //Если портретная
        if (isPortrait()) {
            addDeteilFragmendPar(note);

        }
        //если ландшафная
        if (isLandscape()) {
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            if (fm.getBackStackEntryCount() >= 1) {
                fm.popBackStack();
            }
            addDeteilFragmendPar(note);
        }
    }

    private void addDeteilFragmendPar(Note note){
        NoteDetailFragment noteDetailFragment = NoteDetailFragment.newInstancePar(note);
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
