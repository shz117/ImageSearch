package com.yahoo.gridimagesearch.model;

import android.net.Uri;

import java.net.URI;

/**
 * Created by jeremyshi on 1/31/15.
 */
public class SearchUrlConfig {
    // final String searchUrl = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + query + "&rsz=8" + "&start=" + String.valueOf(cursor);

    public String q;
    public int cursor;
    public String imageSize;
    public String imageColor;
    public String imageType;

    public SearchUrlConfig() {
        q = "";
        cursor = 0;
        imageColor = "";
        imageSize = "";
        imageType = "";
    }

    public String getUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("ajax.googleapis.com")
                .appendPath("ajax")
                .appendPath("services")
                .appendPath("search")
                .appendPath("images")
                .appendQueryParameter("v", "1.0")
                .appendQueryParameter("q", q)
                .appendQueryParameter("imgsz", imageSize)
                .appendQueryParameter("imgtype", imageType)
                .appendQueryParameter("imgcolor", imageColor)
                .appendQueryParameter("rsz", "8")
                .appendQueryParameter("start", String.valueOf(cursor));
        String myUrl = builder.build().toString();
        cursor += 8;
        return myUrl;
    }

    enum ImageSize {
        ICON("icon"),
        SMALL("small"),
        MEDIUM("medium"),
        LARGE("large"),
        XLARGE("xlarge"),
        XXLARGE("xxlarge"),
        HUGE("huge")
        ;

        private final String value;

        ImageSize(String value) {
            this.value = value;
        }
    }

    enum ImageColor {
        BLACK("black"),
        BLUE("blue"),
        WHITE("white");

        private final String value;

        ImageColor(String value) {
            this.value = value;
        }
    }

    enum ImageType {
        FACE("face"),
        PHOTO("photo");

        private final String value;

        ImageType(String value) {
            this.value = value;
        }
    }
}
