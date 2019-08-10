package com.example.photomemory.data;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class CustomMarker implements ClusterItem {

    public final String uri;
    private final LatLng mPosition;

    public CustomMarker(LatLng position, String uri) {
        this.uri = uri;
        mPosition = position;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSnippet() {
        return null;
    }
}
