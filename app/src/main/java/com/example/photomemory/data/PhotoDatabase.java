package com.example.photomemory.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Photo.class}, version = 1)
public abstract class PhotoDatabase extends RoomDatabase {

    private static PhotoDatabase instance;

    public abstract PhotoDao photoDao();

    public static synchronized PhotoDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), PhotoDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return  instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private PhotoDao photoDao;

        private PopulateDbAsyncTask(PhotoDatabase db){
            photoDao = db.photoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            photoDao.createPhoto(new Photo(1, "Canada", "Toronto"));
            photoDao.createPhoto(new Photo(2, "Brazil", "Rio de Janeiro"));
            photoDao.createPhoto(new Photo(3, "Spain", "Madrid"));
            return null;
        }
    }
}
