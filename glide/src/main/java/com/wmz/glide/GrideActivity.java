package com.wmz.glide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GrideActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String url = "http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg";
        String url = "http://p5.qhimg.com/t01d0e0384b952ed7e8.gif";
        ImageView imageView = new ImageView(this);
        Glide.with(this).load(url).into(imageView);
        setContentView(imageView);

    }


}
