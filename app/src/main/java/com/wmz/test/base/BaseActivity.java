package com.wmz.test.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by wmz on 29/10/16.
 */

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);
        initData();
        initEvent();
    }

    public abstract int getLayoutResourceId();

    public void initData() {

    }

    public void initEvent(){

    }

    private Toast mToast;
    protected void showToast(String message) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }
}
