package com.example.photomemory.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PhotoDao {

    @Insert
    void createPhoto(Photo photo);

    @Delete
    void deletePhoto(Photo photo);

    @Query("SELECT * FROM Photo")
    LiveData<List<Photo>> getPhotos();

    @Query("SELECT * FROM Photo WHERE country LIKE :country")
    LiveData<List<Photo>> getPhotosByCountry(String country);

    @Query("SELECT * FROM Photo WHERE country LIKE :city")
    LiveData<List<Photo>> getPhotosByCity(String city);
}
