package me.skrilltrax.mediaplayer;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MediaData {

    public static final String TAG = "mediainfo";

    private long mediaID;
    private String mediaTitle;
    private String mediaPath;
    private Bitmap mediaThumb;

    String[] projection = {
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DATA
    };

    public MediaData(long mediaID, String mediaTitle, String mediaPath) {
        this.mediaID = mediaID;
        this.mediaTitle = mediaTitle;
        this.mediaPath = mediaPath;
//        this.mediaThumb = mediaThumb;
    }

    public MediaData() {

    }

    public long getMediaID() {
        return mediaID;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public Bitmap getMediaThumb() { return mediaThumb; }

    public ArrayList<MediaData> getMediaInfo(Context context) {

        ArrayList<MediaData> arrayList = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Uri mediaUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Cursor mediaCursor = contentResolver.query(mediaUri, projection, null, null, MediaStore.Video.Media.DEFAULT_SORT_ORDER);

        Bitmap previewImage;

        if(mediaCursor != null && mediaCursor.moveToFirst())
        {
            int mediaId = mediaCursor.getColumnIndex(MediaStore.Video.Media._ID);
            int mediaTitle = mediaCursor.getColumnIndex(MediaStore.Video.Media.TITLE);
            int mediaPath = mediaCursor.getColumnIndex(MediaStore.Video.Media.DATA);

            do {
                long currentId = mediaCursor.getLong(mediaId);
                String currentTitle = mediaCursor.getString(mediaTitle);
                String currentPath = mediaCursor.getString(mediaPath);
//                previewImage = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, currentId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
//                Log.e(TAG,"Got thumbnail");
//                arrayList.add(new MediaData(currentId, currentTitle, currentPath, previewImage));
                if (currentPath.contains("WhatsApp"));
                arrayList.add(new MediaData(currentId, currentTitle, currentPath));
                Log.d(TAG,currentPath);
            } while(mediaCursor.moveToNext());
        }
        Log.d(TAG,arrayList.toString());
        return arrayList;
    }

}