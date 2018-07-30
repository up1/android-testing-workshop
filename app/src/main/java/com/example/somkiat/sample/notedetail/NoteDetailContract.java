package com.example.somkiat.sample.notedetail;

import android.support.annotation.Nullable;

public interface NoteDetailContract {

    interface View {

        void setProgressIndicator(boolean active);

        void showMissingNote();

        void hideTitle();

        void showTitle(String title);

        void showImage(String imageUrl);

        void hideImage();

        void hideDescription();

        void showDescription(String description);
    }

    interface UserActionsListener {

        void openNote(@Nullable String noteId);
    }
}
