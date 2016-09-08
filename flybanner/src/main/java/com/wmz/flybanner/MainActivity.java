package com.wmz.flybanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        String[] mImagesUrl = {
                "http://img4.imgtn.bdimg.com/it/u=2430963138,1300578556&fm=23&gp=0.jpg",
                "http://img1.imgtn.bdimg.com/it/u=2755648979,3568014048&fm=23&gp=0.jpg",
                "http://img0.imgtn.bdimg.com/it/u=2272739960,4287902102&fm=23&gp=0.jpg",
                "http://img3.imgtn.bdimg.com/it/u=1078051055,1310741362&fm=23&gp=0.jpg"
        };
        List<String> imgesUrl = new ArrayList<>();
        for (int i = 0; i < mImagesUrl.length; i++) {
            imgesUrl.add(mImagesUrl[i]);
        }
        FlyBanner flyBanner = (FlyBanner) findViewById(R.id.banner_1);
        flyBanner.setImagesUrl(imgesUrl);
        flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getApplicationContext(),"p="+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
