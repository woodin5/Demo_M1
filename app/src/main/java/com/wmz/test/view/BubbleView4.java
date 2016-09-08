package com.wmz.test.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.wmz.test.Quick;

import java.util.ArrayList;

/**
 * Created by wmz on 16/5/16.
 */
public class BubbleView4 extends RelativeLayout {

    private int[] datas = {5,4,3,2,1,6,11,8,9,7,10};
    private ArrayList<Button> datasView = new ArrayList<Button>();
    private ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    private int index = 0;

    public BubbleView4(Context context) {
        super(context);
    }

    public BubbleView4(Context context, AttributeSet attrs) {
        super(context, attrs);
        for(int i=0;i<datas.length;i++){

            int width = 20;
            int height = (datas[i])*width;

            int left = i*40;
            int top = 5;
            int right = 5;
            int bottom = 5;
            Button button = new Button(context);
            button.setText(String.valueOf(datas[i]));
            button.setTextColor(Color.WHITE);
            button.setBackgroundColor(Color.BLACK);

            LayoutParams params = new LayoutParams(width,height);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            button.setLayoutParams(params);
            button.setX(left);
            datasView.add(button);
            addView(button);

        }

        new Quick().sort(list,datas,0,datas.length-1);
        ArrayList<Integer> list1  = list.get(index);
        startAnim(list1.get(0), list1.get(1));
}


    public void startAnim(final int i,final int j){
        final Button view1 = datasView.get(i);
        final Button view2 = datasView.get(j);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(view1, "translationX", view1.getX(), view2.getX()), ObjectAnimator.ofFloat(view2, "translationX", view2.getX(), view1.getX()));

        set.setDuration(1 * 1000).start();

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Button temp = view1;
                datasView.set(i,view2);
                datasView.set(j,view1);
                index++;
                if (index < list.size()) {
                    ArrayList<Integer> list1 = list.get(index);
                    startAnim(list1.get(0), list1.get(1));
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


}
