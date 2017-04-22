package com.udacity.stockhawk.sync;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

import timber.log.Timber;

public class QuoteJobService extends JobService {
  public static String Action = "com.udacity.stockhawk.app.ACTION_UPDATE";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Timber.d("Intent handled");
        Intent nowIntent = new Intent(getApplicationContext(), QuoteIntentService.class);
        getApplicationContext().startService(nowIntent);
        Intent updateIntent = new Intent(Action);
        getApplicationContext().sendBroadcast(updateIntent);

        return true;
    }


    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


}
