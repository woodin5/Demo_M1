package com.wmz.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wmz.test.view.BubbleView2;

import java.util.Random;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private BubbleView2 BubbleView2;
    private Button btnIn, btnOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        btnIn = (Button) findViewById(R.id.in);
        btnOut = (Button) findViewById(R.id.out);
        btnIn.setOnClickListener(this);
        btnOut.setOnClickListener(this);
        BubbleView2 = (BubbleView2) findViewById(R.id.bubbleView2);
        BubbleView2.setDuration(800l);
        BubbleView2.setOnItemClickListener(this);
        // 添加
        feedBubbleView2(BubbleView2, keywords);
        BubbleView2.go2Show(BubbleView2.ANIMATION_IN);
    }

    public String[] keywords = {
            "QQ", "Sodino", "APK", "GFW", "铅笔",
            "短信", "桌面精灵", "MacBook Pro", "平板电脑", "雅诗兰黛",
            "卡西欧 TR-100", "笔记本", "SPY Mouse", "Thinkpad E40", "捕鱼达人",
            "内存清理", "地图", "导航", "闹钟", "主题",
            "通讯录", "播放器", "CSDN leak", "安全", "3D",
            "美女", "天气", "4743G", "戴尔", "联想",
            "欧朋", "浏览器", "愤怒的小鸟", "mmShow", "网易公开课",
            "iciba", "油水关系", "网游App", "互联网", "365日历",
            "脸部识别", "Chrome", "Safari", "中国版Siri", "A5处理器",
            "iPhone4S", "摩托 ME525", "魅族 M9", "尼康 S2500"
    };


    private static void feedBubbleView2(BubbleView2 BubbleView2, String[] arr) {
        Random random = new Random();
        for (int i = 0; i < BubbleView2.MAX; i++) {
            int ran = random.nextInt(arr.length);
            String tmp = arr[ran];
            BubbleView2.feedKeyword(tmp);
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == btnIn) {
            BubbleView2.rubKeywords();
            // BubbleView2.rubAllViews();
            feedBubbleView2(BubbleView2, keywords);
            BubbleView2.go2Show(BubbleView2.ANIMATION_IN);
        } else if (v == btnOut) {
            BubbleView2.rubKeywords();
            // BubbleView2.rubAllViews();
            feedBubbleView2(BubbleView2, keywords);
            BubbleView2.go2Show(BubbleView2.ANIMATION_OUT);
        } else if (v instanceof TextView) {
            String keyword = ((TextView) v).getText().toString();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("http://www.google.com.hk/#q=" + keyword));
            startActivity(intent);
        }
    }

}
