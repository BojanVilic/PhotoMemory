package com.example.photomemory.viewmodels;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.photomemory.data.Photo;
import com.example.photomemory.repository.PhotoRepository;
import com.example.photomemory.ui.MainActivity;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class PhotoViewModel extends AndroidViewModel {

    public PhotoRepository photoRepository;
    private LiveData<List<Photo>> allPhotos;

    public PhotoViewModel(@NonNull Application application) {
        super(application);

        photoRepository = new PhotoRepository(application);
        allPhotos = photoRepository.getListOfPhotos();
    }

    public LiveData<List<Photo>> getListOfPhotos(){
        return allPhotos;
    }

    public void deletePhoto(Photo photo){
        DeletePhotoTask deletePhotoTask = new DeletePhotoTask();
        deletePhotoTask.execute(photo);
    }

    public void createPhoto(Photo photo){
        CreatePhotoTask createPhotoTask = new CreatePhotoTask();
        createPhotoTask.execute(photo);
    }

    private class DeletePhotoTask extends AsyncTask<Photo, Void, Void> {

        @Override
        protected Void doInBackground(Photo... photos) {
            photoRepository.deletePhoto(photos[0]);
            return null;
        }
    }

    private class CreatePhotoTask extends AsyncTask<Photo, Void, Void> {

        @Override
        protected Void doInBackground(Photo... photos) {
            photoRepository.createPhoto(photos[0]);
            return null;
        }
    }

    public String getCountry(double latitude, double longitude){
        String country = "";

        Geocoder geocoder = new Geocoder(getApplication().getApplicationContext(), Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                country = addresses.get(0).getCountryName();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return country;
    }

    public String getCity(double latitude, double longitude){
        String city = "";

        Geocoder geocoder = new Geocoder(getApplication().getApplicationContext(), Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                city = addresses.get(0).getLocality();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return city;
    }
}
