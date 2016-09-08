package com.wmz.library.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by wmz on 20/6/16.
 */
public class SharedPreferencesUtils {

    /**
     * 保存map集合key,value
     * @param mContext
     * @param name
     * @param map
     */
    public static void putString(Context mContext, String name, Map<String, String> map) {
        SharedPreferences spf = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        for(Map.Entry<String,String> entry:map.entrySet()){
            editor.putString(entry.getKey(),entry.getValue());
        }
        editor.commit();
    }

    /**
     * 保存单个key,value
     * @param mContext
     * @param name
     * @param key
     * @param value
     */
    public static void putString(Context mContext,String name,String key,String value){
        SharedPreferences spf = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        spf.edit().putString(key,value).commit();
    }

    /**
     * 获取String值
     * @param mContext
     * @param name
     * @param key
     * @return
     */
    public static String getString(Context mContext, String name, String key) {
        SharedPreferences spf = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        return spf.getString(key, "");
    }

    /**
     * 清空name文件里所有数据
     * @param mContext
     * @param name
     */
    public static void clear(Context mContext, String name) {
        SharedPreferences spf = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        spf.edit().clear().commit();
    }

    /**
     * 删除key值
     * @param mContext
     * @param name
     * @param key
     */
    public static void remove(Context mContext, String name, String key) {
        SharedPreferences spf = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        spf.edit().remove(key).commit();
    }

    /**
     * 是否存在key值
     * @param mContext
     * @param name
     * @param key
     * @return
     */
    public static boolean hasKey(Context mContext, String name, String key) {
        SharedPreferences sp = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.contains(key);
    }
}
