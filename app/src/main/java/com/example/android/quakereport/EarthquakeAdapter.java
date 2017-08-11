package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import java.text.DecimalFormat;
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
        //get the current earthquake item
        Earthquake currentEarthquake = getItem(position);

        //fetch magnitude textview
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        //fetches and formats the magnitude to two digits
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(currentEarthquake.getmMag());
        //sets magnitude textview to formatted magnitude
        magnitudeTextView.setText(output);


        int magnitude1Color = ContextCompat.getColor(getContext(), getMagnitudeColor(currentEarthquake.getmMag()));

        GradientDrawable gradientBackground = (GradientDrawable) magnitudeTextView.getBackground();
        gradientBackground.setColor(magnitude1Color);


        //fetch location info
        String currentString = currentEarthquake.getmLocation();
        //split location info at "," creates a string array
        String[] separated = currentString.split(" of ");
        //if no "," in the location info, sets the first textview to placeholder text, and location to second.
        if (separated.length == 1) {

            TextView apprLocationTextView = (TextView) listItemView.findViewById(R.id.locationFirst);
            apprLocationTextView.setText(R.string.noloc);

            TextView readableLocationTextView = (TextView) listItemView.findViewById(R.id.locationSecond);
            readableLocationTextView.setText(separated[0]);
        }
        //if the location is separated with a "," sets the two textviews accordingly.
        else {
            TextView apprLocationTextView = (TextView) listItemView.findViewById(R.id.locationFirst);
            apprLocationTextView.setText(separated[0] + " of");

            TextView readableLocationTextView = (TextView) listItemView.findViewById(R.id.locationSecond);
            readableLocationTextView.setText(separated[1].trim());
        }

        //fetch time information and sets it to corresponding textview
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_text);
        timeTextView.setText(currentEarthquake.getmTime());

        //fetch date information and sets it to corresponding textview
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_text);
        dateTextView.setText(currentEarthquake.getmDate());

        return listItemView;
    }

    /**
     * magnitude-color matcher
     * @param mag a Double for magnitude
     * @return
     */
    private int getMagnitudeColor(Double mag) {
        int backgroundColor;
        switch (mag.intValue()){
            case 0:
            case 1:
                backgroundColor = R.color.magnitude1;
                break;
            case 2:
                backgroundColor = R.color.magnitude2;
                break;
            case 3:
                backgroundColor = R.color.magnitude3;
                break;
            case 4:
                backgroundColor = R.color.magnitude4;
                break;
            case 5:
                backgroundColor = R.color.magnitude5;
                break;
            case 6:
                backgroundColor = R.color.magnitude6;
                break;
            case 7:
                backgroundColor = R.color.magnitude7;
                break;
            case 8:
                backgroundColor = R.color.magnitude8;
                break;
            case 9:
                backgroundColor = R.color.magnitude9;
                break;
            case 10:
                backgroundColor = R.color.magnitude10plus;
                break;
            default:
                backgroundColor = R.color.magnitude10plus;

        }

        return backgroundColor;
    }

}