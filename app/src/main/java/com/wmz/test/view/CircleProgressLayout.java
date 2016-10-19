package com.wmz.test.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by miles
 * 15/12/11.
 */
public class CircleProgressLayout extends RelativeLayout {

    private static final float RING_SPACE = 10f / 460f;   // 环边距离
    private static final float FG_RADIUS = 400f / 460f;

    private final long ANIM_TIME = 1500;    // 动画运动最大时间

    private int progress = 100;
    private int maxProgress = 100;

    private Paint bgPaint;                // 背景
    private Paint fgPaint;                // 前景

    private Paint ringPaint;              // 环
    private Paint redPaint;
    private Paint linePaint;

    private int[] SWEEP_COLOR = {         // 画笔渐变的颜色
            0x00f55641, 0xfff55641, 0x00f55641
    };

    private float w;          // 宽
    private float h;          // 高
    private float cx;         // 中心x
    private float cy;         // 中心y
    private float radius;     // 半径
    private RectF ringRect;
    private float ringWidth;  // 环的宽度
    private float spaceWidth; // 空间宽度

    public CircleProgressLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setDither(true);
        fgPaint = new Paint(bgPaint);

        ringPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setStrokeCap(Paint.Cap.BUTT);
        ringPaint.setColor(Color.argb(0, 0, 0, 0));


        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStyle(Paint.Style.STROKE);
        redPaint.setStrokeCap(Paint.Cap.ROUND);
        redPaint.setColor(SWEEP_COLOR[1]);
        linePaint = new Paint(redPaint);

        ringRect = new RectF();
        //
        bgPaint.setColor(Color.argb(100, 0, 0, 0)); // defColor
        fgPaint.setColor(Color.argb(255, 0xf5, 0x56, 0x41));
        // 保证onDraw方法被调用
        setWillNotDraw(false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initSize(w, h);
    }

    private void initSize(int w, int h) {
        this.w = w;
        this.h = h;
        cx = w / 2f;
        cy = h / 2f;
        radius = Math.min(w, h) / 2f; // 半径

        spaceWidth = RING_SPACE * radius;

        ringWidth = (1 - RING_SPACE * 2 - FG_RADIUS) * radius; // 环的宽度
        ringPaint.setStrokeWidth(ringWidth);
        redPaint.setStrokeWidth(ringWidth);

        // 中心点到环中心的距离
        float LENGTH = (1 - (1 - FG_RADIUS) * 0.5f) * radius;
        ringRect.set(cx - LENGTH, cy - LENGTH, cx + LENGTH, cy + LENGTH);
    }

    public void startProgressAnim() {
        int value = (int) ((float) progress / (float) maxProgress * ANIM_TIME);
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "progress", 0, this.progress);
        animator.setDuration(value);
        animator.start();
    }

    public void setProgress(int progress) {
        progress = Math.min(100, Math.max(0, progress));
        if (progress != this.progress) {
            this.progress = progress;
            setShader(this.progress);
            postInvalidate();
        }
    }

    private void setShader(int progress) {
        SweepGradient gradient = new SweepGradient(cx, cy,
                SWEEP_COLOR,
                new float[]{0f, (float) progress / (float) maxProgress, 1f});
        Matrix matrix = new Matrix();
        matrix.setRotate(-90, cx, cy);
        gradient.setLocalMatrix(matrix);
        //
        ringPaint.setShader(gradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBG(canvas);
        drawFG(canvas);
        drawProgress(canvas);
    }

    /**
     * 画进度条
     */
    private void drawProgress(Canvas canvas) {
        if (progress > 0) {
            canvas.drawLine(cx, spaceWidth, cx, spaceWidth + ringWidth, linePaint);
            canvas.drawArc(ringRect, -90, progress / 100f * 360, false, ringPaint);
            canvas.drawArc(ringRect, progress / 100f * 360 - 90, 1f, false, redPaint);
        }
    }

    /**
     * 画前景
     */
    private void drawFG(Canvas canvas) {
        canvas.drawCircle(cx, cy, radius * FG_RADIUS, fgPaint);
    }

    /**
     * 画背景
     */
    private void drawBG(Canvas canvas) {
        canvas.drawCircle(cx, cy, radius, bgPaint);
    }

}
