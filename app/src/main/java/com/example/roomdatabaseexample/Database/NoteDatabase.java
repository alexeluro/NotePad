package com.example.roomdatabaseexample.Database;

import android.content.Context;

import com.example.roomdatabaseexample.Database.Entity.NoteTable;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NoteTable.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "notedatabase.db";

    public abstract NoteDao getNoteDao();

    private static NoteDatabase instance;

    public static NoteDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    NoteDatabase.class,
                    DATABASE_NAME).build();
        }
        return instance;
    }

}
