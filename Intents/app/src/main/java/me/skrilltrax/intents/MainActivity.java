package me.skrilltrax.intents;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "me.skrilltrax.intents.EXTRA_USERNAME";
    public static final String EXTRA_PASSWORD = "me.skrilltrax.intents.EXTRA_PASSWORD";
    public static final int REQUEST_CLICK = 1;

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CLICK) {
            if (resultCode == RESULT_OK) {
                String reply = data.getData().toString();
                Toast.makeText(getApplicationContext(),reply,Toast.LENGTH_LONG).show();
            }
        }
    }
}
