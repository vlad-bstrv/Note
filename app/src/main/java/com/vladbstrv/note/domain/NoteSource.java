package com.vladbstrv.note.domain;

import java.util.List;

public interface NoteSource {
    Note getNote(int position);
    int size();
    void deleteNote(int position);
    void updateNote(String title, String description, int position);
    void addNote(Note note);
    void clearNote();


    List<Note> getList();
}
