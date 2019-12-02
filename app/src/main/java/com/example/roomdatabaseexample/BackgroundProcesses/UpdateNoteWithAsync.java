package com.example.roomdatabaseexample.BackgroundProcesses;

import android.os.AsyncTask;
import android.util.Log;

import com.example.roomdatabaseexample.Database.Entity.NoteTable;
import com.example.roomdatabaseexample.Database.NoteDao;

public class UpdateNoteWithAsync extends AsyncTask<Void, Void, Void> {
    private int DATABASE_RESPONSE = 1;
    private NoteDao noteDao;
    private NoteTable note;
    private final String TAG = "UpdateNoteWithAsync:";

    public UpdateNoteWithAsync(NoteDao noteDao, NoteTable note){
        this.noteDao = noteDao;
        this.note = note;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int id = note.getId();
        DATABASE_RESPONSE = noteDao.updateNotes(note);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.i(TAG, "onPostExecute: DATABASE_RESPONSE = "+ DATABASE_RESPONSE);
    }
}
