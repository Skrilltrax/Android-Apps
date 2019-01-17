package me.skrilltrax.intents;

import android.content.Intent;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "me.skrilltrax.intents.EXTRA_USERNAME";
    public static final String EXTRA_PASSWORD = "me.skrilltrax.intents.EXTRA_PASSWORD";
    public static final int REQUEST_CLICK = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText usernameEdit = findViewById(R.id.usernameEdit);
        final EditText passwordEdit = findViewById(R.id.passwordEdit);

        final Intent i = new Intent(this,secondActivity.class);

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(usernameEdit.getText().toString()) && TextUtils.isEmpty(passwordEdit.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"no username and password",Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(usernameEdit.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"no username",Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(passwordEdit.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"no password",Toast.LENGTH_SHORT).show();
                } else {
                    Bundle userInfo = new Bundle();
                    userInfo.putString(EXTRA_USERNAME,usernameEdit.getText().toString());
                    userInfo.putString(EXTRA_PASSWORD,passwordEdit.getText().toString());

                    i.putExtras(userInfo);
                    startActivityForResult(i,REQUEST_CLICK);
                }
            }
        });

        final Intent cameraIntent = new Intent();
        cameraIntent.setAction(Intent.ACTION_SEND);
        cameraIntent.putExtra(Intent.EXTRA_TEXT, "YOOOOOO");
        cameraIntent.setType("text/plain");
        String title = "Share With";
        final Intent chooser = Intent.createChooser(cameraIntent, title);


        Button cameraButton = findViewById(R.id.cameraButton);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(chooser,REQUEST_IMAGE_CAPTURE);
                } else {
                    Toast.makeText(getApplicationContext(),"Not Working", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        final MediaSession mediaSession = new MediaSession(this,"MYMEDIASESSION");

        final Intent third = new Intent("com.android.music.musicservicecommand");
        third.putExtra("command", "play");
        sendBroadcast(third);
        Log.e("MUSICSTART", "HERE");
        final PlaybackState.Builder playbackState = new PlaybackState.Builder();
        playbackState.setActions(PlaybackState.ACTION_PLAY);
        final PlaybackState playbackState1 = playbackState.build();
        final Button thirdActivity = findViewById(R.id.thirdActivity);
        thirdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(third);
                mediaSession.setPlaybackState(playbackState1);

                Log.e("MUSICSTART", "HERE");

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CLICK) {
            if (resultCode == RESULT_OK && data !=null) {
                String reply = (data.getData() != null ) ? data.getData().toString() : null;
                Toast.makeText(getApplicationContext(),reply,Toast.LENGTH_LONG).show();
            }
        }

//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//        }
    }
}
