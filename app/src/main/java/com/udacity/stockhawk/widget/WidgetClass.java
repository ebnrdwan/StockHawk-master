package com.udacity.stockhawk.widget;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.sync.QuoteIntentService;
import com.udacity.stockhawk.ui.MainActivity;

/**
 * Created by Abdulrhman on 21/04/2017.
 */

public class WidgetClass extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
//        if (QuoteJobService.Action.equals(intent.getAction())){
//            context.startService(new Intent(context,QuoteIntentService.class));
//        }


    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//
//





        for (int appwidegtsid : appWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            Intent theintent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, theintent, 0);

            remoteViews.setOnClickPendingIntent(R.id.widgetList, pendingIntent);
//            Intent collectionintent = new Intent(context, StockRemoteServices.class);
//            collectionintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appwidegtsid);
//            remoteViews.setRemoteAdapter(
//                    R.id.widgetList,
//                    collectionintent);
            context.startService(new Intent(context, QuoteIntentService.class));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                setRemoteAdapter(context, remoteViews);
            } else {
                setRemoteAdapterV11(context, remoteViews);
            }
            appWidgetManager.updateAppWidget(appwidegtsid, remoteViews);


        }

    }






    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void setRemoteDescription(RemoteViews views, String d) {

    }
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(R.id.widgetList,
                new Intent(context, StockRemoteServices.class));
    }

    /**
     * Sets the remote adapter used to fill in the list items
     *
     * @param views RemoteViews to set the RemoteAdapter
     */
    @SuppressWarnings("deprecation")
    private static void setRemoteAdapterV11(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(0, R.id.widgetList,
                new Intent(context, StockRemoteServices.class));
    }
    }




