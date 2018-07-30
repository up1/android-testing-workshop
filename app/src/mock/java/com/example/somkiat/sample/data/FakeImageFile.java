package com.example.somkiat.sample.data;

import com.example.somkiat.sample.image.ImageStorage;

import java.io.IOException;

public class FakeImageFile implements ImageStorage {
    @Override
    public void create(String name, String extension) throws IOException {
        // Do nothing
    }

    @Override
    public String getPath() {
        return "file:///android_asset/atsl-logo.png";
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public void delete() {
        // Do nothing
    }
}
