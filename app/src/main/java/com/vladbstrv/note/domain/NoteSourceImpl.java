package com.vladbstrv.note.domain;

import java.util.ArrayList;
import java.util.List;

public class NoteSourceImpl implements NoteSource {
    private List<Note> dataSource;

    public NoteSourceImpl() {
        this.dataSource = new ArrayList<>();
        init();
    }



    @Override
    public Note getNote(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public void deleteNote(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateNote(String title, String description, int position) {
        Note note = new Note(dataSource.get(position).getId(), title, description);
        dataSource.set(position, note);
    }

    @Override
    public void addNote(Note note) {
        dataSource.add(note);
    }

    @Override
    public void clearNote() {
        dataSource.clear();
    }

    @Override
    public List<Note> getList() {
        return dataSource;
    }

    private void init() {
        dataSource.add(new Note("1", "Title1", "Description1"));
        dataSource.add(new Note("1", "Title2", "Description2"));
        dataSource.add(new Note("1", "Title3", "Description3"));
        dataSource.add(new Note("1", "Title4", "Description4"));
        dataSource.add(new Note("1", "Title5", "Description5"));
        dataSource.add(new Note("1", "Title6", "Description6"));
        dataSource.add(new Note("1", "Title7", "Description7"));
        dataSource.add(new Note("1", "Title8", "Description8"));
        dataSource.add(new Note("1", "Title9", "Description9"));
        dataSource.add(new Note("1", "Title10", "Description10"));
    }
}
