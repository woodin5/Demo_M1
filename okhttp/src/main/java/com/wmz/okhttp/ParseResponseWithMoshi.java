package com.wmz.okhttp;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wmz on 24/5/16.
 */
public class ParseResponseWithMoshi {
    private OkHttpClient client = new OkHttpClient();
    private Moshi moshi = new Moshi.Builder().build();
    private JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);

    public void run() throws Exception{
        Request request = new Request.Builder()
                .url("https://api.github.com/gists/c2a7c39532239ff261be")
                .build();
        Response response = client.newCall(request).execute();
        if(!response.isSuccessful()) throw new IOException("UnExcepted code="+response);
        Gist gist = gistJsonAdapter.fromJson(response.body().source());
        for(Map.Entry<String,GistFile> entry:gist.files.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().content);
        }
    }
    static class Gist{
        Map<String,GistFile> files;
    }
    static class GistFile{
        String content;
    }
}
