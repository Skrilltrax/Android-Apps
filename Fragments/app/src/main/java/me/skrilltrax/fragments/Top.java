package me.skrilltrax.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Top extends Fragment {

    private EditText editTextTop;
    private EditText editTextBottom;
    private TextView textView;

    public interface TopSectionListener {
        void createMeme(String top, String bottom);
    }

    TopSectionListener activityCommander;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityCommander = (TopSectionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top,container,false);

        editTextTop = view.findViewById(R.id.editText);
        editTextBottom = view.findViewById(R.id.editText2);
        Button button = view.findViewById(R.id.setText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonclick(v);
            }
        });

        return view;
    }

    public void buttonclick(View view) {
        activityCommander.createMeme(editTextTop.getText().toString(), editTextBottom.getText().toString());
    }

}

