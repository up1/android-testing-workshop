package com.example.somkiat.sample.addnote;

import android.support.annotation.NonNull;

import com.example.somkiat.sample.data.Note;
import com.example.somkiat.sample.data.NoteRepository;
import com.example.somkiat.sample.image.ImageStorage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

public class AddNotePresenter implements AddNoteContract.UserActionsListener {

    @NonNull
    private final NoteRepository mNotesRepository;
    @NonNull
    private final AddNoteContract.View mAddNoteView;
    @NonNull
    private final ImageStorage mImageFile;

    public AddNotePresenter(@NonNull NoteRepository notesRepository,
                            @NonNull AddNoteContract.View addNoteView,
                            @NonNull ImageStorage imageFile) {
        mNotesRepository = checkNotNull(notesRepository);
        mAddNoteView = checkNotNull(addNoteView);
        mImageFile = imageFile;
    }

    @Override
    public void saveNote(String title, String description) {
        String imageUrl = null;
        if (mImageFile.exists()) {
            imageUrl = mImageFile.getPath();
        }
        Note newNote = new Note(title, description, imageUrl);
        if (newNote.isEmpty()) {
            mAddNoteView.showEmptyNoteError();
        } else {
            mNotesRepository.saveNote(newNote);
            mAddNoteView.showNotesList();
        }
    }

    @Override
    public void takePicture() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        mImageFile.create(imageFileName, ".jpg");
        mAddNoteView.openCamera(mImageFile.getPath());
    }

    @Override
    public void imageAvailable() {
        if (mImageFile.exists()) {
            mAddNoteView.showImagePreview(mImageFile.getPath());
        } else {
            imageCaptureFailed();
        }
    }

    @Override
    public void imageCaptureFailed() {
        captureFailed();
    }

    private void captureFailed() {
        mImageFile.delete();
        mAddNoteView.showImageError();
    }

}
