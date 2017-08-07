package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by d on 8/7/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude_text);
        magnitudeTextView.setText(Double.toString(currentEarthquake.getmMag()));

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location_text);
        locationTextView.setText(currentEarthquake.getmLocation());

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_text);
        timeTextView.setText(currentEarthquake.getmTime());
    return listItemView;
    }
}