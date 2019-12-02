package com.example.roomdatabaseexample.BackgroundProcesses;

import android.os.AsyncTask;

import com.example.roomdatabaseexample.Database.Entity.NoteTable;
import com.example.roomdatabaseexample.Database.NoteDao;

public class GetSingleNoteWithAsync extends AsyncTask<Void, Void, Void> {
    private int DATABASE_RESPONSE = 1;
    private NoteDao noteDao;
    private int position;
    private NoteTable noteTable;
    public GetSingleNoteWithAsync(NoteDao noteDao, int position){
        this.noteDao = noteDao;
        this.position = position;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        noteTable = noteDao.getThisNote(position);
        return null;
    }

    public NoteTable getThisNote(){
        return noteTable;
    }
}
