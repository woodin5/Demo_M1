package com.wmz.library.utils;

import android.content.Context;
import android.content.res.Resources;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wmz on 18/6/16.
 */
public class ResourceUtils {

    /**
     * 打开assets目录下的文件(assets不可省略，完整目录)
     * @param mContext
     * @param resName
     * @return
     */
    public static InputStream getResourceAsStream(Context mContext,String resName){
        InputStream is = null;
        try {
            is = mContext.getClass().getClassLoader().getResourceAsStream(resName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }
    /**
     * 打开assets目录下的文件(assets可省略)
     * @param fileName
     * @return
     */
    public static InputStream openAssetsFile(Context mContext,String fileName){
        InputStream is = null;
        try {
            is = mContext.getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * 打开res目录的原生文件
     * @param res
     * @return
     */
    public static InputStream openRawResource(Context mContext,int res) {
        InputStream is = null;
        try {
            is = mContext.getResources().openRawResource(res);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return is;
    }
}
