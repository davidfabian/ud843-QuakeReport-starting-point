package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */

public final class QueryUtils {

    //modify this according to API documentation for different results
    private static final String USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    //JSON_RESPONSE static to used through the class
    private static String JSON_RESPONSE = "";

    //dummy creator
    private QueryUtils() {
    }

    //casting the url to URL format
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    //returns the JSon response specified by USGS_URL
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    //helper method: builds JSon string from inputstream
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        JSON_RESPONSE = output.toString();
        return JSON_RESPONSE;
    }

    //returns the list of earthquakes defined by the USGS_URL
    public static List<Earthquake> extractEarthquakes() {
        //prepare url
        URL url = createUrl(USGS_URL);
        // get json data from USGS with makeHttpRequest
        try {
            JSON_RESPONSE = makeHttpRequest(url);
        } catch (IOException io) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", io);
        }
        // Create an empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();

        // Try to parse the JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {


            JSONObject quakeList = new JSONObject(JSON_RESPONSE);
            JSONArray features = quakeList.getJSONArray("features");
            for (int i = 0; i < features.length(); i++) {
                JSONObject currentQuake = features.getJSONObject(i);
                JSONObject properties = currentQuake.getJSONObject("properties");
                double mag = properties.getDouble("mag");
                Log.i("magnitude", "mag " + mag);
                String loc = properties.getString("place");
                Log.i("location", "loc " + loc);
                long time = properties.getLong("time");
                Log.i("time", "tim " + time);
                String web = properties.getString("url");
                Log.i("web", "www " + web);
                earthquakes.add(new Earthquake(mag, loc, time, web));

            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}