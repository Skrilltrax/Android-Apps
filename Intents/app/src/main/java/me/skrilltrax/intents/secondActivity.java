package me.skrilltrax.intents;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static me.skrilltrax.intents.MainActivity.EXTRA_USERNAME;

public class secondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle getInfo;

        Intent i = getIntent();
        getInfo = i.getExtras();

        TextView userText = new TextView(this);
        userText.setText(getInfo.getString(EXTRA_USERNAME));
        userText.setTextColor(Color.BLACK);
        userText.setId(View.generateViewId());

        RelativeLayout secondLayout = findViewById(R.id.secondActivity);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.CENTER_VERTICAL);

        secondLayout.addView(userText,params);

        Button backButton = new Button(this);
        backButton.setText("BACK");
        backButton.setId(View.generateViewId());

        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.addRule(RelativeLayout.BELOW, userText.getId());
        buttonParams.setMargins(0,500,0,0);

        secondLayout.addView(backButton,buttonParams);
        backButton.setText("BACK");

        Snackbar.make(findViewById(backButton.getId()),"Hello", Snackbar.LENGTH_SHORT).show();

        final Intent returnIntent = new Intent();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnIntent.setData(Uri.parse("YO RETURNED YO"));
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
