package com.example.roomdatabaseexample.Database;

import com.example.roomdatabaseexample.Database.Entity.NoteTable;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDao {

    @Insert
    void addNote(NoteTable[] note);

    @Query("SELECT * FROM notes ORDER BY id DESC")
    LiveData<List<NoteTable>> getAllNotes();

    //// add a new query annotation to get specific notes to update the recyclerView
//    @Query("SELECT * FROM notes WHERE id = 1")

    @Query("SELECT * FROM notes WHERE id = :position")
    NoteTable getThisNote(int position);

    @Update
    int updateNotes(NoteTable note);

    @Delete
    void deleteNote(NoteTable[] note);


}
