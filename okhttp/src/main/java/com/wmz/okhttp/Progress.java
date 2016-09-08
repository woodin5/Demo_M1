package com.wmz.okhttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by wmz on 24/5/16.
 */
public class Progress {
    public void run() throws  Exception{
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        final ProgressListener progressListener = new ProgressListener(){
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                System.out.println(bytesRead);
                System.out.println(contentLength);
                System.out.println(done);
                System.out.format("%d%% done\n", (100 * bytesRead) / contentLength);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        return response.newBuilder().body(new ProgressResponseBody(response.body(),progressListener)).build();
                    }
                }).build();
        Response response = client.newCall(request).execute();
        if(!response.isSuccessful()) throw new IOException("Unexcepted code="+response);
        System.out.println(response.body().string());

    }

    private static class ProgressResponseBody extends ResponseBody{
        private ResponseBody body;
        private ProgressListener listener;
        private BufferedSource source;
        public ProgressResponseBody(ResponseBody body,ProgressListener listener){
            this.body = body;
            this.listener = listener;
        }

        @Override
        public BufferedSource source() {
            if(this.source==null){
                this.source = Okio.buffer(source(body.source()));
            }
            return this.source;
        }

        @Override
        public long contentLength() {
            return body.contentLength();
        }

        @Override
        public MediaType contentType() {
            return body.contentType();
        }

        public Source source(Source source){
            return new ForwardingSource(source) {
                long totalBytesRead = 0l;
                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink,byteCount);
                    totalBytesRead += bytesRead!=-1?bytesRead:0;
                    listener.update(totalBytesRead,body.contentLength(),bytesRead==-1);
                    return bytesRead;
                }
            };
        }
    }
    interface ProgressListener{
        void update(long bytesRead,long contentLength,boolean done);
    }
}
