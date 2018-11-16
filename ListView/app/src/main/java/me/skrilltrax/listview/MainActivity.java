package me.skrilltrax.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] stuff = new String[30];

        for (int i = 0 ; i < 30; i++)
            stuff[i] = String.valueOf(i);
        ArrayAdapter<String> stuffAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1, stuff);

        ListView myListView = findViewById(R.id.listView);
        myListView.setAdapter(stuffAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String food = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),food,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
