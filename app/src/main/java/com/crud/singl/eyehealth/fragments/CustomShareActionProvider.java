package com.crud.singl.eyehealth.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;

import com.crud.singl.eyehealth.R;

/**
 * Created by singl on 3/19/2018.
 */

public class CustomShareActionProvider extends ShareActionProvider {
    private final Context context;

    public CustomShareActionProvider(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View onCreateActionView() {
        ActivityChooserView chooserView = (ActivityChooserView) super.onCreateActionView();

        Drawable icon = context.getResources().getDrawable(R.drawable.ic_share_black_24dp);

        chooserView.setExpandActivityOverflowButtonDrawable(icon);

        return chooserView;
    }
}
