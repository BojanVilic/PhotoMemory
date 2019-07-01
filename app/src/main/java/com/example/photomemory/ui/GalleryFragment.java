package com.example.photomemory.ui;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.photomemory.R;
import com.example.photomemory.data.Photo;
import com.example.photomemory.viewmodels.PhotoViewModel;

import java.util.List;


public class GalleryFragment extends Fragment {

    private PhotoViewModel photoViewModel;

    private List<Photo> photoList;

    private Button mCreateButton;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        photoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);
        photoViewModel.getListOfPhotos().observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                for(Photo p : photos){
                    Log.d("SATARAS", p.toString());
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        mCreateButton = view.findViewById(R.id.buttonCreate);
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Photo photo = new Photo(0, "Canada", "Toronto");
                photoViewModel.createPhoto(photo);
            }
        });
        return view;
    }
}
