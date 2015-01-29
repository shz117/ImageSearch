package com.yahoo.gridimagesearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yahoo.gridimagesearch.R;
import com.yahoo.gridimagesearch.model.ImageResult;


public class ImageDisplayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        Intent i = getIntent();
        Bundle data = i.getExtras();
        ImageResult result = (ImageResult) data.getParcelable("result");
        String ulr = result.fullUrl;
        int height = result.height;
        int width = result.width;
        String title = result.title;
        ImageView ivImageResult = (ImageView) findViewById(R.id.ivImageResult);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(Html.fromHtml(title));
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
