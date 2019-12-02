package com.example.roomdatabaseexample.BackgroundProcesses;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.roomdatabaseexample.Database.Entity.NoteTable;
import com.example.roomdatabaseexample.Database.NoteDao;

public class InsertWithAsync extends AsyncTask<NoteTable, Void, Void> {
    private NoteDao noteDao;
    public InsertWithAsync(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    protected Void doInBackground(NoteTable... noteTables) {
        noteDao.addNote(noteTables);
        return null;
    }

}
