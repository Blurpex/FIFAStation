package com.example.fifastation;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class PlayerActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        ImageView image = findViewById(R.id.imageView);
        Picasso.get().load("https://cdn.sofifa.net/players/158/023/22_120.png").placeholder(R.mipmap.ic_launcher).into(image);
    }

}
