package com.wmz.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wmz on 16/5/16.
 */
public class BubbleView extends View {

    private int[] datas = {6,7,8,9,10,11,3,4,5,2,1};

    public BubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);

        for(int i=0;i<datas.length;i++) {
            int left = (i+1)*40;
            int top =  250 - datas[i]*20;
            int right = (i+1)*40 +20;
            int bottom = 250;
            canvas.drawRect(left,top, right, bottom, paint);
            canvas.drawText(String.valueOf(datas[i]),left+2,bottom+40,paint);
        }

        postDelayed(new Runnable() {
            @Override
            public void run() {
                flag = true;
                bubble_sort(datas);
            }
        },1000);
    }

    private int i = 0;
    private boolean flag = false;
    public void bubble_sort(int[] unsorted)
    {
        while(i<unsorted.length && flag)
//        for (int i = 0; i < unsorted.length; i++)
        {
            for (int j = i; j < unsorted.length; j++)
            {
                if (unsorted[i] > unsorted[j])
                {
                    int temp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                }
            }
            i++;
            invalidate();
            flag = false;
        }
    }
}
