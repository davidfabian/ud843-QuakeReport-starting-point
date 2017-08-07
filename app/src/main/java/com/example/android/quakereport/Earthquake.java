package com.example.android.quakereport;

import java.text.SimpleDateFormat;

/**
 * Created by d on 8/7/2017.
 */

public class Earthquake {
    //declare variables
    private double magnitude;
    private String location;
    private long time;
//constructor for earthquake object.
    public Earthquake(double mag, String loc, long tim) {
        magnitude = mag;
        location = loc;
        time = tim;
    }

    /**
     * getter for magnitude
     * @return magnitude
     */
    public double getmMag() {
        return magnitude;
    }

    /**
     * getter for location
     * @return location
     */
    public String getmLocation() {
        return location;
    }

    /**
     * getter for time
     * @return formatted time data
     */
    public String getmTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, y");
        return sdf.format(time);
    }
}
