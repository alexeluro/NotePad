package com.example.roomdatabaseexample.ViewModels;

import android.app.Application;

import com.example.roomdatabaseexample.Database.Entity.NoteTable;
import com.example.roomdatabaseexample.MainActivity;
import com.example.roomdatabaseexample.Repositories.NoteRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MainActivityViewModelTest {

    MainActivity app = new MainActivity();

    @Before
    public void setUp() throws Exception {
    }

//    @Test
    public void pullNotesFromRepo(){
        boolean isFull = true;
        NoteRepository noteRepository = new NoteRepository(app.getApplicationContext());
        List<NoteTable> noteList = noteRepository.getAllAsyncNotes();
        if(noteList.size() == 0){
            isFull = false;
        }
        assertArrayEquals(true, isFull);
    }

    private void assertArrayEquals(boolean b, boolean isFull) {
        assertEquals(b,isFull);
    }

    @After
    public void tearDown() throws Exception {
    }
}