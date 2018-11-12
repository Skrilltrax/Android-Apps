package me.skrilltrax.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Top.TopSectionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    Called by TopFragment when user press the button

    @Override
    public void createMeme(String top, String bottom) {
        BottomFragment bottomFragment = (BottomFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3);
        if (bottomFragment != null) {
            System.out.println(top);
            bottomFragment.setMemeText(top, bottom);
        }
    }
}