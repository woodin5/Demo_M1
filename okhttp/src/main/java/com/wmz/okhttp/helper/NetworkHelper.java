package com.wmz.okhttp.helper;

import android.content.Context;
import android.os.Handler;

import okhttp3.Callback;
import okhttp3.RequestBody;

/**
 * Created by wmz on 26/5/16.
 */
public class NetworkHelper {
    public Context context;

    public NetworkHelper(){}
    public NetworkHelper(Context context){
        this.context = context;
    }
    public void execute(final String url, final CallBack callBack){}

    public String execute(final String url){
        return null;
    }
    public void execute(final String url, final Handler handler,final int what){}
    public void execute(final String url, final RequestBody body,final CallBack callBack){}
    
    public String execute(String url, RequestBody body){
        return null;
    }
    
    public void enqueue(String url, Callback responseCallback){
    }

    public interface CallBack{
        void response(String response);
    }
}
