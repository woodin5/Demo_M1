package com.wmz.okhttp.helper;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.wmz.okhttp.manager.ExecutorManager;
import com.wmz.okhttp.manager.OkHttpManager;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by wmz on 26/5/16.
 */
public class OkHttpHelper extends NetworkHelper {
    private OkHttpManager okHttpManager;

    public OkHttpHelper(){
        okHttpManager = OkHttpManager.getManager();
    }

    public OkHttpHelper(Context context){
        super(context);
        okHttpManager = OkHttpManager.getManager();
    }




    public RequestBody getRequestBody(){
        return new FormBody.Builder()
                .add("username","1")
                .add("password","1")
                .build();
    }

    @Override
    public void execute(final String url, final Handler handler,final int what){
        ExecutorManager.execute(new Runnable() {
            @Override
            public void run() {
                handler.sendMessage(Message.obtain(handler, what, execute(url)));
            }
        });
    }

    @Override
    public void execute(final String url, final CallBack callBack){
        ExecutorManager.execute(new Runnable() {
            @Override
            public void run() {
                callBack.response(execute(url));
            }
        });
    }

    @Override
    public String execute(final String url){
        return okHttpManager.execute(url);
    }

    @Override
    public void execute(final String url, final RequestBody body,final CallBack callBack){
        ExecutorManager.execute(new Runnable() {
            @Override
            public void run() {
                callBack.response(execute(url,body));
            }
        });
    }
    @Override
    public String execute(String url, RequestBody body){
        return okHttpManager.execute(url,body);
    }
    @Override
    public void enqueue(String url, Callback responseCallback){
        okHttpManager.enqueue(url,responseCallback);
    }


}
