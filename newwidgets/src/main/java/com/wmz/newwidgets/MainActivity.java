package com.wmz.newwidgets;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wmz.newwidgets.adapter.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mFloatingActionButton;
    private NavigationView mNavigationView;

    private String[] mTitles;
    private List<Fragment> mFragments;
    private MyViewPagerAdapter myViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initDatas();
        configViews();
    }

    private void configViews() {
//        setSupportActionBar(mToolbar);
        // 设置Drawerlayout开关指示器，即Toolbar最左边的那个icon
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.open,R.string.close);
        actionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);

        //给NavigationView填充顶部区域，也可在xml中使用app:headerLayout="@layout/header_nav"来设置
        mNavigationView.inflateHeaderView(R.layout.header_nav);
        //给NavigationView填充Menu菜单，也可在xml中使用app:menu="@menu/menu_nav"来设置
        mNavigationView.inflateMenu(R.menu.menu_nav);

        // 初始化ViewPager的适配器，并设置给它
        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),mTitles,mFragments);
        mViewPager.setAdapter(myViewPagerAdapter);
        // 设置ViewPager最大缓存的页面个数
        mViewPager.setOffscreenPageLimit(5);
        // 给ViewPager添加页面动态监听器（为了让Toolbar中的Title可以变化相应的Tab的标题）
        mViewPager.addOnPageChangeListener(this);

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        // 将TabLayout和ViewPager进行关联，让两者联动起来
        mTabLayout.setupWithViewPager(mViewPager);
        // 设置Tablayout的Tab显示ViewPager的适配器中的getPageTitle函数获取到的标题
        mTabLayout.setTabsFromPagerAdapter(myViewPagerAdapter);

    }

    private void initDatas() {
        mTitles = getResources().getStringArray(R.array.tab_titles);
        mFragments = new ArrayList<>();
        for(int i=0;i<mTitles.length;i++){
            Bundle mBundle = new Bundle();
            mBundle.putInt("flag",i);
            MainFragment mainFragment = new MainFragment();
            mainFragment.setArguments(mBundle);
            mFragments.add(i,mainFragment);
        }
    }

    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerlayout);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.id_coordinatorlayout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.id_appbarlayout);
        mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.id_floatingactionbutton);
        mNavigationView = (NavigationView)findViewById(R.id.id_navigationview);

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mToolbar.setTitle(mTitles[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
