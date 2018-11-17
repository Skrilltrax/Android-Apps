package me.skrilltrax.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, int resource, String[] names) {
        super(context, R.layout.custom_list_view, names);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_list_view,parent,false);

        String name = getItem(position);
        TextView textView = customView.findViewById(R.id.text);
        textView.setText(name);
        ImageView imageView = customView.findViewById(R.id.imageView);
        imageView.setImageResource(R.mipmap.ic_launcher_round);

        return customView;

    }
}
