package com.udacity.stockhawk.sync;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;
import com.udacity.stockhawk.ui.MainActivity;
import com.udacity.stockhawk.ui.WidgetClass;

import timber.log.Timber;


public class QuoteIntentService extends IntentService {

    String symbol;
    Uri muri;

    public QuoteIntentService() {
        super(QuoteIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        symbol="YAHOO";
//        Contract.Quote.makeUriForStock(symbol);
        Timber.d("Intent handled");

            QuoteSyncJob.getQuotes(getApplicationContext());


        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appwidegtsids = appWidgetManager.getAppWidgetIds(new ComponentName(this, WidgetClass.class));

        muri = Contract.Quote.URI;
        Cursor cursor = getContentResolver().query(muri, null, null, null, null);
        if (cursor == null) {
            return;
        }
        if (!cursor.moveToFirst()) {
            cursor.close();
            return;
        }
//


        Context context = getApplicationContext();
        String theSymbol = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_SYMBOL));
        float price = cursor.getFloat(cursor.getColumnIndex(Contract.Quote.COLUMN_PRICE));
        String change = cursor.getString(cursor.getColumnIndex(Contract.Quote.COLUMN_PERCENTAGE_CHANGE));



        for (int appwidegtsid : appwidegtsids) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            Intent theintent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, theintent, 0);

            remoteViews.setTextViewText(R.id.widgetsymbol, symbol);

            remoteViews.setTextViewText(R.id.widgetchange, change);
            remoteViews.setTextViewText(R.id.widgetprice, String.valueOf(price));
            remoteViews.setOnClickPendingIntent(R.id.widget, pendingIntent);
            appWidgetManager.updateAppWidget(appwidegtsid, remoteViews);
        }
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void setRemoteDescription(RemoteViews views, String d) {

    }


}
