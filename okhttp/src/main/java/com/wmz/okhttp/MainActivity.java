package com.wmz.okhttp;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.wmz.okhttp.helper.NetworkHelper;
import com.wmz.okhttp.helper.OkHttpHelper;

public class MainActivity extends AppCompatActivity {
    String url = "https://api.github.com/repos/square/okhttp/contributors";
    String content = "";
    private NetworkHelper networkHelper;
    TextView textView;
    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            textView.setText(msg.obj.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        new OkHttpManager().enqueue(url, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                handler.sendMessage(Message.obtain(handler, 0, null));
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                handler.sendMessage(Message.obtain(handler, 0, response.body().string()));
//            }
//        });

//        new NetworkHelper().execute("http://192.168.199.206:8080/Test/Login?username=1&password=1", new NetworkHelper.CallBack() {
//            @Override
//            public void response(String response) {
//                handler.sendMessage(Message.obtain(handler, 0, response));
//            }
//        });

        networkHelper = new OkHttpHelper();
        networkHelper.execute("http://www.baidu.com/", new NetworkHelper.CallBack() {
            @Override
            public void response(String response) {
//                Gson gson = new Gson();
//                User user = gson.fromJson(response,User.class);
//                User user = JSON.parseObject(response,User.class);
                handler.sendMessage(Message.obtain(handler, 0, response));
            }
        });

//        networkHelper.execute("http://192.168.199.209:8080/Test/Login?username=1&password=1",handler,0);

        textView = new TextView(this);
        setContentView(textView);
    }


}
