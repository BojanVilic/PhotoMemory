package com.example.photomemory.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.photomemory.R;
import com.example.photomemory.adapters.ViewPagerAdapter;
import com.example.photomemory.data.Photo;
import com.example.photomemory.viewmodels.PhotoViewModel;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Uri mUri;
    private static final int CAMERA_IMAGE_RESULT = 202;
    private static final int LOCATION_REQUEST = 201;
    private static final String CAPTURE_IMAGE_FILE_PROVIDER = "com.example.photomemory";
    private ViewPager viewPager;
    private PhotoViewModel photoViewModel;
    private String country;
    private String city;
    private String timeStamp;
    private double lat;
    private double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void userLocationInfo(){
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
        } else {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            lat = location.getLatitude();
            lng = location.getLongitude();

            country = photoViewModel.getCountry(lat, lng);
            city = photoViewModel.getCity(lat, lng);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.camera_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.camera_icon);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_IMAGE_RESULT);
                }
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_IMAGE_RESULT);
                }
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_IMAGE_RESULT);
                } else {
                    File file = null;
                    try {
                        file = createImageFile();
                        mUri = FileProvider.getUriForFile(MainActivity.this, CAPTURE_IMAGE_FILE_PROVIDER, file);

                        Log.d("uri", mUri.toString());
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        cameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                        startActivityForResult(cameraIntent, CAMERA_IMAGE_RESULT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_IMAGE_RESULT && resultCode == RESULT_OK) {
            if (mUri != null) {
                userLocationInfo();
                Photo photo = new Photo(timeStamp, country, city, mUri.toString(), lat, lng);
                photoViewModel.createPhoto(photo);
            }
        }
    }

    private File createImageFile() throws IOException {
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "photomemory_" + timeStamp + ".jpeg";
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "Photo Memory");
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }
        File image = new File(mediaStorageDir, imageFileName);
        return image;
    }
}
