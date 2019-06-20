package com.example.gallerysample;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.example.gallerysample.adapter.GalleryAdapter;
import com.example.gallerysample.model.Album;

import java.util.ArrayList;

public class Gallerysample extends Activity {

    RecyclerView gallery;
    private ArrayList<Album> images;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        gallery = findViewById(R.id.galleryRecycler);

        if (!checkSelfPermission()) {
            requestPermission();
        } else {
            // if permission granted read images from storage.
            //  source code for this function can be found below.

            loadAllImagesFromGallery();

        }

    }

    private void loadAllImagesFromGallery(){
        images = getAllShownImagesPath(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        gallery.setLayoutManager(gridLayoutManager);
        gallery.setAdapter(new GalleryAdapter(this,images));
        setGalleryItemClickListener();
    }

    private void setGalleryItemClickListener() {

        gallery.addOnItemTouchListener(new RecyclerItemClickListener(this, gallery, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, MotionEvent motionEvent) {
                if (null != images && !images.isEmpty())
                    Toast.makeText(getApplicationContext(), "position " + position + " " + images.get(position), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {  }

        }));

    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{(Manifest.permission.READ_EXTERNAL_STORAGE)}, 6036);
    }


    private Boolean checkSelfPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else
            return true;
    }



    private ArrayList<Album> getAllShownImagesPath(Activity activity) {
        Uri uri;
        Cursor cursor;
        int column_index_data = 0, column_index_folder_name = 0;
        ArrayList<Album> listOfAllImages = new ArrayList<>();
        String absolutePathOfImage = null;
        String imageName = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        cursor = activity.getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null) {
            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(column_index_data);
                imageName = cursor.getString(column_index_folder_name);
                listOfAllImages.add(new Album(absolutePathOfImage,imageName));
            }
        }

        return listOfAllImages;
    }
























    /**
     * The Class ImageAdapter.
     */
//    private class ImageAdapter extends BaseAdapter {
//
//        /** The context. */
//        private Activity context;
//
//        /**
//         * Instantiates a new image adapter.
//         *
//         * @param localContext
//         *            the local context
//         */
//        public ImageAdapter(Activity localContext) {
//            context = localContext;
//            images = getAllShownImagesPath(context);
//        }
//
//        public int getCount() {
//            return images.size();
//        }
//
//
//        public Object getItem(int position) {
//            return position;
//        }
//
//        public long getItemId(int position) {
//            return position;
//        }
//
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            ImageView picturesView;
//            if (convertView == null) {
//                picturesView = new ImageView(context);
//                picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                picturesView.setLayoutParams(new GridView.LayoutParams(270, 270));
//
//            } else {
//                picturesView = (ImageView) convertView;
//            }
//
//            Glide.with(context).load(images.get(position)).into(picturesView);
//            return picturesView;
//        }
//
//        /**
//         * Getting All Images Path.
//         *
//         * @param activity
//         *            the activity
//         * @return ArrayList with images Path
//         */
//        private ArrayList<String> getAllShownImagesPath(Activity activity) {
//            Uri uri;
//            Cursor cursor;
//            int column_index_data, column_index_folder_name;
//            ArrayList<String> listOfAllImages = new ArrayList<String>();
//            String absolutePathOfImage = null;
//            uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//
//            String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME };
//
//            cursor = activity.getContentResolver().query(uri, projection, null, null, null);
//
//            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//            column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
//            while (cursor.moveToNext()) {
//                absolutePathOfImage = cursor.getString(column_index_data);
//                listOfAllImages.add(absolutePathOfImage);
//            }
//            return listOfAllImages;
//        }
//    }

}
