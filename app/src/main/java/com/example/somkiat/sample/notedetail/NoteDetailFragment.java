package com.example.somkiat.sample.notedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.somkiat.sample.Injection;
import com.example.somkiat.sample.R;

public class NoteDetailFragment extends Fragment implements NoteDetailContract.View {

    public static final String ARGUMENT_NOTE_ID = "NOTE_ID";

    private NoteDetailContract.UserActionsListener mActionsListener;

    private TextView mDetailTitle;

    private TextView mDetailDescription;

    private ImageView mDetailImage;

    public static NoteDetailFragment newInstance(String noteId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_NOTE_ID, noteId);
        NoteDetailFragment fragment = new NoteDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActionsListener = new NoteDetailPresenter(Injection.provideNotesRepository(),
                this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        mDetailTitle = (TextView) root.findViewById(R.id.note_detail_title);
        mDetailDescription = (TextView) root.findViewById(R.id.note_detail_description);
        mDetailImage = (ImageView) root.findViewById(R.id.note_detail_image);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        String noteId = getArguments().getString(ARGUMENT_NOTE_ID);
        mActionsListener.openNote(noteId);
    }

    @Override
    public void setProgressIndicator(boolean active) {
        if (active) {
            mDetailTitle.setText("");
            mDetailDescription.setText(getString(R.string.loading));
        }
    }

    @Override
    public void hideDescription() {
        mDetailDescription.setVisibility(View.GONE);
    }

    @Override
    public void hideTitle() {
        mDetailTitle.setVisibility(View.GONE);
    }

    @Override
    public void showDescription(String description) {
        mDetailDescription.setVisibility(View.VISIBLE);
        mDetailDescription.setText(description);
    }

    @Override
    public void showTitle(String title) {
        mDetailTitle.setVisibility(View.VISIBLE);
        mDetailTitle.setText(title);
    }

    @Override
    public void showImage(String imageUrl) {
        mDetailImage.setVisibility(View.VISIBLE);

        // This app uses Glide for image loading
        Glide.with(this)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(mDetailImage) {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                    }
                });
    }

    @Override
    public void hideImage() {
        mDetailImage.setImageDrawable(null);
        mDetailImage.setVisibility(View.GONE);
    }

    @Override
    public void showMissingNote() {
        mDetailTitle.setText("");
        mDetailDescription.setText(getString(R.string.no_data));
    }
}