package com.yahoo.gridimagesearch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yahoo.gridimagesearch.R;
import com.yahoo.gridimagesearch.model.ImageResult;

import java.util.List;

/**
 * Created by jeremyshi on 1/27/15.
 */
public class ImageResultsAdapter extends ArrayAdapter<ImageResult>{
    public ImageResultsAdapter(Context context, List<ImageResult> images) {
        super(context, R.layout.item_image_result, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageResult imageInfo = getItem(position);
//        float ratio = imageInfo.tbWidth / imageInfo.tbHeight;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);
        }
//        int cellHeight = parent.getLayoutParams().height / 3;
//        int cellWidth = Math.round(cellHeight * ratio);
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
//        ivImage.setMinimumWidth(cellWidth);
//        ivImage.setMinimumHeight(cellHeight);
        ivImage.setImageResource(0);
        Picasso.with(getContext()).load(imageInfo.thumbUrl).placeholder(R.drawable.placeholder).into(ivImage);
        return convertView;
    }
}
