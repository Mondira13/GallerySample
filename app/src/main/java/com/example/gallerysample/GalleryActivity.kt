package com.example.gallerysample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.gallerysample.model.Albums

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

//        savedState = savedInstanceState
//
//        if (savedState != null)
//            folder_name = savedInstanceState!!.getString("folder_name")

//        setSupportActionBar(my_toolbar)
        // Enable the Up button
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.setHomeAsUpIndicator(resources.getDrawable(R.drawable.ic_menu_white_24dp))

//        setupNavigationView()

        var extra = intent.extras;
        if (extra != null) {
            var extraData = extra.get("image_url_data") as ArrayList<Albums>
            select_fragment(extraData)
        }

//        drawer_layout_listener()
        supportActionBar!!.setTitle("Folders")

    }

    public fun select_fragment(imagesList: ArrayList<Albums>) {

//        val options = RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(160, 160).skipMemoryCache(true).error(R.drawable.ic_image_unavailable)
        val glide = Glide.with(this)

        val builder = glide.asBitmap()
//        rvAlbums?.layoutManager = GridLayoutManager(this, 2)
//
//        rvAlbums?.setHasFixedSize(true)
//
//        // AlbumFoldersAdapter.kt is RecyclerView Adapter class. we will implement shortly.
//        rvAlbums?.adapter = GalleryAdapter(imagesList, this, options, builder, glide)
//
//
//        rvAlbums?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(galleryRecycler, dx, dy)
//            }
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(galleryRecycler, newState)
//                when (newState) {
//                    RecyclerView.SCROLL_STATE_IDLE -> glide.resumeRequests()
//                    AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL, AbsListView.OnScrollListener.SCROLL_STATE_FLING -> glide.pauseRequests()
//                }
//            }
//        }
//        )

//        fab_camera?.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(p0: View?) {
//                launchCamera()
//            }
//        }

    }











}
