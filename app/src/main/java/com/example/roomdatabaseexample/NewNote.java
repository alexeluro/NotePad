package com.example.roomdatabaseexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabaseexample.BackgroundProcesses.InsertWithAsync;
import com.example.roomdatabaseexample.Database.Entity.NoteTable;
import com.example.roomdatabaseexample.Database.NoteDao;
import com.example.roomdatabaseexample.Database.NoteDatabase;
import com.example.roomdatabaseexample.utils.CurrentTime;

import java.text.SimpleDateFormat;

public class NewNote extends AppCompatActivity {

    EditText newTitle, newBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        setTitle("New Note");

        newTitle = findViewById(R.id.note_title_edit);
        newBody = findViewById(R.id.note_body_edit);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.save_option){
            String title = newTitle.getText().toString().trim();
            String body = newBody.getText().toString().trim();
            if (!(title.isEmpty()) && !(body.isEmpty())) {
                NoteTable note = new NoteTable(title, body, CurrentTime.getCurrentTime());
                new InsertWithAsync(NoteDatabase.getInstance(getApplicationContext()).getNoteDao()).execute(note);
                Toast.makeText(getApplication(), "Saved!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(getApplication(), "Empty TextFields!", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
