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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;

public class NewNote extends AppCompatActivity {

    EditText newTitle, newBody;
    BottomNavigationView bottomNav;
    String noteCategory;
    private static final String UNCATEGORIZED = "uncategorized";
    private static final String WORK = "work";
    private static final String FAMILY = "family_affair";
    private static final String STUDY = "study";
    private static final String PERSONAL = "personal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        setTitle("New Note");

        newTitle = findViewById(R.id.note_title_edit);
        newBody = findViewById(R.id.note_body_edit);
        bottomNav = findViewById(R.id.note_navigation);


        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_uncategorized:
                        noteCategory = UNCATEGORIZED;
                        Toast.makeText(getApplicationContext(), "Uncategorized", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_work:
                        noteCategory = WORK;
                        Toast.makeText(getApplicationContext(), "Work", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_family_affair:
                        noteCategory = FAMILY;
                        Toast.makeText(getApplicationContext(), "Family", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_study:
                        noteCategory = STUDY;
                        Toast.makeText(getApplicationContext(), "Study", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_personal:
                        noteCategory = PERSONAL;
                        Toast.makeText(getApplicationContext(), "Personal", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
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

            if(noteCategory == null){
                noteCategory = UNCATEGORIZED;
                Toast.makeText(getApplicationContext(), "Uncategorized", Toast.LENGTH_SHORT).show();
            }

            if (!(title.isEmpty()) && !(body.isEmpty())) {
                NoteTable note = new NoteTable(title, body, CurrentTime.getCurrentTime(), noteCategory);
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
