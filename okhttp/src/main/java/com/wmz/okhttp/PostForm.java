package com.wmz.okhttp;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wmz on 24/5/16.
 */
public class PostForm {
    private OkHttpClient client = new OkHttpClient();
    public void run() throws Exception{
        RequestBody body = new FormBody.Builder()
                .add("search","Jurassic Park")
                .build();
        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if(!response.isSuccessful()) throw new IOException("Unexcepted code="+response);
        System.out.println(response.body().string());
    }
}
