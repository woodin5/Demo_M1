package com.wmz.test;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wmz.library.manager.NetworkRequest;
import com.wmz.library.manager.NetworkResponse;
import com.wmz.library.parser.sax.BaseParserHandler;
import com.wmz.library.parser.sax.ParseUtils;
import com.wmz.library.utils.ResourceUtils;
import com.wmz.library.utils.StringUtils;
import com.wmz.library.view.XListView;
import com.wmz.test.parser.BookParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext = TestActivity.this;
    private LinearLayout layout;
    private TextView mTvShow;
    private Button mBtn;
    private XListView mXListView;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mTvShow.setText(msg.obj.toString());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.btn_press);
        mBtn.setOnClickListener(this);

        mXListView = (XListView) findViewById(R.id.XListview);

        mXListView.setPullLoadEnable(true);
        mTvShow = (TextView) findViewById(R.id.tv_show);
    }


    @Override
    public void onClick(View v) {
        //解析xml文件
//        parse();

//        get();

        showXListView();
    }

    private ArrayList<String> dates = new ArrayList<>();

    private void showXListView() {
        setDates();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, dates);
        mXListView.setAdapter(adapter);
        mXListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onLoad();
                        dates.add(0, "onRefresh-" + new Date().toLocaleString());
                        adapter.notifyDataSetChanged();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onLoad();
                        dates.add("onLoadMore-" + new Date().toLocaleString());
                        adapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        });
    }

    private void onLoad() {
        mXListView.stopRefresh();
        mXListView.stopLoadMore();
        mXListView.setRefreshTime(new Date().toLocaleString());
    }

    private void setDates() {
        for (int i = 0; i < 20; i++) {
            dates.add("item_" + i);
        }
    }

    private void get() {
        Log.d("wmz", "get");
        String url = "http://120.25.103.18:8097/user/login"; //?phone=1&password=2&code=3";
        Map<String, String> map = new HashMap<>();
        map.put("phone", "15112362535");
        map.put("password", "123");
        map.put("login_type", "2");
        new NetworkRequest().execute(url, StringUtils.map2Params(map), new NetworkResponse() {
            @Override
            public void onResponse(String response) {
                mHandler.sendMessage(mHandler.obtainMessage(0, response));
            }

            @Override
            public void onError(String error) {
                Log.d("wmz", "wmz:error=" + error);
            }
        });
    }


    /**
     * 解析xml文件
     */
    private void parse() {
        try {
            InputStream is = ResourceUtils.getResourceAsStream(mContext, "assets/books.xml");

            ParseUtils.parse(is, new BookParser(new BaseParserHandler.ParseEndListener() {
                @Override
                public void onParseEnd(Object o) {
                    mTvShow.setText(o.toString());
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
