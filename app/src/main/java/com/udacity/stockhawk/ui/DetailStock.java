package com.udacity.stockhawk.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DetailStock extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    LineChart lineChart;
    List<Float> objects = Collections.EMPTY_LIST;
    Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_stock);
        lineChart = (LineChart) findViewById(R.id.mylinechart);
        Intent intent = getIntent();
        String symbol = intent.getStringExtra("SYMBOL_CODE");
        mUri = intent.getData();
        getSupportLoaderManager().initLoader(1, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                mUri,
                Contract.Quote.QUOTE_COLUMNS.toArray(new String[]{}),
                null,
                null,
                Contract.Quote.COLUMN_SYMBOL);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        boolean cursorHasValidData = false;
        if (data != null && data.moveToFirst()) {
            /* We have valid data, continue on to bind the data to the UI */
            cursorHasValidData = true;
        }

        if (!cursorHasValidData) {
            /* No data to display, simply return and do nothing */
            return;
        }
        String history = data.getString(data.getColumnIndex(Contract.Quote.COLUMN_HISTORY));
        history.length();
        objects = new ArrayList<>();
        String[] splitArray = history.split("\n");

        for (int i = 0; i < splitArray.length; i++) {
            objects.add(Float.valueOf(String.valueOf(splitArray[i])));
        }
        objects.size();
        List<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < objects.size(); i++) {
            Float object = objects.get(i);
            entries.add(new Entry(i, object));
        }
        LineDataSet dataset = new LineDataSet(entries, getString(R.string.mydataSetLabe));
        LineData lineData = new LineData(dataset);
        lineChart.setData(lineData);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

