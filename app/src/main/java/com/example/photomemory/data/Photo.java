package com.example.photomemory.data;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;

@Entity
public class Photo {

    @PrimaryKey
    @NonNull
    private String timestampName;
    private String country;
    private String city;
    private String uri;
    private double latitude;
    private double longitude;

    public Photo(String timestampName, String country, String city, String uri, double latitude, double longitude) {
        this.timestampName = timestampName;
        this.country = country;
        this.city = city;
        this.uri = uri;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTimestampName() {
        return timestampName;
    }

    public void setTimestampName(String timestampName) {
        this.timestampName = timestampName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "timestampName='" + timestampName + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", uri='" + uri + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
