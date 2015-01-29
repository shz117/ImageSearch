package com.yahoo.gridimagesearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jeremyshi on 1/27/15.
 */
public class ImageResult implements Parcelable {
    public String fullUrl;
    public String thumbUrl;
    public String title;
    public int width;
    public int height;
    public int tbWidth;
    public int tbHeight;

    public ImageResult (JSONObject json) {
        try {
            this.fullUrl = json.getString("url");
            this.thumbUrl = json.getString("tbUrl");
            this.title = json.getString("title");
            this.width = json.getInt("width");
            this.height = json.getInt("height");
            this.tbHeight = json.getInt("tbHeight");
            this.tbWidth = json.getInt("tbWidth");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ImageResult> fromJSONArray(JSONArray array) {
        ArrayList<ImageResult> res = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                res.add(new ImageResult(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    protected ImageResult(Parcel in) {
        fullUrl = in.readString();
        thumbUrl = in.readString();
        title = in.readString();
        width = in.readInt();
        height = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullUrl);
        dest.writeString(thumbUrl);
        dest.writeString(title);
        dest.writeInt(width);
        dest.writeInt(height);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ImageResult> CREATOR = new Parcelable.Creator<ImageResult>() {
        @Override
        public ImageResult createFromParcel(Parcel in) {
            return new ImageResult(in);
        }

        @Override
        public ImageResult[] newArray(int size) {
            return new ImageResult[size];
        }
    };
}