package com.example.gallerysample

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.gallerysample.genericclasses.GlideConnector
import kotlinx.android.synthetic.main.activity_view_image.*

class ViewImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_image)

        val imageUrl = intent.getStringExtra("imageUrl")
        GlideConnector.getInstance().loadImageDirectlyWithoutThumbnail(this, imageUrl, ViewImage)

    }
}
