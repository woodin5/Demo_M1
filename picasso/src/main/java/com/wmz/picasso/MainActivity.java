package com.wmz.picasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import it.sephiroth.android.library.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        ImageView img1 = (ImageView) findViewById(R.id.img_1);
        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(img1);
    }

}
