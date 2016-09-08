package com.wmz.okhttp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wmz on 24/5/16.
 */
public class GetExample {
    OkHttpClient client = new OkHttpClient();
    String run(String url) throws IOException{
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String content = response.body().string();
        System.out.println("wmz:content-"+content);
        return content;
    }
}
