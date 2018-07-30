package com.example.somkiat.sample.data;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class InMemoryNotesRepository implements NoteRepository {

    private final NoteServiceApi mNotesServiceApi;

    @VisibleForTesting
    List<Note> mCachedNotes;

    public InMemoryNotesRepository(@NonNull NoteServiceApi notesServiceApi) {
        mNotesServiceApi = checkNotNull(notesServiceApi);
    }

    @Override
    public void getNotes(@NonNull final LoadNotesCallback callback) {
        checkNotNull(callback);
        if (mCachedNotes == null) {
            mNotesServiceApi.getAllNotes(new NoteServiceApi.NotesServiceCallback<List<Note>>() {
                @Override
                public void onLoaded(List<Note> notes) {
                    mCachedNotes = ImmutableList.copyOf(notes);
                    callback.onNotesLoaded(mCachedNotes);
                }
            });
        } else {
            callback.onNotesLoaded(mCachedNotes);
        }
    }

    @Override
    public void saveNote(@NonNull Note note) {
        checkNotNull(note);
        mNotesServiceApi.saveNote(note);
        refreshData();
    }

    @Override
    public void getNote(@NonNull final String noteId, @NonNull final GetNoteCallback callback) {
        checkNotNull(noteId);
        checkNotNull(callback);
        mNotesServiceApi.getNote(noteId, new NoteServiceApi.NotesServiceCallback<Note>() {
            @Override
            public void onLoaded(Note note) {
                callback.onNoteLoaded(note);
            }
        });
    }

    @Override
    public void refreshData() {
        mCachedNotes = null;
    }
}
