package com.example.roomdatabaseexample.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabaseexample.Database.Entity.NoteTable;
import com.example.roomdatabaseexample.EditNoteActivity;
import com.example.roomdatabaseexample.R;
import com.example.roomdatabaseexample.utils.CurrentTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    Context context;
    List<NoteTable> NoteList = new ArrayList<>();
    private final String TAG = "NotesAdapter:";

    public NotesAdapter(List<NoteTable> noteList) {
//        this.context = context;
        NoteList = noteList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewer = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_layout, parent, false);


        return new NoteViewHolder(viewer);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.title.setText(NoteList.get(position).getTitle());
        holder.body.setText(NoteList.get(position).getBody());
        try{
            String month = NoteList.get(position).getTimestamp().substring(3, 5);
            month = CurrentTime.getMonthAbbreviation(month);
            String day = NoteList.get(position).getTimestamp().substring(0, 2);
            String year = NoteList.get(position).getTimestamp().substring(6);
            String timeStamp = day + " " + month + " " + year;
            holder.time.setText(timeStamp);
        }catch(NullPointerException e){
            Log.i(TAG, "onBindViewHolder: NullException caught while setting time");
        }

    }

    @Override
    public int getItemCount() {
        return NoteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView title, body, time;
        CardView noteLayout;

        public NoteViewHolder(@NonNull final View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.note_topic);
            body = itemView.findViewById(R.id.note_body);
            time = itemView.findViewById(R.id.data_time);
            noteLayout = itemView.findViewById(R.id.note_layout);



            noteLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), EditNoteActivity.class);
                    intent.putExtra("NOTE_POSITION", NoteList.get(getAdapterPosition()).getId());
                    intent.putExtra("NOTE_TITLE", NoteList.get(getAdapterPosition()).getTitle());
                    intent.putExtra("NOTE_BODY", NoteList.get(getAdapterPosition()).getBody());
                    Toast.makeText(itemView.getContext(), "loading note...", Toast.LENGTH_SHORT).show();
                    itemView.getContext().startActivity(intent);
                }
            });
        }


    }
}
