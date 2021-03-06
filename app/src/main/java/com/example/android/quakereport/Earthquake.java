package com.example.android.quakereport;

import java.text.SimpleDateFormat;

/**
 * class to store individual earthquake events
 */

public class Earthquake {
    //declare variables
    private double magnitude;
    private String location;
    private long time;
    private String webSite;

    //constructor for earthquake object.
    public Earthquake(double mag, String loc, long tim, String web) {
        magnitude = mag;
        location = loc;
        time = tim;
        webSite = web;
    }

    /**
     * getter for magnitude
     *
     * @return magnitude
     */
    public double getmMag() {
        return magnitude;
    }

    /**
     * getter for location
     *
     * @return location
     */
    public String getmLocation() {
        return location;
    }

    /**
     * getter for time
     *
     * @return formatted time data
     */
    public String getmDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, y");
        return sdf.format(time);
    }

    /**
     * getter for date
     *
     * @return formatted date data
     */
    public String getmTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        return sdf.format(time);
    }

    /**
     * getter for USGS website url for individual earthquake event
     *
     * @return website url in string format
     */
    public String getmWebSite() {
        return webSite;
    }
}
