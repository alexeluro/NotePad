package com.example.roomdatabaseexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.roomdatabaseexample.Adapters.NotesAdapter;
import com.example.roomdatabaseexample.Database.Entity.NoteTable;
import com.example.roomdatabaseexample.Database.NoteDatabase;
import com.example.roomdatabaseexample.Repositories.NoteRepository;
import com.example.roomdatabaseexample.ViewModels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainActivityViewModel mainActivityViewModel;
    NotesAdapter notesAdapter;
    List<NoteTable> noteList = new ArrayList<>();
    ItemTouchHelper.SimpleCallback touchHelper;
    RelativeLayout relativeLayout;
    AlertDialog.Builder customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("NotePad");

        ///// the Note Repository
        new NoteRepository(this);
        recyclerView = findViewById(R.id.recycler_view);

        relativeLayout = findViewById(R.id.cat_bg);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init(this);

        // Thank God this is finally working as expected
        mainActivityViewModel.getData().observe(this, new Observer<List<NoteTable>>() {
            @Override
            public void onChanged(List<NoteTable> noteTables) {
                if(noteList.size() > 0){
                    noteList.clear();
                }
                if(noteList != null){
                    noteList.addAll(noteTables);
                    if(noteList.size() == 0){
                        relativeLayout.setVisibility(View.VISIBLE);
                    }else{
                        relativeLayout.setVisibility(View.GONE);
                    }
                    notesAdapter.notifyDataSetChanged();
                }
            }
        });
        init();


    }

    private void init(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter = new NotesAdapter(noteList);
        recyclerView.setAdapter(notesAdapter);

        touchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(viewHolder.getAdapterPosition() >= 0){
                    mainActivityViewModel.requestToDeleteNote(noteList.get(viewHolder.getAdapterPosition()));
                    Toast.makeText(getApplicationContext(),
                            "Note Deleted",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "An error occurred",
                            Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getApplicationContext(),
                        "You swiped",
                        Toast.LENGTH_SHORT).show();
            }
        };

    new ItemTouchHelper(touchHelper).attachToRecyclerView(recyclerView);

    }

    public void newNote(View view){
        Intent intent = new Intent(view.getContext(), NewNote.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        notesAdapter.notifyDataSetChanged();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.home_options){
            AlertDialog.Builder customDialog = new AlertDialog.Builder(this);
            View customView = getLayoutInflater().inflate(R.layout.custom_dialog , null);
            customDialog.setView(customView);
            customDialog.create();
            customDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }
}

