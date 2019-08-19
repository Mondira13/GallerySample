package com.example.gallerysample


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.gallerysample.genericclasses.GlideConnector
import android.app.AlertDialog
import android.widget.ImageView
import android.widget.TextView


class ViewImageActivity : AppCompatActivity() {

    lateinit var imageUrl : String
    lateinit var name : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_image)

        imageUrl = intent.getStringExtra("imageUrl")
        name = intent.getStringExtra("imageName")

        openAlertDialog()


    }

    private fun openAlertDialog() {
        val inflater = layoutInflater
        val dialoglayoutView = inflater.inflate(R.layout.alert_dialog_layout, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialoglayoutView)
        val imageview = dialoglayoutView.findViewById<ImageView>(R.id.ViewImage)
        val imageName = dialoglayoutView.findViewById<TextView>(R.id.name)
        GlideConnector.getInstance().loadImageDirectlyWithoutThumbnail(this, imageUrl, imageview)
        imageName.setText(name)
        builder.show()
    }
}
