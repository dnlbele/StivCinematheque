package com.dbele.stiv.model;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class Cinema {

    private String name;
    private String address;
    private String properties;
    private double lat;
    private double lng;
    private String picture;

    public static Cinema createCinema(JSONObject jsonObject){
        Cinema cinema = new Cinema();
        try {
            cinema.name = jsonObject.getString("name");
            cinema.address = jsonObject.getString("address");
            cinema.properties = jsonObject.getString("properties");
            cinema.lat = jsonObject.getDouble("lat");
            cinema.lng = jsonObject.getDouble("lng");
            cinema.picture = jsonObject.getString("picture");
        } catch (JSONException e) {
            Log.e(Cinema.class.getName(), "unable to create Cinema from JSON", e);
        }
        return cinema;
    }

    public String getPicture() {
        return picture;
    }

    public double getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getProperties() {
        return properties;
    }

    public double getLat() {
        return lat;
    }

}
