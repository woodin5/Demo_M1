package com.wmz.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miles
 * 15/12/18.
 */
public class PointLineView extends HorzionScroll {

    // 满屏时最多显示7个点
    private final int VISIBLE_COUNT = 7;

    private Paint paint;

    private int w, h;

    private int color = 0xffef5440;

    // 点到点的距离
    private float pointDis;

    private float radius;

    private float bigRadius;

    private ArrayList<Integer> datas = new ArrayList<>();

    /**
     * y坐标与data的转换比 y = data * yScale;
     */
    private float yScale;

    private float yZero;

    private int pointIndex;

    private float yAxisLength;

    private int selectIndex;

    private int lineWidth = 3;

    private onDateScrolling linstener;

    public PointLineView(Context context) {
        super(context);
        init();
    }

    public PointLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PointLineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        setWillNotDraw(false);
    }

    public void setCurrentItem(int position) {
        selectIndex = 6 - position;
        scrollTo((int) (-(6 - position) * pointDis), 0);
        if (linstener != null) {
            linstener.onScrolling(6 - selectIndex);
        }
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        selectIndex = getRealScroll(-l) / (int) pointDis;
        if (linstener != null) {
            linstener.onScrolling(6 - selectIndex);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;

        pointDis = w / VISIBLE_COUNT;
        radius = pointDis / 5;
        bigRadius = radius * 1.3f;

        yZero = h - 1 - bigRadius;
        yAxisLength = yZero - 1 - bigRadius;

        setYscale();
        if (selectIndex != 0) {
            scrollTo((int) (-selectIndex * pointDis), 0);
        }
    }

    private void setYscale() {
        float yMax = 0.1f;// 为了防止除0异常
        for (int i = 0; i < datas.size(); i++) {
            yMax = Math.max(yMax, datas.get(i));
        }
        yScale = yAxisLength / yMax;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(1);
        canvas.drawLine(getScrollX(), yZero - yAxisLength - bigRadius, w + getScrollX(), yZero - yAxisLength - bigRadius, paint);
        canvas.drawLine(getScrollX(), yZero + bigRadius, w + getScrollX(), yZero + bigRadius, paint);
        paint.setStrokeWidth(lineWidth);

        drawPoints(canvas);
        drawCenter(canvas);
    }


    private void drawCenter(Canvas canvas) {
        if (datas.size() <= 0) return;
        paint.setStyle(Paint.Style.FILL);
        // x坐标始终在屏幕中心位置
        float cx = w / 2 + getScrollX();
        // 被选中的数据
        float yData1 = datas.get(selectIndex);
        // 默认选择光点的y位置为选中点的位置
        float cy = yZero - yData1 * yScale;
        // 如果在两点之间，计算他的y坐标
        if (selectIndex < datas.size() - 1) {
            float yData2 = datas.get(selectIndex + 1);
            cy -= (yData1 - yData2) * yScale / -pointDis * (getRealScroll(-getScrollX()) % pointDis);
        }
        canvas.drawCircle(cx, cy, radius, paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(cx, cy, bigRadius, paint);
        paint.setAlpha(125);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawRect(cx - bigRadius, yZero - yAxisLength - bigRadius, cx + bigRadius, yZero + bigRadius, paint);

    }

    private void drawPoints(Canvas canvas) {
        if (datas == null) {
            return;
        }
        paint.setAlpha(255);
        paint.setStyle(Paint.Style.STROKE);
        ArrayList<Integer> points = datas;
        for (int i = 0; i < points.size(); i++) {
            canvas.save();
            // 该点的y坐标
            float y = yZero - datas.get(pointIndex + i) * yScale;
            // 移动到该点处
            canvas.translate(w / 2 - pointDis * (pointIndex + i), y);
            canvas.drawCircle(0, 0, radius, paint);

            if (pointIndex + i < datas.size() - 1) {// 如果后面还有点，就要画线
                // 两点的竖向坐标距离
                float yDis = (datas.get(pointIndex + i) - datas.get(pointIndex + i + 1)) * yScale;
                // 两点连线的斜率角度
                float degree = (float) (180 / Math.PI * Math.atan2(yDis, -pointDis));
                canvas.rotate(degree);
                // 旋转后2点连线即为x轴，计算出2点距离
                float dis = (float) Math.sqrt(yDis * yDis + pointDis * pointDis);

                if (getScrollX() % pointDis == 0) {
                    if (selectIndex == pointIndex + i) {

                        canvas.drawLine(bigRadius, 0, dis - radius, 0, paint);
                    } else if (selectIndex - 1 == pointIndex + i) {
                        canvas.drawLine(radius, 0, dis - bigRadius, 0, paint);
                    } else {
                        canvas.drawLine(radius, 0, dis - radius, 0, paint);
                    }
                } else {
                    canvas.drawLine(radius, 0, dis - radius, 0, paint);
                }

            }
            canvas.restore();
        }

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (linstener != null) {
            linstener.onScrolling(6 - selectIndex);
        }
    }

    protected int getRealScroll(int scroll) {
        int maxOffset = (int) ((datas.size() - 1) * pointDis);
        return Math.min(maxOffset, Math.max(0, scroll));
    }

    @Override
    protected void endScorll() {
        super.endScorll();
        int scroll = getRealScroll(-getScrollX());
        // 找到最近的点
        int index = scroll % (int) pointDis > pointDis * 0.5f ? selectIndex + 1 : selectIndex;
        scrollTo((int) (-index * pointDis), 0);
    }

    public void setGraphColor(int color) {
        this.color = color;
    }

    public interface onDateScrolling {
        void onScrolling(int index);
    }

    public void setDateScrollingLinstener(onDateScrolling linstener) {
        this.linstener = linstener;
    }

    public void setDatas(List<Integer> datas) {
        if (datas == null) {
            return;
        }
        this.datas.clear();
        for (int i = datas.size() - 1; i >= 0; i--) {
            this.datas.add(datas.get(i));
        }
        setYscale();
        invalidate();
    }

}
