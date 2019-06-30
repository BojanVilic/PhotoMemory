package com.example.photomemory.viewmodels;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.photomemory.data.Photo;
import com.example.photomemory.repository.PhotoRepository;

import java.util.List;

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
}
