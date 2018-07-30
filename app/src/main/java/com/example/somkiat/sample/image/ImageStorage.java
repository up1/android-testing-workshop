package com.example.somkiat.sample.image;

import java.io.IOException;

public interface ImageStorage {
    void create(String name, String extension) throws IOException;

    boolean exists();

    void delete();

    String getPath();
}
