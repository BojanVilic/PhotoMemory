package com.example.photomemory.utils;

import android.Manifest;

public class Constants {

    public static final int MULTIPLE_PERMISSIONS = 1;
    public static final int CAMERA_IMAGE_RESULT = 3;
    public static final String CAPTURE_IMAGE_FILE_PROVIDER = "com.example.photomemory";
    public static String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION};
}
