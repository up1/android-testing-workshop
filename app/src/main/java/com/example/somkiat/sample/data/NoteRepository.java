package com.example.somkiat.sample.data;

import android.support.annotation.NonNull;

import java.util.List;

public interface NoteRepository {
    interface LoadNotesCallback {

        void onNotesLoaded(List<Note> notes);
    }

    interface GetNoteCallback {

        void onNoteLoaded(Note note);
    }

    void getNotes(@NonNull LoadNotesCallback callback);

    void getNote(@NonNull String noteId, @NonNull GetNoteCallback callback);

    void saveNote(@NonNull Note note);

    void refreshData();
}
