package com.wmz.okhttp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wmz on 24/5/16.
 */
public class AccessHeaders {
    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent","Ok Http Headers.java")
                .addHeader("Accept","application/json;q=0.5")
                .addHeader("Accept","application/vnd.github.v3+json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if(!response.isSuccessful()) throw new IOException("UnExcepted Code="+response);
            System.out.println("Server: " + response.header("Server"));
            System.out.println("Date: " + response.header("Date"));
            System.out.println("Vary: " + response.headers("Vary"));
        }catch (IOException e){

        }
    }
}
