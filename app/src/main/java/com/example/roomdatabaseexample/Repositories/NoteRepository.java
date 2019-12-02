package com.example.roomdatabaseexample.Repositories;

import android.content.Context;
import android.os.Handler;

import com.example.roomdatabaseexample.BackgroundProcesses.DeleteWithAsync;
import com.example.roomdatabaseexample.BackgroundProcesses.GetAllNotesWithAsync;
import com.example.roomdatabaseexample.BackgroundProcesses.GetSingleNoteWithAsync;
import com.example.roomdatabaseexample.BackgroundProcesses.UpdateNoteWithAsync;
import com.example.roomdatabaseexample.Database.Entity.NoteTable;
import com.example.roomdatabaseexample.Database.NoteDao;
import com.example.roomdatabaseexample.Database.NoteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class NoteRepository {

    Context context;
    List<NoteTable> repoNoteList = new ArrayList<>();

    public NoteRepository(Context context){
        this.context = context;
//        GetAllNotesWithAsync getAllNotes = new GetAllNotesWithAsync(
//                NoteDatabase.getInstance(this.context).getNoteDao());
//        getAllNotes.execute();
    }

    // This should be called when the app is newly opened to get the
    // all the notes in the database.
    public List<NoteTable> getAllAsyncNotes(){
        return repoNoteList;
    }

    public LiveData<List<NoteTable>> getLiveNotes(){
        return NoteDatabase.getInstance(context).getNoteDao().getAllNotes();
    }

    ///// This will be called to retrieve just one note
    public List<NoteTable> getOneNote(int position){

    return null;
    }

    // This will be called for Updating the note
    public void updateNote(NoteTable note){
        new UpdateNoteWithAsync(NoteDatabase.getInstance(context).getNoteDao(), note).execute();
    }

    public void deleteNote(NoteTable note){
        DeleteWithAsync deleteWithAsync =
                new DeleteWithAsync(NoteDatabase.getInstance(context).getNoteDao(), note);
        deleteWithAsync.execute(note);
    }

}
