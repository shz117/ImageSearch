package com.yahoo.gridimagesearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yahoo.gridimagesearch.R;


public class ImageDisplayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        Intent i = getIntent();
        String ulr = getIntent().getStringExtra("url");
        int height = i.getIntExtra("height", 1);
        int width = i.getIntExtra("width", 1);
        ImageView ivImageResult = (ImageView) findViewById(R.id.ivImageResult);
        float ratio = height / width;
        DisplayMetrics displayMetrics;
        displayMetrics = this.getResources().getDisplayMetrics();
        int viewHeight = Math.round(ratio * displayMetrics.widthPixels);
        ivImageResult.setMinimumWidth(displayMetrics.widthPixels);
        ivImageResult.setMinimumHeight(viewHeight);
        ivImageResult.setImageResource(0);
        Picasso.with(this).load(ulr).into(ivImageResult);
    }
}
