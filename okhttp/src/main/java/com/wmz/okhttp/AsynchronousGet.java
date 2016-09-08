package com.wmz.okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by wmz on 24/5/16.
 */
public class AsynchronousGet {
    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("https://www.baidu.com/")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                if (!response.isSuccessful()) throw new IOException("UnExcepted Code=" + response);
                Headers headers = response.headers();
                for (int i = 0, size = headers.size(); i < size; i++) {
                    System.out.println(headers.name(i) + ":" + headers.value(i));
                }
                System.out.println(body.string());
            }

        });
    }
}
