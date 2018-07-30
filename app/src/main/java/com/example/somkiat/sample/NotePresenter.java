package com.example.somkiat.sample;

import android.support.annotation.NonNull;

import com.example.somkiat.sample.data.Note;
import com.example.somkiat.sample.data.NoteRepository;
import com.google.common.base.Preconditions;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class NotePresenter implements NoteContract.UserActionsListener {

    private final NoteRepository mNotesRepository;
    private final NoteContract.View mNotesView;

    public NotePresenter(
            @NonNull NoteRepository notesRepository, @NonNull NoteContract.View notesView) {
        mNotesRepository = checkNotNull(notesRepository, "notesRepository cannot be null");
        mNotesView = checkNotNull(notesView, "notesView cannot be null!");
    }

    @Override
    public void loadNotes(boolean forceUpdate) {
        mNotesView.setProgressIndicator(true);
        if (forceUpdate) {
            mNotesRepository.refreshData();
        }

        mNotesRepository.getNotes(new NoteRepository.LoadNotesCallback() {
            @Override
            public void onNotesLoaded(List<Note> notes) {
                mNotesView.setProgressIndicator(false);
                mNotesView.showNotes(notes);
            }
        });
    }

    @Override
    public void addNewNote() {
        mNotesView.showAddNote();
    }

    @Override
    public void openNoteDetails(@NonNull Note requestedNote) {
        checkNotNull(requestedNote, "requestedNote cannot be null!");
        mNotesView.showNoteDetailUi(requestedNote.getId());
    }

}