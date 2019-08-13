package com.example.photomemory.ui;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.photomemory.R;
import com.example.photomemory.adapters.RecyclerViewAdapter;
import com.example.photomemory.data.Photo;
import com.example.photomemory.viewmodels.MainViewModel;

import java.util.List;


public class GalleryFragment extends Fragment {

    private MainViewModel mainViewModel;

    private List<Photo> photoList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getListOfPhotos().observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                recyclerViewAdapter.setPhotos(photos);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        recyclerViewAdapter = new RecyclerViewAdapter();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        return view;

    }
}
