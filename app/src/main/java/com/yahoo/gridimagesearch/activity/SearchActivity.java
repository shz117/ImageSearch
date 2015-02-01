package com.yahoo.gridimagesearch.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.gridimagesearch.R;
import com.yahoo.gridimagesearch.adapter.ImageResultsAdapter;
import com.yahoo.gridimagesearch.lib.EndlessScrollListener;
import com.yahoo.gridimagesearch.model.SearchUrlConfig;
import com.yahoo.gridimagesearch.model.ImageResult;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SearchActivity extends Activity {

    private EditText etQuery;
    private GridView gvResutls;
    private InputMethodManager imm;
    private AsyncHttpClient client;
    private ArrayList<ImageResult> imageResults;
    private ImageResultsAdapter aImageResults;
    private SearchUrlConfig cfg = new SearchUrlConfig();

    public int ADV_CFG_REQ = 20;

    private void setViews() {
        gvResutls = (GridView) findViewById(R.id.gvResults);
        etQuery = (EditText) findViewById(R.id.etQuery);

        gvResutls.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                performSearch(true);
            }
        });

        etQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(false);
                    v.clearFocus();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setViews();
        imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        client = new AsyncHttpClient();
        imageResults = new ArrayList<>();
        aImageResults = new ImageResultsAdapter(this, imageResults);
        gvResutls.setAdapter(aImageResults);
        gvResutls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SearchActivity.this, ImageDisplayActivity.class);
                ImageResult result = imageResults.get(position);
                i.putExtra("result", result);
                startActivity(i);
            }
        });
    }



    public void performSearch(final boolean loadmore) {
        Log.i("INFO", "performing search!");
        String query = etQuery.getText().toString();
        cfg.q = query;
        imm.hideSoftInputFromWindow(etQuery.getWindowToken(), 0);
        String searchUrl = cfg.getUrl();
        client.get(searchUrl, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray imageResultsJSON = null;
                try {
                    imageResultsJSON = response.getJSONObject("responseData").getJSONArray("results");
                    if (!loadmore) {
                        imageResults.clear();
                        cfg.cursor = 0;
                    }
                    aImageResults.addAll(ImageResult.fromJSONArray(imageResultsJSON));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onAdvBtnClick(View view) {
        Intent toAdvSearch = new Intent(SearchActivity.this, AdvConfigActivity.class);
        startActivityForResult(toAdvSearch, ADV_CFG_REQ);
    }

    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        if (resCode == RESULT_OK && reqCode == ADV_CFG_REQ) {
            cfg.cursor = 0;
            cfg.imageColor = data.getStringExtra("color");
            cfg.imageSize = data.getStringExtra("size");
            cfg.imageType = data.getStringExtra("type");
        }
        performSearch(false);
    }
}
