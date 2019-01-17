package me.skrilltrax.mediaplayer;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Cache;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private static final String TAG = "mediainfo";
    private static Picasso picassoWithCache;
    private ArrayList<MediaData> mediaDataArrayList;
    private File httpCacheDirectory;
    private okhttp3.Cache cache;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView headingText;
        TextView subtitleText;
        ImageView previewImage;
        String thumbnailPath;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            headingText = itemView.findViewById(R.id.text_heading);
            subtitleText = itemView.findViewById(R.id.text_subtitle);
            previewImage = itemView.findViewById(R.id.imageView);
            thumbnailPath = null;
        }

        public View getView() {
            return itemView;
        }
    }


    public CustomAdapter(ArrayList<MediaData> data, Context context) {
        this.mediaDataArrayList = data;
        this.context = context;
        httpCacheDirectory = new File(context.getCacheDir(), "picasso-cache");
        cache = new okhttp3.Cache(httpCacheDirectory, 15* 1024 * 1024);
        try {
            Log.e(TAG,String.valueOf(httpCacheDirectory));
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().cache(cache);
            picassoWithCache = new Picasso.Builder(context.getApplicationContext()).downloader(new OkHttp3Downloader(okHttpClientBuilder.build())).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.media_list,parent,false);
        view.setOnClickListener(new MainActivity.MyOnClickListener());
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder myViewHolder, int position) {

        TextView headingText = myViewHolder.headingText;
        TextView subtitleText = myViewHolder.subtitleText;
        ImageView previewImage = myViewHolder.previewImage;
        String thumbnailPath = myViewHolder.thumbnailPath;

        headingText.setText(mediaDataArrayList.get(position).getMediaTitle());
        subtitleText.setText(mediaDataArrayList.get(position).getMediaPath());
        thumbnailPath = mediaDataArrayList.get(position).getThumbnailPath();
        picassoWithCache.load(Uri.fromFile(new File(thumbnailPath))).into(previewImage);
        Log.e(TAG,String.valueOf(position));
//        new ImageLoader(context,myViewHolder.getView(), mediaDataArrayList.get(position).getMediaID()).execute();

    }

    @Override
    public int getItemCount() {
        return (mediaDataArrayList.size());
    }

//    public Uri getUriFromBitmap(Context context, long currentId) {
//
//        ContentResolver contentResolver = context.getContentResolver();
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        Bitmap previewImage;
//        previewImage = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, currentId, MediaStore.Video.Thumbnails.MINI_KIND,null);
//        previewImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), previewImage, "Title", null);
//
//        return Uri.parse(path);
//    }
}