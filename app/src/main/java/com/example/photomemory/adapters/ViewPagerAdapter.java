package com.example.photomemory.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.photomemory.ui.GalleryFragment;
import com.example.photomemory.ui.MapFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MapFragment();
            case 1:
                return new GalleryFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
