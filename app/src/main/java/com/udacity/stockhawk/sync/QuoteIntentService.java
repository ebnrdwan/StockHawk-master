package com.udacity.stockhawk.sync;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;

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
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
//        int[] appwidegtsids = appWidgetManager.getAppWidgetIds(new ComponentName(this, WidgetClass.class));
//        Context context = getApplicationContext();
//        for (int appwidegtsid : appwidegtsids) {
//
//            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
//            Intent theintent = new Intent(context, MainActivity.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, theintent, 0);
//
//            remoteViews.setOnClickPendingIntent(R.id.widgetList, pendingIntent);
////            Intent collectionintent = new Intent(context, StockRemoteServices.class);
////            collectionintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appwidegtsid);
////            remoteViews.setRemoteAdapter(
////                    R.id.widgetList,
////                    collectionintent);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//                setRemoteAdapter(context, remoteViews);
//            } else {
//                setRemoteAdapterV11(context, remoteViews);
//            }
//            appWidgetManager.updateAppWidget(appwidegtsid, remoteViews);
//
//
//        }
//    }
//
//
//    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//    private void setRemoteDescription(RemoteViews views, String d) {
//
//    }
//    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
//        views.setRemoteAdapter(R.id.widgetList,
//                new Intent(context, StockRemoteServices.class));
//    }
//
//    /**
//     * Sets the remote adapter used to fill in the list items
//     *
//     * @param views RemoteViews to set the RemoteAdapter
//     */
//    @SuppressWarnings("deprecation")
//    private static void setRemoteAdapterV11(Context context, @NonNull final RemoteViews views) {
//        views.setRemoteAdapter( R.id.widgetList,
//                new Intent(context, StockRemoteServices.class));
//    }


    }
}
