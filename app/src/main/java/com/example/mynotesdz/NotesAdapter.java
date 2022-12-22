package com.example.mynotesdz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    private Note[] arrayNotes;

    public void setArrayNotes(Note[] arrayNotes) {
        this.arrayNotes = arrayNotes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater.inflate(R.layout.fragment_notes_recycle, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(arrayNotes[position]);

    }

    @Override
    public int getItemCount() {
        return Note.getNotes().size();
    }


    //Вложенный класс, но можно и написать отдельно, главное указать в дженереке и методах свой холдер.
    //Но для компакности лучше искользовать тут.
    class MyViewHolder extends RecyclerView.ViewHolder {
        //private LinearLayout linearLayout;
       private TextView textViewNote;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             textViewNote = itemView.findViewById(R.id.text_view);

        }
        //Связываем контент с макетом
        public void bindContentWithLayout(Note note){
            textViewNote.setText(note.getTitle());

        }
    }
}
