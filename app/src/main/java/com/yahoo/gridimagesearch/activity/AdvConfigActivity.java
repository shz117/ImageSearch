package com.yahoo.gridimagesearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.yahoo.gridimagesearch.R;

public class AdvConfigActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private String size;
    private String type;
    private  String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_config);

        Spinner spSize = (Spinner) findViewById(R.id.spSize);
        Spinner spColor = (Spinner) findViewById(R.id.spColor);
        Spinner spType = (Spinner) findViewById(R.id.spType);
        ArrayAdapter<CharSequence> aSize = ArrayAdapter.createFromResource(this,
                R.array.imagesize_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> aColor = ArrayAdapter.createFromResource(this,
                R.array.imagecolor_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> aType = ArrayAdapter.createFromResource(this,
                R.array.imagetype_array, android.R.layout.simple_spinner_item);
        aSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSize.setAdapter(aSize);
        spColor.setAdapter(aColor);
        spType.setAdapter(aType);
        spSize.setOnItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adv_config, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spSize:
                size = (String) parent.getItemAtPosition(position);
                break;
            case R.id.spColor:
                color = (String) parent.getItemAtPosition(position);
                break;
            case R.id.spType:
                type = (String) parent.getItemAtPosition(position);
                break;
            default:

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onSubmit(View v) {
        Intent data = getIntent();
        data.putExtra("color", color);
        data.putExtra("size", size);
        data.putExtra("type", type);
        setResult(RESULT_OK, data);
        finish();
    }
}
