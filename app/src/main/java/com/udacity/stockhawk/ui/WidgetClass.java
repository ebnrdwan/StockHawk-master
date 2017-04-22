package com.udacity.stockhawk.ui;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import com.udacity.stockhawk.sync.QuoteIntentService;
import com.udacity.stockhawk.sync.QuoteJobService;

/**
 * Created by Abdulrhman on 21/04/2017.
 */

public class WidgetClass extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (QuoteJobService.Action.equals(intent.getAction())){
            context.startService(new Intent(context,QuoteIntentService.class));
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {


        context.startService(new Intent(context, QuoteIntentService.class));

    }



}
