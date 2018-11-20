package me.skrilltrax.mediaplayer;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class ImageLoader extends AsyncTask<Void,Void,Bitmap> {

    private View view;
    private Bitmap previewImage;
    private long currentId;
    private ContentResolver contentResolver;
    private ImageView thumbnail;
    private WeakReference<Context> contextRef;


    public ImageLoader(Context context, View view, long currentId) {
        contextRef = new WeakReference<>(context);
        this.currentId = currentId;
        this.view = view;
        contentResolver = contextRef.get().getContentResolver();
    }


    @Override
    protected Bitmap doInBackground(Void... voids) {
        previewImage = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, currentId, MediaStore.Video.Thumbnails.MINI_KIND,null);
        return previewImage;
    }

    @Override
    protected void onPostExecute(Bitmap previewImage) {
        if (previewImage != null) {
            thumbnail = view.findViewById(R.id.imageView);
            thumbnail.setImageBitmap(previewImage);

        }

    }
}
