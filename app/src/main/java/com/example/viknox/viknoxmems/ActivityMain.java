package com.example.viknox.viknoxmems;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ActivityMain extends AppCompatActivity {
    Toolbar toobar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toobar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toobar);
        loadUI();


    }

    private void loadUI() {
        ImageView backGroundImage = (ImageView) findViewById(R.id.bg_image);
        Glide.with(this)
                .load("http://media.istockphoto.com/photos/abstract-cubes-retro-styled-colorful-background-picture-id508795172?k=6&m=508795172&s=170667a&w=0&h=uxKISA4xMNkrBCcEFhdN5mm-ZDv8LFzWUhMMmG3CNuQ=")
                .centerCrop()
                .into(backGroundImage);

    }

    public void showDialog(View view) {

    }
}
