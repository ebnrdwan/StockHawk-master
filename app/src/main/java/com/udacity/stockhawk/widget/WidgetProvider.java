package com.udacity.stockhawk.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;
import com.udacity.stockhawk.model.stockModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Abdulrhman on 22/04/2017.
 */

public class WidgetProvider implements RemoteViewsService.RemoteViewsFactory {
    Context context;
    List<stockModel> stockModelList = Collections.EMPTY_LIST;

    public WidgetProvider(Context context, Intent intent) {
        this.context = context;
    }

    @Override

    public void onCreate() {
        getData(context);
    }

    @Override
    public void onDataSetChanged() {

        getData(context);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return stockModelList.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {

       RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.list_item_quote);
        stockModel model = stockModelList.get(i);
        views.setTextViewText(R.id.symbol,model.getSymbol());
        views.setTextViewText(R.id.change,model.getChange());
        views.setTextViewText(R.id.price,String.valueOf(model.getPrice()));
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public List<stockModel> getData(Context context) {
        Uri uri = Contract.Quote.URI;
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            return null;
        }

        if (!cursor.moveToFirst()) cursor.close();

        String symbol = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_SYMBOL));
        String change = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_ABSOLUTE_CHANGE));
        float price = cursor.getFloat(cursor.getColumnIndex(Contract.Quote.COLUMN_PRICE));
        stockModelList.add(new stockModel(symbol, price, change));


        return stockModelList;
    }
}
