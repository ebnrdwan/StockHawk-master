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
import com.udacity.stockhawk.ui.DetailStock;
import com.udacity.stockhawk.ui.MainActivity;

/**
 * Created by Abdulrhman on 21/04/2017.
 */

public class WidgetClass extends AppWidgetProvider {
    public static String HEADER_ACTION = "MAIN_ACTION";
    public static String ITEM_ACTION = "ITEM_ACTION";


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
//        if (QuoteJobService.Action.equals(intent.getAction())) {
//            context.startService(new Intent(context, QuoteIntentService.class));
//        }
        if (intent.getAction().equals(HEADER_ACTION)) {
            Intent intent1 = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
        else if (intent.getAction().equals(ITEM_ACTION)){
            Intent intent1 = new Intent(context, DetailStock.class);
            context.startActivity(intent);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        context.startService(new Intent(context, QuoteIntentService.class));


        for (int appwidegtsid : appWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            Intent Headerintent = new Intent(context, MainActivity.class);
            Headerintent.setAction(HEADER_ACTION);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, Headerintent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.headerWidgt, pendingIntent);
            Intent ListIntent = new Intent(context, DetailStock.class);
            ListIntent.setAction(ITEM_ACTION);
            remoteViews.setPendingIntentTemplate(R.id.widgetList, pendingIntent);
            remoteViews.setOnClickFillInIntent(R.id.widgetList, ListIntent);
            Intent collectionintent = new Intent(context, MainActivity.class);
            collectionintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appwidegtsid);
            remoteViews.setRemoteAdapter(
                    R.id.widgetList,
                    collectionintent);

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




