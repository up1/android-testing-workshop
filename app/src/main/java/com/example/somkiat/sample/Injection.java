package com.example.somkiat.sample;

import com.example.somkiat.sample.data.InMemoryNotesRepository;
import com.example.somkiat.sample.data.MockNoteServiceApi;
import com.example.somkiat.sample.data.NoteRepository;
import com.example.somkiat.sample.image.ImageFile;
import com.example.somkiat.sample.image.ImageStorage;

public class Injection {
    public static ImageStorage provideImageFile() {
        return new ImageFile();
    }

    public static NoteRepository provideNotesRepository() {
        return new InMemoryNotesRepository(new MockNoteServiceApi());
    }
}
