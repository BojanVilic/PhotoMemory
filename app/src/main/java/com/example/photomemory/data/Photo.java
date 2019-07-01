package com.example.photomemory.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Photo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String country;
    private String city;

    public Photo(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
