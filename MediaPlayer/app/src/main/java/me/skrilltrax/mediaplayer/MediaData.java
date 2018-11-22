package me.skrilltrax.mediaplayer;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class MediaData {

    public static final String TAG = "mediainfo";

    private long mediaID;
    private long thumnbailID;
    private String mediaTitle;
    private String mediaPath;
    private String thumbnailPath;

    String[] projection = {
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DATA,
    };

    String[] thumbnailProjection = {
            MediaStore.Video.Thumbnails._ID,
            MediaStore.Video.Thumbnails.VIDEO_ID,
            MediaStore.Video.Thumbnails.DATA
    };


    public MediaData(long mediaID, String mediaTitle, String mediaPath, String thumbnailPath) {
        this.mediaID = mediaID;
        this.mediaTitle = mediaTitle;
        this.mediaPath = mediaPath;
        this.thumbnailPath = thumbnailPath;
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

    public String getThumbnailPath() { return thumbnailPath; }

    public ArrayList<MediaData> getMediaInfo(Context context) {

        ArrayList<MediaData> arrayList = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Uri mediaUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Cursor mediaCursor = contentResolver.query(mediaUri, projection, null, null, MediaStore.Video.Media.DEFAULT_SORT_ORDER);
        Uri thumbnailUri = MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI;
        Cursor thumbnailCursor = contentResolver.query(thumbnailUri, thumbnailProjection, null, null, MediaStore.Video.Thumbnails.VIDEO_ID + " DESC" );


        if((mediaCursor != null && mediaCursor.moveToFirst()) && (thumbnailCursor != null && thumbnailCursor.moveToFirst()))
        {
            int mediaId = mediaCursor.getColumnIndex(MediaStore.Video.Media._ID);
            int mediaTitle = mediaCursor.getColumnIndex(MediaStore.Video.Media.TITLE);
            int mediaPath = mediaCursor.getColumnIndex(MediaStore.Video.Media.DATA);

            int thumbnailId = thumbnailCursor.getColumnIndex(MediaStore.Video.Thumbnails._ID);
            int thumbnailPath = thumbnailCursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA);
            do {

                long currentId = mediaCursor.getLong(mediaId);
                long mediaID = thumbnailCursor.getLong(thumbnailId);
                String currentTitle = mediaCursor.getString(mediaTitle);
                String currentPath = mediaCursor.getString(mediaPath);
                String currentThumbnailPath = thumbnailCursor.getString(thumbnailPath);
//                previewImage = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, currentId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
//                Log.e(TAG,"Got thumbnail");
//                arrayList.add(new MediaData(currentId, currentTitle, currentPath, previewImage));
                arrayList.add(new MediaData(currentId, currentTitle, currentPath, currentThumbnailPath));
                Log.d(TAG,currentPath);
                mediaCursor.moveToNext();
                thumbnailCursor.moveToNext();
            } while(!mediaCursor.isAfterLast() && !thumbnailCursor.isAfterLast());
        }
        Log.d(TAG,arrayList.toString());
        return arrayList;
    }

}
