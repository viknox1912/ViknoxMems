package com.example.viknox.viknoxmems;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class ActivityMain extends AppCompatActivity {
    Toolbar toobar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toobar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toobar);


    }
}
