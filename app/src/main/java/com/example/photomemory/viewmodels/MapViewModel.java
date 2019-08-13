package com.example.photomemory.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.photomemory.data.Photo;
import com.example.photomemory.repository.PhotoRepository;

import java.util.List;

public class MapViewModel extends AndroidViewModel {

    private PhotoRepository photoRepository;
    private LiveData<List<Photo>> allPhotos;

    public MapViewModel(@NonNull Application application) {
        super(application);

        photoRepository = new PhotoRepository(application);
        allPhotos = photoRepository.getListOfPhotos();
    }

    public LiveData<List<Photo>> getListOfPhotos(){
        return allPhotos;
    }
}
