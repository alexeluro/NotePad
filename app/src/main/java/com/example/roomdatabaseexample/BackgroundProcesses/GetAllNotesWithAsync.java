package com.example.roomdatabaseexample.BackgroundProcesses;

import android.os.AsyncTask;

import com.example.roomdatabaseexample.Database.Entity.NoteTable;
import com.example.roomdatabaseexample.Database.NoteDao;
import com.example.roomdatabaseexample.Repositories.NoteRepository;

import java.util.ArrayList;
import java.util.List;

public class GetAllNotesWithAsync extends AsyncTask<Void, Void, Void> {

    private NoteDao noteDao;
    private List<NoteTable> noteTableList = new ArrayList<>();

    public GetAllNotesWithAsync(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

}
