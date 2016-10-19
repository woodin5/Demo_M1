package com.wmz.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by miles
 * 15/12/18.
 */
public class HorzionScroll extends View {

    public HorzionScroll(Context context) {
        super(context);
    }

    public HorzionScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorzionScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected float start;
    protected float pre;
    protected VelocityTracker velocityTracker;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                removeCallbacks(goOnDraw);
                drawCount = 1;
                initOrResetVelocityTracker();
                velocityTracker.addMovement(event);
                start = event.getRawX();
                pre = start;
                break;
            case MotionEvent.ACTION_MOVE:
                velocityTracker.addMovement(event);
                scrollBy((int) (pre - event.getRawX() - 0.5), 0);
                pre = event.getRawX();
                break;
            case MotionEvent.ACTION_UP:
                velocityTracker.addMovement(event);
                velocityTracker.computeCurrentVelocity(1000, ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity());
                mVelocity = (int) velocityTracker.getXVelocity();
                postDelayed(goOnDraw, 50);
                break;
            case MotionEvent.ACTION_CANCEL:
                endScorll();
                break;
            default:
                break;
        }
        return true;
    }

    protected int mVelocity, drawCount;

    protected Runnable goOnDraw = new Runnable() {
        private int REDRAW_COUNT = 20;

        @Override
        public void run() {
            if (drawCount < REDRAW_COUNT && Math.abs(mVelocity) > 5000) {
                int dis = (int) (mVelocity * 1.0f / REDRAW_COUNT * (REDRAW_COUNT - drawCount + 0.5) * 0.1 / (drawCount * 5));
                scrollBy(-dis, 0);
                drawCount++;
                postDelayed(goOnDraw, 500 / REDRAW_COUNT);
            } else {
                endScorll();
            }
        }
    };

    protected void endScorll() {
        recycleVelocityTracker();
        getParent().requestDisallowInterceptTouchEvent(false);
    }

    protected void recycleVelocityTracker() {
        if (velocityTracker != null) {
            velocityTracker.recycle();
            velocityTracker = null;
        }
    }

    protected void initOrResetVelocityTracker() {
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

}
