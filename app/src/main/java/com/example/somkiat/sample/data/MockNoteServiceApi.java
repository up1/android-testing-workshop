package com.example.somkiat.sample.data;

import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockNoteServiceApi implements NoteServiceApi {

    private static final int SERVICE_LATENCY_IN_MILLIS = 2000;
    private static final Map<String, Note> NOTES_SERVICE_DATA =
            NotesServiceApiEndpoint.loadPersistedNotes();

    @Override
    public void getAllNotes(final NotesServiceCallback callback) {
        // Simulate network by delaying the execution.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Note> notes = new ArrayList<>(NOTES_SERVICE_DATA.values());
                callback.onLoaded(notes);
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void getNote(final String noteId, final NotesServiceCallback callback) {
        //TODO: Add network latency here too.
        Note note = NOTES_SERVICE_DATA.get(noteId);
        callback.onLoaded(note);
    }

    @Override
    public void saveNote(Note note) {
        NOTES_SERVICE_DATA.put(note.getId(), note);
    }
}

final class NotesServiceApiEndpoint {

    static {
        DATA = new HashMap<>();
        addNote("Hello", "Try Unit testing", null);
        addNote("Espresso", "UI Testing for Android", null);
    }

    private final static Map<String, Note> DATA;

    private static void addNote(String title, String description, String imageUrl) {
        Note newNote = new Note(title, description, imageUrl);
        DATA.put(newNote.getId(), newNote);
    }

    public static Map<String, Note> loadPersistedNotes() {
        return DATA;
    }
}
