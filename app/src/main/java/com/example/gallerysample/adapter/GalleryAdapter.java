package com.example.gallerysample.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.gallerysample.genericclasses.GlideConnector;
import com.example.gallerysample.R;
import com.example.gallerysample.model.Album;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private Activity activity;
    private ArrayList<Album> images;

    public GalleryAdapter(Activity activity, ArrayList<Album> images) {
        this.activity = activity;
        this.images = images;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gallery_list_child, viewGroup, false);
        return new GalleryAdapter.GalleryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int i) {
        holder.imageName.setText(images.get(i).getImageName());
        holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        GlideConnector.getInstance().loadImageDirectlyWithoutThumbnail(activity, images.get(i).getImagePath(), holder.image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    class GalleryViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView imageName;

        GalleryViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.imageName);

        }
    }

}
