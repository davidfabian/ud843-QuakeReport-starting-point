/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        earthquakes.add(new Earthquake(3.6, "teszt",            1502034228120L));
        earthquakes.add(new Earthquake(3.65, "teszt2",          1502034428120L));
        earthquakes.add(new Earthquake(13.6, "teszt3",          1502034828120L));
        earthquakes.add(new Earthquake(31.6, "teszt5",          1500852203000L));
        earthquakes.add(new Earthquake(3.46, "tesztdsfsf",      1202034228120L));
        earthquakes.add(new Earthquake(31.6, "teszt6454",       1522034228120L));
        earthquakes.add(new Earthquake(3.633, "lasttteszt",     1500852203000L));

        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, earthquakes);
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        earthquakeListView.setAdapter(earthquakeAdapter);
    }
}