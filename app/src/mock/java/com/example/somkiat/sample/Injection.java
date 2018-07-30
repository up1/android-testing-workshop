package com.example.somkiat.sample;

import com.example.somkiat.sample.data.FakeImageFile;
import com.example.somkiat.sample.data.FakeNoteServiceApi;
import com.example.somkiat.sample.data.InMemoryNotesRepository;
import com.example.somkiat.sample.data.NoteRepository;
import com.example.somkiat.sample.image.ImageStorage;

public class Injection {
    public static ImageStorage provideImageFile() {
        return new FakeImageFile();
    }

    public static NoteRepository provideNotesRepository() {
        return new InMemoryNotesRepository(new FakeNoteServiceApi());
    }
}


