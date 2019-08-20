package com.example.gallerysample.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.gallerysample.R
import com.example.gallerysample.genericclasses.GlideConnector
import com.example.gallerysample.model.Album
import java.util.ArrayList

class GalleryAdapter(activity: Activity, images: ArrayList<Album>) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    lateinit var activity: Activity
    lateinit var images: ArrayList<Album>

    init {
        this.activity = activity
        this.images = images
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GalleryViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.gallery_list_child, viewGroup, false)
        return GalleryAdapter.GalleryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, i: Int) {
        holder.imageName.text = images[i].imageName
        holder.image.scaleType = ImageView.ScaleType.CENTER_CROP
        GlideConnector.getInstance().loadImageDirectlyWithoutThumbnail(activity, images[i].imagePath, holder.image)
    }

    override fun getItemCount(): Int {
        return images.size
    }


     class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView
        var imageName: TextView

        init {
            image = itemView.findViewById(R.id.image)
            imageName = itemView.findViewById(R.id.imageName)

        }
    }

}
