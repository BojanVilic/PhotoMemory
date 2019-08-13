package com.example.photomemory.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.photomemory.R;
import com.example.photomemory.data.CustomMarker;
import com.example.photomemory.data.Photo;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import java.util.List;

public class ClusterRender extends DefaultClusterRenderer<CustomMarker> {

    private final IconGenerator iconGenerator;
    private final ImageView imageView;
    private final int markerWidth;
    private final int markerHeight;
    private List<Photo> photoList;
    private Context context;

    public ClusterRender(Context context, GoogleMap map, ClusterManager<CustomMarker> clusterManager) {
        super(context, map, clusterManager);

        this.context = context;
        iconGenerator = new IconGenerator(context.getApplicationContext());
        imageView = new ImageView(context.getApplicationContext());
        markerWidth = (int) context.getResources().getDimension(R.dimen.custom_marker_image);
        markerHeight = (int) context.getResources().getDimension(R.dimen.custom_marker_image);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(markerWidth, markerHeight));
        iconGenerator.setContentView(imageView);
    }

    @Override
    protected void onBeforeClusterItemRendered(CustomMarker item, MarkerOptions markerOptions) {
        Uri uri = Uri.parse(item.uri);
        imageView.setImageURI(uri);
        Bitmap bitmap = iconGenerator.makeIcon();
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster<CustomMarker> cluster) {
        return false;
    }
}
