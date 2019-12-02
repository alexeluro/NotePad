package com.example.roomdatabaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabaseexample.BackgroundProcesses.InsertWithAsync;
import com.example.roomdatabaseexample.Database.Entity.NoteTable;
import com.example.roomdatabaseexample.Database.NoteDatabase;
import com.example.roomdatabaseexample.Repositories.NoteRepository;
import com.example.roomdatabaseexample.utils.CurrentTime;

import java.util.concurrent.ExecutionException;

public class EditNoteActivity extends AppCompatActivity {

    private final String TAG = "EditNoteActivity:";
    EditText title, body;
    int position;
    String retrievedTitle, retrievedBody;
    NoteRepository noteRepository;
    NoteTable noteTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        title = findViewById(R.id.note_title_read);
        body = findViewById(R.id.note_body_read);

        noteRepository = new NoteRepository(this);

        position = getIntent().getExtras().getInt("NOTE_POSITION");
        retrievedTitle = getIntent().getExtras().getString("NOTE_TITLE");
        retrievedBody = getIntent().getExtras().getString("NOTE_BODY");

        setTitle(retrievedTitle);

//        noteTable = noteRepository.getOneNote(position);

        /////// This noteTable is still null hence, causing a NullPointerException
        String noteTitle = retrievedTitle;
        String noteBody = retrievedBody;

        title.setText(noteTitle);
        title.setEnabled(false);

        body.setText(noteBody);
        body.setEnabled(false);


    }


    private void saveChanges(){
        String newTopic = title.getText().toString().trim();
        String newBody = body.getText().toString().trim();
        NoteTable newNotes = new NoteTable(newTopic, newBody, CurrentTime.getCurrentTime());
        newNotes.setId(position);
        noteRepository.updateNote(newNotes);
        Log.i(TAG, "saveChanges: Saving Changes... \n..................................");
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if(!(title.getText().toString().isEmpty()) && !(body.getText().toString().isEmpty())){
            if(!(title.getText().toString().equals(retrievedTitle)) |
                    !(body.getText().toString().equals(retrievedBody))){
                saveChanges();

            }
        }else{
            Toast.makeText(this, "Can't have an empty note", Toast.LENGTH_SHORT).show();
        }
        super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final String READ_MODE = "READ";

        switch(item.getItemId()){
            case R.id.edit_option:
                title.setEnabled(true);
                body.setEnabled(true);
                Toast.makeText(getApplicationContext(), "EDIT MODE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.read_option:
                title.setEnabled(false);
                body.setEnabled(false);
                Toast.makeText(getApplicationContext(), "READ-ONLY MODE", Toast.LENGTH_SHORT).show();
                break;
            default:
                super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
