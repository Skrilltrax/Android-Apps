package me.skrilltrax.overflowmenu;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Drawable drawable = toolbar.getOverflowIcon();
        if(drawable != null) {
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable.mutate(), Color.WHITE);
            toolbar.setOverflowIcon(drawable);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        RelativeLayout mainActivity = findViewById(R.id.main_activity);

        switch (id) {
            case R.id.red:
                if (!item.isChecked()) {
                    item.setChecked(true);
                    mainActivity.setBackgroundColor(Color.RED);
                }
                break;

            case R.id.green:
                if (!item.isChecked()) {
                    item.setChecked(true);
                    mainActivity.setBackgroundColor(Color.GREEN);
                }

                break;

            case R.id.blue:
                if (!item.isChecked()) {
                    item.setChecked(true);
                    mainActivity.setBackgroundColor(Color.BLUE);
                }
                break;
        }

        return true;
    }
}
