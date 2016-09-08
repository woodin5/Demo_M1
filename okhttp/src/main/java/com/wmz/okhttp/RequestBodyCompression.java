package com.wmz.okhttp;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/**
 * Created by wmz on 24/5/16.
 */
public class RequestBodyCompression {
    public static final String GOOGLE_API_KEY = "AIzaSyAx2WZYe0My0i-uGurpvraYJxO7XNbwiGs";
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");
    private final OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new GzipRequestInterceptor())
            .build();
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Map<String,String>> mapJsonAdapter = moshi.adapter(Types.newParameterizedType(Map.class,String.class,String.class));

    public void run() throws Exception{
        Map<String,String> requestBody = new LinkedHashMap<>();
        requestBody.put("longUrl", "https://publicobject.com/2014/12/04/html-formatting-javadocs/");
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON,mapJsonAdapter.toJson(requestBody));
        Request request = new Request.Builder()
                .url("https://www.googleapis.com/urlshortener/v1/url?key=" + GOOGLE_API_KEY)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if(!response.isSuccessful()) throw new IOException("Unexcepted code="+response);
        System.out.println(response.body().string());
    }
    static class GzipRequestInterceptor implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if(request.body()==null || request.header("Content-Encoding")!=null){
                return chain.proceed(request);
            }
            Request request1 = request.newBuilder()
                    .header("Content-Encoding", "gzip")
                    .method(request.method(),gzip(request.body()))
                    .build();
            return chain.proceed(request1);
        }
        public RequestBody gzip(final RequestBody body){
            return new RequestBody() {
                @Override
                public MediaType contentType() {
                    return body.contentType();
                }

                @Override
                public void writeTo(BufferedSink sink) throws IOException {
                    BufferedSink sink1 = Okio.buffer(new GzipSink(sink));
                    body.writeTo(sink1);
                    sink1.close();
                }
            };
        }
    }
}
