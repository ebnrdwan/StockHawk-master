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

        Timber.d("Intent handled");
        QuoteSyncJob.getQuotes(getApplicationContext());


    }




}
