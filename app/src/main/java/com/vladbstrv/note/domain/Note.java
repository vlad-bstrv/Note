package com.vladbstrv.note.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

    private String id;

    private String title;

    private String description;

    public Note(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    protected Note(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(description);
    }
}
