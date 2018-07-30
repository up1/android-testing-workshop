package com.example.somkiat.sample.data;

import java.util.List;

public interface NoteServiceApi {
    interface NotesServiceCallback<T> {

        void onLoaded(T notes);
    }

    void getAllNotes(NotesServiceCallback<List<Note>> callback);

    void getNote(String noteId, NotesServiceCallback<Note> callback);

    void saveNote(Note note);

}
