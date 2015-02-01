package com.yahoo.gridimagesearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yahoo.gridimagesearch.R;
import com.yahoo.gridimagesearch.model.ImageResult;

import java.io.File;


public class ImageDisplayActivity extends Activity {

    private String ulr;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        Intent i = getIntent();
        Bundle data = i.getExtras();
        ImageResult result = (ImageResult) data.getParcelable("result");
        ulr = result.fullUrl;
        int height = result.height;
        int width = result.width;
        title = result.title;
        ImageView ivImageResult = (ImageView) findViewById(R.id.ivImageResult);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(Html.fromHtml(title));
        float ratio = (float) height / (float) width;
        int viewWidth = ivImageResult.getMeasuredWidth();
        int viewHeight = Math.round(ratio * viewWidth);
        ivImageResult.setMinimumHeight(viewHeight);
        ivImageResult.setImageResource(0);
        Picasso.with(this).load(ulr).into(ivImageResult);
    }
    public void onShare(View v) {
        if (ulr != null) {
            // Construct a ShareIntent with link to image
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, title);
            shareIntent.putExtra(Intent.EXTRA_TEXT, ulr);

            startActivity(Intent.createChooser(shareIntent, "Share image!"));
        } else {
            // ...sharing failed, handle error
        }
    }
}
