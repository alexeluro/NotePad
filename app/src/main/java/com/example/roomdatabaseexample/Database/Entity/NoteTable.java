package com.example.roomdatabaseexample.Database.Entity;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class NoteTable implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "body")
    private String body;

    @ColumnInfo(name = "timestamp")
    private String timestamp;

    public NoteTable(String title, String body, String timestamp){
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
    }

    @Ignore
    public NoteTable(){}


    protected NoteTable(Parcel in) {
        id = in.readInt();
        title = in.readString();
        body = in.readString();
        timestamp = in.readString();
    }

    public static final Creator<NoteTable> CREATOR = new Creator<NoteTable>() {
        @Override
        public NoteTable createFromParcel(Parcel in) {
            return new NoteTable(in);
        }

        @Override
        public NoteTable[] newArray(int size) {
            return new NoteTable[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "NoteTable{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(body);
        parcel.writeString(timestamp);
    }
}
