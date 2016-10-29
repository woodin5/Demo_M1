package com.wmz.test.activity;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.wmz.library.utils.ImageUtils;
import com.wmz.test.R;
import com.wmz.test.base.BaseActivity;

import butterknife.BindView;

public class RedDotActivity extends BaseActivity {

    @BindView(R.id.iv_red_dot)
    ImageView mIv;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_red_dot;
    }

    @Override
    public void initData() {
        super.initData();
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        Drawable drawable1 = ImageUtils.getUnreadedDrawable(drawable,getResources(),6);
        mIv.setImageDrawable(drawable1);
    }
}
