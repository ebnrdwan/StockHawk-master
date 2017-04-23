package com.udacity.stockhawk.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;
import com.udacity.stockhawk.data.PrefUtils;
import com.udacity.stockhawk.model.stockModel;
import com.udacity.stockhawk.ui.DetailStock;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Abdulrhman on 22/04/2017.
 */

public class WidgetProvider implements RemoteViewsService.RemoteViewsFactory {
    Context context;
    Cursor cursor;

    List<stockModel> stockModelList = new ArrayList<>();


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

        DecimalFormat dollarFormatWithPlus;
        DecimalFormat dollarFormat;
        DecimalFormat percentageFormat;
        stockModel model = stockModelList.get(i);

        dollarFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        dollarFormatWithPlus = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        dollarFormatWithPlus.setPositivePrefix("+$");
        percentageFormat = (DecimalFormat) NumberFormat.getPercentInstance(Locale.getDefault());
        percentageFormat.setMaximumFractionDigits(2);
        percentageFormat.setMinimumFractionDigits(2);
        percentageFormat.setPositivePrefix("+");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.list_item_quote);
        float rawAbsoluteChange = Float.valueOf(model.getChange());
        float percentageChange = Float.valueOf(model.getChangePercent());

        if (rawAbsoluteChange > 0) {
            views.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_green);
        } else {
            views.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_red);
        }
        String change = dollarFormatWithPlus.format(rawAbsoluteChange);
        String percentage = percentageFormat.format(percentageChange / 100);
        String symbol = model.getSymbol();
        String price = dollarFormat.format(model.getPrice());

        if (PrefUtils.getDisplayMode(context)
                .equals(context.getString(R.string.pref_display_mode_absolute_key))) {
            views.setTextViewText(R.id.change,change);
        } else {
            views.setTextViewText(R.id.change,percentage);
        }

        views.setTextViewText(R.id.symbol,symbol);
        views.setTextViewText(R.id.price, price);
        Intent intent = new Intent(context, DetailStock.class);
        intent.putExtra("SYMBOL_CODE", model.getSymbol());
        Uri mrui = Contract.Quote.makeUriForStock(model.getSymbol());
        Log.d("TATA", model.getSymbol() + "\n " + mrui.toString()+"\n"+change +"\n"+model.getChangePercent()+"\n"+model.getSymbol()+"\n"+model.getPrice());

        intent.setData(mrui);
        views.setOnClickFillInIntent(R.id.wholeItemView, intent);
//
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


        stockModelList.clear();
        Uri uri = Contract.Quote.URI;
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            return null;
        }

        if (!cursor.moveToFirst()) cursor.close();

        int count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            String symbol = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_SYMBOL));

            float price = cursor.getFloat(cursor.getColumnIndex(Contract.Quote.COLUMN_PRICE));
            float AbsoluteChange = cursor.getFloat(cursor.getColumnIndex(Contract.Quote.COLUMN_ABSOLUTE_CHANGE));
            float percentageChange = cursor.getFloat(cursor.getColumnIndex(Contract.Quote.COLUMN_PERCENTAGE_CHANGE));
            stockModelList.add(new stockModel(symbol, price, String.valueOf(AbsoluteChange), String.valueOf(percentageChange)));
        }


        return stockModelList;
    }


}
