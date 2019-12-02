package com.example.roomdatabaseexample.ViewModels;

import android.content.Context;

import com.example.roomdatabaseexample.Database.Entity.NoteTable;
import com.example.roomdatabaseexample.Database.NoteDatabase;
import com.example.roomdatabaseexample.Repositories.NoteRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<NoteTable>> mutableNote = new MutableLiveData<>();
    @NonNull
    private Context context;
    private NoteRepository noteRepository;

    public void init(Context context){
        if(mutableNote == null){
            return;
        }
        this.context = context;
        noteRepository = new NoteRepository(context);
//        mutableNote.postValue(pullNotesFromRepo());
    }


    public LiveData<List<NoteTable>> getData(){
        return noteRepository.getLiveNotes();
    }

    private List<NoteTable> pullNotesFromRepo(){
        List<NoteTable> noteList = noteRepository.getAllAsyncNotes();
        return noteList;
    }

    public void requestToDeleteNote(NoteTable note){
        note.setId(note.getId());
        noteRepository.deleteNote(note);
    }


}
