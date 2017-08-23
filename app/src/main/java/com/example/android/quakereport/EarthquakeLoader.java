package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by d on 8/21/2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader {
    private String LOG_TAG = EarthquakeLoader.class.getName();

    public EarthquakeLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        List<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
        return earthquakes;
    }

}
