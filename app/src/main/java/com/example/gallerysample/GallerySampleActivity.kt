package com.example.gallerysample

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.gallerysample.adapter.GalleryAdapter
import com.example.gallerysample.genericclasses.GlideConnector
import com.example.gallerysample.genericclasses.RecyclerItemClickListener
import com.example.gallerysample.model.Album
import java.util.ArrayList

class GallerySampleActivity : AppCompatActivity() {

    lateinit var gallery: RecyclerView
    private var images: ArrayList<Album>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_sample)

        gallery = findViewById(R.id.galleryRecycler)

        if (!checkSelfPermission()) {
            requestPermission()
        } else {
            // if permission granted read images from storage.
            //  source code for this function can be found below.

            loadAllImagesFromGallery()

        }

    }

    private fun loadAllImagesFromGallery() {
        images = getAllShownImagesPath(this)
        val gridLayoutManager = GridLayoutManager(applicationContext, 2)
        gallery.layoutManager = gridLayoutManager
        gallery.adapter = GalleryAdapter(this, images!!)
        setGalleryItemClickListener()
    }

    private fun setGalleryItemClickListener() {

        gallery.addOnItemTouchListener(
            RecyclerItemClickListener(
                this,
                gallery,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int, motionEvent: MotionEvent) {
                        openAlertDialog(images?.get(position)?.imageName,images?.get(position)?.imagePath)
                    }

                    override fun onLongItemClick(view: View, position: Int) {
                        openAlertDialog(images?.get(position)?.imageName,images?.get(position)?.imagePath)
                    }

                })
        )

    }

    private fun openAlertDialog(name: String?, imagePath: String?) {
        val inflater = layoutInflater
        val dialoglayoutView = inflater.inflate(R.layout.alert_dialog_layout, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialoglayoutView)
        val imageview = dialoglayoutView.findViewById<ImageView>(R.id.ViewImage)
        val imageName = dialoglayoutView.findViewById<TextView>(R.id.name)
        GlideConnector.getInstance().loadImageDirectlyWithoutThumbnail(this, imagePath, imageview)
        imageName.setText(name)
        builder.show()
    }


    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 6036)
    }


    private fun checkSelfPermission(): Boolean {
        return if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            false
        } else
            true
    }


    @SuppressLint("Recycle")
    private fun getAllShownImagesPath(activity: Activity): ArrayList<Album> {
        val uri: Uri
        val cursor: Cursor?
        var column_index_data = 0
        var column_index_folder_name = 0
        val listOfAllImages = ArrayList<Album>()
        var absolutePathOfImage: String? = null
        var imageName: String? = null
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

        cursor = activity.contentResolver.query(uri, projection, null, null, null)

        if (cursor != null) {
            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(column_index_data)
                imageName = cursor.getString(column_index_folder_name)
                listOfAllImages.add(Album(absolutePathOfImage, imageName))
            }
        }

        return listOfAllImages
    }


}
