package com.example.somkiat.sample.data;

import android.support.annotation.Nullable;

import java.util.UUID;

public class Note {

    private final String mId;
    @Nullable
    private final String mTitle;
    @Nullable
    private final String mDescription;
    @Nullable
    private final String mImageUrl;

    public Note(@Nullable String title, @Nullable String description) {
        this(title, description, null);
    }

    public Note(@Nullable String title, @Nullable String description, @Nullable String imageUrl) {
        mId = UUID.randomUUID().toString();
        mTitle = title;
        mDescription = description;
        mImageUrl = imageUrl;
    }

    public String getId() {
        return mId;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    @Nullable
    public String getImageUrl() {
        return mImageUrl;
    }

    public boolean isEmpty() {
        return (mTitle == null || "".equals(mTitle)) &&
                (mDescription == null || "".equals(mDescription));
    }

}
