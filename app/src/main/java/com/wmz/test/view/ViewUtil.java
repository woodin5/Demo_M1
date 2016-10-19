package com.wmz.test.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import java.util.Calendar;

public class ViewUtil {

    /**
     * 获取画笔所画字符的行高
     */
    public static int getPaintRowHeight(Paint paint) {
        FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.bottom - fm.top) + 2;
    }

    /**
     * 获取画笔的纠正行高的值
     */
    public static int getPaintPutRight(Paint paint) {
        FontMetrics fm = paint.getFontMetrics();
        return (int) (Math.ceil(fm.descent) + 1);
    }

    /**
     * 取得Value所占的宽
     */
    public static int getStringWidth(Paint paint, String value) {
        if (value != null) {
            return (int) paint.measureText(value) + 1;
        }
        return 0;
    }

    /**
     * 转换时间字符串
     */
    private String transDate(Calendar calendar) {
        return String.format("%04d-%02d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }








    /**
     * @param colorA   the startColor
     * @param colorB   the endColor
     * @param degree   the count of color what you want between startColor & endColor
     * @param progress the index in degree
     * @return
     */
    public static int getColorBetweenAB(int colorA, int colorB, float degree, int progress) {
        // calculate R
        float r = (((colorB & 0xFF0000) >> 16) - ((colorA & 0xFF0000) >> 16)) / degree * progress + ((colorA & 0xFF0000) >> 16);
        // calculate G
        float g = (((colorB & 0x00FF00) - (colorA & 0x00FF00)) >> 8) / degree * progress + ((colorA & 0x00FF00) >> 8);
        // calculate B
        float b = ((colorB & 0x0000FF) - (colorA & 0x0000FF)) / degree * progress + (colorA & 0x0000FF);
        return Color.rgb((int) r, (int) g, (int) b);
    }

    /**
     * @param b       需要模糊的bitmap
     * @param context
     * @return 模糊后的bitmap
     */
    public static Bitmap blur(Bitmap b, Context context) {
        RenderScript rs = RenderScript.create(context);
        Allocation overlayAlloc = Allocation.createFromBitmap(rs, b);
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, overlayAlloc.getElement());
        blur.setInput(overlayAlloc);
        blur.setRadius(25);
        blur.forEach(overlayAlloc);
        overlayAlloc.copyTo(b);
        return b;

    }

    /**
     * @param paint 测试前，必须保证paint已经设置过textSize , Typeface;
     * @return 该paint画出文字的理应高度
     */
    public static float getTextHeight(Paint paint) {
        FontMetrics m = paint.getFontMetrics();
        return m.bottom - m.top;
    }

    /**
     * @param paint
     * @param content
     * @return
     */
    public static float getTextRectWidth(Paint paint, String content) {
        // if()
        Rect rect = new Rect();

        paint.getTextBounds(content, 0, content.length(), rect);
//		return rect.width();
        return paint.measureText(content);
    }

    /**
     * 这个返回值<={@link #getTextHeight(Paint)}
     *
     * @return
     */
    public static float getTextRectHeight(Paint paint, String content) {
        Rect rect = new Rect();
        paint.getTextBounds(content, 0, content.length(), rect);
        return rect.height();
    }

    public static float px2Dp(int px, Context context) {
        return px * context.getResources().getDisplayMetrics().density;
    }

}
