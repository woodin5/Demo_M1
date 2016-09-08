package com.wmz.library.helper;

import android.content.Context;
import android.os.Handler;

/**
 * Created by wmz on 26/5/16.
 */
public class NetworkHelper {
    public Context context;

    public NetworkHelper() {
    }

    public NetworkHelper(Context context) {
        this.context = context;
    }

    public String execute(String url) {
        return null;
    }

    public void execute(String url, Handler handler, int what) {
    }

    public void execute(String url, CallBack callBack) {
    }


    public interface CallBack {
        void response(String response);
    }
}
