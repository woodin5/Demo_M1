package com.wmz.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.wmz.test.view.CircleProgressLayout;
import com.wmz.test.view.SportBarChart;

import java.util.ArrayList;
import java.util.List;

public class AnimActivity extends Activity implements View.OnClickListener{

    private SportBarChart sportBarChart;

    private CircleProgressLayout circleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_anim);

        sportBarChart = (SportBarChart) findViewById(R.id.sportBarChart);

        circleLayout = (CircleProgressLayout) findViewById(R.id.circleLayout);

        sportBarChart.setOnClickListener(this);
        circleLayout.setOnClickListener(this);

        List<Integer> list = new ArrayList<>();
        for(int i=0;i<24;i++){
            list.add(i+1);
        }
        sportBarChart.setDatas(list);
    }

    @Override
    public void onClick(View v) {
        if (v == circleLayout) {
            runHideSportPicView();
        } else if (v == sportBarChart) {
            runHideSportBarChart();
        }
    }

    private void runHideSportBarChart() {
        circleLayout.setEnabled(false);
        sportBarChart.setEnabled(false);
        sportBarChart.setVisibility(View.GONE);
        circleLayout.setVisibility(View.VISIBLE);
        Animation scaleAnim = new ScaleAnimation(
                0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        scaleAnim.setDuration(500);
        scaleAnim.setAnimationListener(showPieView);
        circleLayout.startAnimation(scaleAnim);
    }

    private void runHideSportPicView() {
        circleLayout.setEnabled(false);
        sportBarChart.setEnabled(false);

        Animation scaleAnim = new ScaleAnimation(
                1.0f, 0.0f, 1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        scaleAnim.setDuration(500);
        scaleAnim.setAnimationListener(hidePieView);
        circleLayout.startAnimation(scaleAnim);
    }

    // 显示
    private AnimationAction showPieView = new AnimationAction() {
        @Override
        public void onAnimationEnd(Animation animation) {
            sportBarChart.setEnabled(true);
            circleLayout.setEnabled(true);
        }
    };

    // 隐藏动画的监听
    private AnimationAction hidePieView = new AnimationAction() {
        @Override
        public void onAnimationEnd(Animation animation) {
            circleLayout.setVisibility(View.GONE);
            sportBarChart.setVisibility(View.VISIBLE);
            sportBarChart.setEnabled(true);
            circleLayout.setEnabled(true);
            sportBarChart.startAnimSet();
        }
    };

    abstract class AnimationAction implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

}
