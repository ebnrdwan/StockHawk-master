package com.udacity.stockhawk.widget;

import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.model.stockModel;
import com.udacity.stockhawk.ui.MainActivity;

import java.util.Collections;
import java.util.List;

/**
 * Created by Abdulrhman on 22/04/2017.
 */

public class StockRemoteServices extends RemoteViewsService {
    List<stockModel> stockModelList = Collections.EMPTY_LIST;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        return new WidgetProvider(this, intent);


    }
}
