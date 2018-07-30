package com.example.somkiat.sample;

import android.support.annotation.NonNull;

import com.example.somkiat.sample.data.Note;

import java.util.List;

public interface MainContract {
    interface View {

        void setProgressIndicator(boolean active);

        void showNotes(List<Note> notes);

        void showAddNote();

        void showNoteDetailUi(String noteId);
    }

    interface UserActionsListener {

        void loadNotes(boolean forceUpdate);

        void addNewNote();

        void openNoteDetails(@NonNull Note requestedNote);
    }
}
