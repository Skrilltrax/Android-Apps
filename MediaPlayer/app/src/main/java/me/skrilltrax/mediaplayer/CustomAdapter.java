package me.skrilltrax.mediaplayer;

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

    private ArrayList<MediaData> mediaDataArrayList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView headingText;
        TextView subtitleText;
        ImageView previewImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            headingText = itemView.findViewById(R.id.text_heading);
            subtitleText = itemView.findViewById(R.id.text_subtitle);
            previewImage = itemView.findViewById(R.id.imageView);

        }
    }

    public CustomAdapter(ArrayList<MediaData> data) {
        this.mediaDataArrayList = data;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.media_list,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder myViewHolder, int position) {

        TextView headingText = myViewHolder.headingText;
        TextView subtitleText = myViewHolder.subtitleText;
        ImageView previewImage = myViewHolder.previewImage;

        headingText.setText(mediaDataArrayList.get(position).getMediaTitle());
        subtitleText.setText(mediaDataArrayList.get(position).getMediaPath());
    }

    @Override
    public int getItemCount() {
        return mediaDataArrayList.size();
    }
}
