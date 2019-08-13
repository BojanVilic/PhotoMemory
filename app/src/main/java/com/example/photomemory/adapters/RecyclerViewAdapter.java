package com.example.photomemory.adapters;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photomemory.R;
import com.example.photomemory.data.Photo;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<Photo> photoList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_image_recyclerview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Photo currentPhoto = photoList.get(position);
        Uri uri = Uri.parse(currentPhoto.getUri());
        holder.inflatingImage.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public void setPhotos(List<Photo> photos) {
        photoList = photos;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView inflatingImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            inflatingImage = itemView.findViewById(R.id.image);
        }
    }
}