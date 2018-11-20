package me.skrilltrax.mediaplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.ThumbnailUtils;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    public static final String TAG = "mediainfo";

    Context context;
    private ArrayList<MediaData> mediaDataArrayList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView headingText;
        TextView subtitleText;
        ImageView previewImage;
        Bitmap previewImageBitmap;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            headingText = itemView.findViewById(R.id.text_heading);
            subtitleText = itemView.findViewById(R.id.text_subtitle);
            previewImage = itemView.findViewById(R.id.imageView);

        }

        public View getView() {
            return itemView;
        }
    }


    public CustomAdapter(ArrayList<MediaData> data, Context context) {
        this.mediaDataArrayList = data;
        this.context = context;
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
        Bitmap[] previewImageThumb = new Bitmap[1];

        headingText.setText(mediaDataArrayList.get(position).getMediaTitle());
        subtitleText.setText(mediaDataArrayList.get(position).getMediaPath());
        new ImageLoader(context,myViewHolder.getView(), mediaDataArrayList.get(position).getMediaID()).execute();

//        final android.os.Handler handler = new android.os.Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//
//                if (previewImageThumb[0] != null)
//                    previewImage.setImageBitmap(previewImageThumb[0]);
//            }
//        };
//
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                previewImageThumb[0] = ThumbnailUtils.createVideoThumbnail(subtitleText.getText().toString(),MediaStore.Video.Thumbnails.MICRO_KIND);
//                Log.e(TAG,"CREATEEDDD");
//                Log.d(TAG,subtitleText.toString());
//                handler.sendEmptyMessage(0);
//            }
//
//        };

    }

    @Override
    public int getItemCount() {
        return mediaDataArrayList.size();
    }
}