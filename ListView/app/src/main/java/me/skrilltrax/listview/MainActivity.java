package me.skrilltrax.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] names = new String[26];
        for (int i = 0; i < 26; i++)
        {
            names[i] = String.valueOf((char) (i + 65));
        }

        ListAdapter customAdapter = new CustomAdapter(this, R.layout.custom_list_view, names);

        ListView myListView = findViewById(R.id.listView);
        myListView.setAdapter(customAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String food = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),food,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
