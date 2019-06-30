package com.example.photomemory.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.example.photomemory.R;
import com.example.photomemory.adapters.ViewPagerAdapter;
import com.example.photomemory.data.Photo;
import com.example.photomemory.viewmodels.PhotoViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PhotoViewModel photoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        photoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);
//        photoViewModel.getListOfPhotos().observe(this, new Observer<List<Photo>>() {
//            @Override
//            public void onChanged(List<Photo> photos) {
//                for(Photo p : photos){
//                    Log.d("RESULT", String.valueOf(p.getId()));
//                    Log.d("RESULT", p.getCountry());
//                    Log.d("RESULT", p.getCity());
//                }
//            }
//        });
    }
}
