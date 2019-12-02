package com.example.roomdatabaseexample.BackgroundProcesses;

import android.os.AsyncTask;
import android.util.Log;

import com.example.roomdatabaseexample.Database.Entity.NoteTable;
import com.example.roomdatabaseexample.Database.NoteDao;
import com.example.roomdatabaseexample.Database.NoteDatabase;

public class DeleteWithAsync extends AsyncTask<NoteTable, Void, Void> {
    private final String TAG = "DeleteNoteWithAsync:";
    private int DATABASE_RESPONSE = 1;
    private NoteDao noteDao;
    private NoteTable note;

    public DeleteWithAsync(NoteDao noteDao, NoteTable note){
        this.noteDao = noteDao;
        this.note = note;
    }

    @Override
    protected Void doInBackground(NoteTable... noteTables) {
        noteDao.deleteNote(noteTables);
        Log.i(TAG, "doInBackground: Delete done");
//        DATABASE_RESPONSE = noteDao.deleteNote(position);
        return null;
    }

    public int databaseFeedback(){
        return DATABASE_RESPONSE;
    }
}
