package com.example.mynotesdz;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class Note implements Parcelable {
    private static final Random random = new Random();
    private String title;
    private String description;
    private String creationData;


    private static Note[] notes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Note[] getNotes() {
        return notes;
    }

    public String getCreationData() {
        return creationData;
    }

    public void setCreationData(String creationData) {
        this.creationData = creationData;
    }

    public static void setNotes(Note[] notes) {
        Note.notes = notes;
    }

    // Как только запустили приложение, отрабатывает конструкция
    static {
        notes = new Note[10];
        for (int i = 0; i < notes.length; i++) {
            notes[i] = Note.getNote(i);
        }
    }

    @SuppressLint("DefaultLocale")
    private static Note getNote(int index) {
        String title = String.format("Заметка %d", index);
        String description = String.format("Описание заметки %d", index);

        LocalDateTime localDateTime = LocalDateTime.now().plusDays(-random.nextInt(5));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String creationData = localDateTime.format(formatter);

        return new Note(title, description, creationData);

    }

    public Note(String title, String description, String creationData) {
        this.title = title;
        this.description = description;
        this.creationData = creationData;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    //упаковываем поля в стек
    @Override
    public void writeToParcel(Parcel parcel, int i) {
       parcel.writeString(title);
       parcel.writeString(description);
       parcel.writeString(creationData);
    }

    //распаковываем стек
    protected Note(Parcel parcel){
        title = parcel.readString();
        description = parcel.readString();
        creationData = parcel.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel parcel) {
        return new Note(parcel);
        }

        @Override
        public Note[] newArray(int i) {
            return new Note[i];
        }
    };
}