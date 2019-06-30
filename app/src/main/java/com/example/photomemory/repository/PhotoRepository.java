package com.example.photomemory.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.photomemory.data.Photo;
import com.example.photomemory.data.PhotoDao;
import com.example.photomemory.data.PhotoDatabase;

import java.util.List;

public class PhotoRepository {

    private static PhotoRepository instance;
    private PhotoDao photoDao;
    private LiveData<List<Photo>> allPhotos;

    public PhotoRepository (Application application){
        PhotoDatabase photoDatabase = PhotoDatabase.getInstance(application);
        photoDao = photoDatabase.photoDao();
        allPhotos = photoDao.getPhotos();
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
