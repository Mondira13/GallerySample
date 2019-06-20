package com.example.gallerysample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * Created by Sayan on 05-09-2017.
 */

public class GlideConnector {


    private static GlideConnector instance = null;

    private GlideConnector() {

    }

    public static GlideConnector getInstance() {
        if (instance == null) {
            instance = new GlideConnector();
        }
        return instance;
    }

    public void loadImageDirectly(Context context, String imageURL, ImageView imageView) {
//        if (imageURL.contains(".gif")) {
//            Glide.with(context)
//                    .load(imageURL)
//                    .thumbnail(0.1f)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
////                .placeholder(R.drawable.ic_launcher_background)
////                .crossFade()
//                    .into(new GlideDrawableImageViewTarget(imageView));
//        } else {
//            Glide.with(context)
//                    .load(imageURL)
//                    .thumbnail(0.1f)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
////                .placeholder(R.drawable.ic_launcher_background)
////                .crossFade()
//                    .into(imageView);
//        }
        loadImageDirectlyWithoutThumbnail(context, imageURL, imageView);
    }

    public void loadImageDirectlyWithoutThumbnail(Context context, String imageURL, ImageView imageView) {
        try {
            if (context != null) {
                if (imageURL.contains(".gif")) {
                    Glide.with(context)
                            .load(imageURL)
                            .apply(
                                    new RequestOptions()
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            )
                            .into(new DrawableImageViewTarget(imageView));
                } else {
                    Glide.with(context)
                            .load(imageURL)
                            .apply(
                                    new RequestOptions()
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            )
                            .into(imageView);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadImageDirectlyWithSize(Context context, String imageURL, ImageView imageView, int height, int width) {
        try {
            Glide.with(context)
                    .load(imageURL)
                    .apply(
                            new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .override(width, height)
                                    .centerCrop()
                    )
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadImageBitmapWithSize(Context context, String imageURL, ImageView imageView, int height, int width) {
        Glide.with(context)
                .load(imageURL)
                .apply(
                        new RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .override(width, height)
                                .centerCrop()
                )
                .into(imageView);
    }

    public void loadImageBitmapWithSize(Context context, String imageURL, final ImageView imageView, final int height, final int width, final GlideCallback callback) {
        try {
            DrawableImageViewTarget viewTarget = Glide.with(context.getApplicationContext())
                    .load(imageURL)
                    .apply(
                            new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .override(width, height)
                                    .centerCrop()
                    )
    //                .listener(new RequestListener<Drawable>() {
    //                    @Override
    //                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
    //                        //on load failed
    //                        callback.onFailure();
    //                        return false;
    //                    }
    //
    //                    @Override
    //                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
    //                        //on load success
    //                        callback.onSuccess();
    //                        return false;
    //                    }
    //                })
                    .into(new DrawableImageViewTarget(imageView, true) {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            super.onResourceReady(resource, transition);
                            callback.onSuccess();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static interface GlideCallback {
        void onSuccess();

        void onFailure();
    }

}
