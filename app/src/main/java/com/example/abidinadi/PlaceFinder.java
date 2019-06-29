package com.example.abidinadi;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

public class PlaceFinder extends AsyncTask<String, Void, String>{

    private String googlePlacesData;
    private String url;
    private ArrayList<Place> foundPlacesList = new ArrayList<>();

    @SuppressLint("StaticFieldLeak")
    private activity_find_place findPlaceActivity;

    PlaceFinder(activity_find_place findPlaceActivity) {
        this.findPlaceActivity = findPlaceActivity;
    }

    @Override
    protected String doInBackground(String... strings) {
        UrlDownloader urlDownloader = new UrlDownloader();
        url = strings[0];

        try {
            googlePlacesData = urlDownloader.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {
        DataParser dataParser = new DataParser();
        foundPlacesList = dataParser.parsePlaces(s);

        findPlaceActivity.setFoundPlaces(foundPlacesList);
        findPlaceActivity.setListValues();

        super.onPostExecute(s);
    }

}
