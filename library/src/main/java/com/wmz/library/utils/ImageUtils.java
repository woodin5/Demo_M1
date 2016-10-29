package com.wmz.library.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.wmz.library.R;

/**
 * Created by wmz on 28/10/16.
 */

public class ImageUtils {
    private static Paint textPaintWhite;
    static {
        textPaintWhite = new Paint();
        textPaintWhite.setColor(Color.WHITE);
        textPaintWhite.setTextAlign(Paint.Align.CENTER);
        textPaintWhite.setTextSize(18);
    }

    /**
     * 在图片上绘制原点
     *
     * @param baseDrawable
     * @return
     */
    public static Drawable getDotDrawable(Drawable baseDrawable, Resources resources) {
        Bitmap bitmap = Bitmap.createBitmap(baseDrawable.getIntrinsicWidth(), baseDrawable.getIntrinsicHeight(),
                baseDrawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);
        baseDrawable.setBounds(0, 0, baseDrawable.getIntrinsicWidth(), baseDrawable.getIntrinsicHeight());
        baseDrawable.draw(canvas);

        Bitmap bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.dot_red);
        Bitmap newBitmap = bitmap1.createScaledBitmap(bitmap1,30,30,false);
        int left = bitmap.getWidth() - newBitmap.getWidth();// left表示从左边那个位置开始画起

        canvas.drawBitmap(newBitmap, left, 0, new Paint());// 画上红色的圈圈

        int x = (int) (bitmap.getWidth() - newBitmap.getWidth() / 2d);
        int y = (int) (newBitmap.getWidth() * 0.8d);

        BitmapDrawable ret = new BitmapDrawable(resources, bitmap);

        return ret;
    }

    /**
     * 在图片上绘制未读消息数
     *
     * @param baseDrawable
     * @param unreadedNum
     * @return
     */
    public static Drawable getUnreadedDrawable(Drawable baseDrawable, Resources resources, int unreadedNum) {
        Bitmap bitmap = Bitmap.createBitmap(baseDrawable.getIntrinsicWidth(), baseDrawable.getIntrinsicHeight(),
                baseDrawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);
        baseDrawable.setBounds(0, 0, baseDrawable.getIntrinsicWidth(), baseDrawable.getIntrinsicHeight());
        baseDrawable.draw(canvas);

        Bitmap bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.dot_red);
        Bitmap newBitmap = bitmap1.createScaledBitmap(bitmap1,30,30,false);
        int left = bitmap.getWidth() - newBitmap.getWidth();// left表示从左边那个位置开始画起

        canvas.drawBitmap(newBitmap, left, 0, new Paint());// 画上红色的圈圈

        int x = (int) (bitmap.getWidth() - newBitmap.getWidth() / 2d);
        int y = (int) (newBitmap.getWidth() * 0.8d);
        String text = unreadedNum > 9 ? "n" : String.valueOf(unreadedNum);
        canvas.drawText(text, x, y, textPaintWhite);

        BitmapDrawable ret = new BitmapDrawable(resources, bitmap);

        return ret;
    }

    /**
     * 在Bitmap图片上画未读消息
     *
     * @param bitmap
     * @param resources
     * @param unreadedNum
     * @return
     */
    public static Bitmap getUnreadedBitmap(Bitmap bitmap, Resources resources, int unreadedNum) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, bitmap);
        BitmapDrawable ret = (BitmapDrawable) getUnreadedDrawable(bitmapDrawable, resources, unreadedNum);
        return ret.getBitmap();
    }

}
