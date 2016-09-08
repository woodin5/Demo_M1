package com.wmz.test.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wmz on 16/5/16.
 */
public class BubbleView3 extends RelativeLayout {

    private int[] datas = {5,4,3,2,1,6,11,8,9,7,10};
    private ArrayList<Button> datasView = new ArrayList<Button>();
    private HashMap<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();
    private SparseArray<ArrayList<Integer>> sparseArray = new SparseArray<>();

    public BubbleView3(Context context) {
        super(context);
    }

    public BubbleView3(Context context, AttributeSet attrs) {
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

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width,height);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            button.setLayoutParams(params);
            button.setX(left);
            datasView.add(button);
            addView(button);

        }

        bubble_sort(datas);

        ArrayList<Integer> list  = map.get(index);
        startAnim(list.get(0), list.get(1));
}


    public void startAnim(final int i,final int j){
        final Button view1 = datasView.get(i);
        final Button view2 = datasView.get(j);
        com.nineoldandroids.animation.AnimatorSet set = new AnimatorSet();
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
                if (index < map.size()) {
                    ArrayList<Integer> list = map.get(index);
                    startAnim(list.get(0), list.get(1));
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

    private int index = 0;
    public void bubble_sort(int[] unsorted)
    {
        int k = 0;
        for (int i = 0; i < unsorted.length; i++)
        {
            for (int j = i; j < unsorted.length;  j++)
            {
                if (unsorted[i] > unsorted[j])
                {
                    int temp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    list.add(j);
                    map.put(k++, list);

                }
            }
        }
    }
}
