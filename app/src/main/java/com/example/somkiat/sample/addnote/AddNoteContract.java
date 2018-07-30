package com.example.somkiat.sample.addnote;

import android.support.annotation.NonNull;

import java.io.IOException;

public interface AddNoteContract {

    interface View {

        void showEmptyNoteError();

        void showNotesList();

        void openCamera(String saveTo);

        void showImagePreview(@NonNull String uri);

        void showImageError();
    }

    interface UserActionsListener {

        void saveNote(String title, String description);

        void takePicture() throws IOException;

        void imageAvailable();

        void imageCaptureFailed();
    }
}
