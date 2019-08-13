package com.example.photomemory.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.photomemory.R;
import com.example.photomemory.data.CustomMarker;
import com.example.photomemory.data.Photo;
import com.example.photomemory.utils.ClusterRender;
import com.example.photomemory.viewmodels.MapViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MapFragment extends Fragment {

    private GoogleMap googleMap;
    MapView mMapView;
    private ClusterManager<CustomMarker> mClusterManager;
    private ClusterRender mClusterManagerRenderer;
    private ArrayList<CustomMarker> mClusterMarkers = new ArrayList<>();
    private MapViewModel mapViewModel;
    private List<Photo> photoList = new ArrayList<>();

    public MapFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mapViewModel = ViewModelProviders.of(this).get(MapViewModel.class);

        mapViewModel.getListOfPhotos().observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                for (Photo p : photos) {
                    Log.d("MILKA", p.getUri());
                }
                photoList.addAll(photos);
                addMapMarkers();
            }
        });

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
            }
        });
        return view;
    }

    private void addMapMarkers() {

        if (googleMap != null) {

            if (mClusterManager == null) {
                mClusterManager = new ClusterManager<CustomMarker>(getActivity().getApplicationContext(), googleMap);
            }
            if (mClusterManagerRenderer == null) {
                mClusterManagerRenderer = new ClusterRender(
                        getActivity(),
                        googleMap,
                        mClusterManager
                );
                mClusterManager.setRenderer(mClusterManagerRenderer);
            }
            for(Photo p : photoList){
                CustomMarker customMarker = new CustomMarker(position(p.getLatitude(), p.getLongitude()), p.getUri());
                mClusterManager.addItem(customMarker);
                mClusterMarkers.add(customMarker);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(p.getLatitude(), p.getLongitude()), 8));
            }
        }
        mClusterManager.cluster();
    }

    private LatLng position(Double lat, Double lng) {
        return new LatLng(lat, lng);
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}
