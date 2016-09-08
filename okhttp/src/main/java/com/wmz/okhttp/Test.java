package com.wmz.okhttp;

import com.wmz.okhttp.manager.OkHttpManager;

import okhttp3.Callback;
import okhttp3.RequestBody;

/**
 * Created by wmz on 26/5/16.
 */
public class Test {

    public String execute(String url){
        return new OkHttpManager().execute(url);
    }

    public String execute(String url, RequestBody body){
        return new OkHttpManager().execute(url,body);
    }

    public void enqueue(String url, Callback responseCallback){
         new OkHttpManager().enqueue(url,responseCallback);
    }

}
