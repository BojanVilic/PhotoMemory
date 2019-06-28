package com.example.photomemory.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Photo.class}, version = 1)
public abstract class PhotoDatabase extends RoomDatabase {

    public abstract PhotoDao photoDao();
}
