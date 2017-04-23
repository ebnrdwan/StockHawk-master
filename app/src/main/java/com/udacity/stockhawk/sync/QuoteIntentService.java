package com.udacity.stockhawk.sync;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;

import com.udacity.stockhawk.R;

import timber.log.Timber;


public class QuoteIntentService extends IntentService {

    String symbol;
    Uri muri;

    public QuoteIntentService() {
        super(QuoteIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Timber.d(this.getString(R.string.intentcode));
        QuoteSyncJob.getQuotes(getApplicationContext());


    }




}
