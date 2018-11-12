package me.skrilltrax.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;


public class BottomFragment extends Fragment {

    private static TextView topText;
    private static TextView bottomText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom,container,false);
        topText = view.findViewById(R.id.textView2);
        bottomText = view.findViewById(R.id.textView3);
        return view;
    }

    public void setMemeText(String top, String bottom) {
        topText.setText(top);
        bottomText.setText(bottom);
    }
}