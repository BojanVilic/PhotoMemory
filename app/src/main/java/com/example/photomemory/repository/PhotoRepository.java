package com.example.photomemory.repository;

import androidx.lifecycle.LiveData;

import com.example.photomemory.data.Photo;
import com.example.photomemory.data.PhotoDao;

import java.util.List;

public class PhotoRepository {

    private static PhotoRepository instance;
    private final PhotoDao photoDao;

    public PhotoRepository(PhotoDao photoDao){
        this.photoDao = photoDao;
    }

    public LiveData<List<Photo>> getListOfPhotos(){
        return photoDao.getPhotos();
    }

    public LiveData<List<Photo>> getListOfPhotosByCountry(String country){
        return photoDao.getPhotosByCountry(country);
    }

    public LiveData<List<Photo>> getListOfPhotosByCity(String city){
        return photoDao.getPhotosByCity(city);
    }

    public void createPhoto(Photo photo){
        photoDao.createPhoto(photo);
    }

    public void deletePhoto(Photo photo){
        photoDao.deletePhoto(photo);
    }
}
