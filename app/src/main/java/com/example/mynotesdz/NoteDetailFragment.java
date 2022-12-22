package com.example.mynotesdz;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class NoteDetailFragment extends Fragment {

    final static String SELECTED_NOTE = "note";
    Note note;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_detail, container, false);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // раздули меню активности детализация
        inflater.inflate(R.menu.detail_menu_by_fragmen,menu);
        //меню от активити
        MenuItem item = menu.findItem(R.id.menu_about_program);
        if (item != null){
            item.setVisible(false);
        }
        item = menu.findItem(R.id.menu_add);
        if (item != null){
            item.setVisible(false);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_delete:
                Note.getNotes().remove(note);
                note = null;
                updateData();
                requireActivity().getSupportFragmentManager().popBackStack();
                Toast.makeText(getContext(),"Заметака удалена", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    // проходим по всему стеку
    private void updateData(){
        for (Fragment fragment: requireActivity().getSupportFragmentManager().getFragments()) {
            if (fragment instanceof NotesFragment){
                ((NotesFragment)fragment).initNotesItem();
                break;
            }
        }
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

    public static NoteDetailFragment newInstancePar(Note note){
        NoteDetailFragment noteDetailFragment = new NoteDetailFragment();
        Bundle arg = new Bundle();
        arg.putParcelable(SELECTED_NOTE,note);
        noteDetailFragment.setArguments(arg);
        return noteDetailFragment;
    }
}