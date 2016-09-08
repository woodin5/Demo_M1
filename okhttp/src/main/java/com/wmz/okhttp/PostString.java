package com.wmz.okhttp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wmz on 24/5/16.
 */
public class PostString {
    private OkHttpClient client = new OkHttpClient();
    private MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    public void run() throws Exception{
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN,postBody))
                .build();
        OkHttpClient client1 = client.newBuilder().readTimeout(50, TimeUnit.SECONDS).build();
        Response response = client1.newCall(request).execute();
        if(!response.isSuccessful()) throw new IOException("Unexcepted code="+response);
        System.out.println(response.body().string());
    }
}
