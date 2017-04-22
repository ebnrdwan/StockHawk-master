package com.udacity.stockhawk.sync;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.ui.MainActivity;
import com.udacity.stockhawk.widget.RemoteServices;
import com.udacity.stockhawk.widget.WidgetClass;

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
        Context context = getApplicationContext();
        for (int appwidegtsid : appwidegtsids) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            Intent theintent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, theintent, 0);

            remoteViews.setOnClickPendingIntent(R.id.widgetList, pendingIntent);
            Intent collectionintent = new Intent(context, RemoteServices.class);
            collectionintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appwidegtsid);
            remoteViews.setRemoteAdapter(
                    R.id.widgetList,
                    collectionintent);
            appWidgetManager.updateAppWidget(appwidegtsid, remoteViews);


        }
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void setRemoteDescription(RemoteViews views, String d) {

    }


}
