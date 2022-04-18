package com.midterm.vominhnhut.otherActivites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.midterm.vominhnhut.R;

public class DetailActivity extends AppCompatActivity {
    TextView title,desc,ts,lat,lng,add,e,zip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if(intent != null){
            title = findViewById(R.id.tvv_title);
            title.setText(intent.getStringExtra("title"));

            desc = findViewById(R.id.tvv_desc);
            desc.setText(intent.getStringExtra("desc"));

            ts = findViewById(R.id.tvv_ts);
            ts.setText(intent.getStringExtra("timestamp"));

            lat = findViewById(R.id.tvv_lat);
            lat.setText(intent.getStringExtra("lat"));

            lng = findViewById(R.id.tvv_lng);
            lng.setText(intent.getStringExtra("lng"));

            add = findViewById(R.id.tvv_addr);
            add.setText(intent.getStringExtra("addr"));

            e = findViewById(R.id.tvv_e);
            e.setText(intent.getStringExtra("e"));

            zip = findViewById(R.id.tvv_zip);
            zip.setText(intent.getStringExtra("zip"));
        }
    }
}