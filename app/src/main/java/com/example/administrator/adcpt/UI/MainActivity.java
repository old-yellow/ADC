package com.example.administrator.adcpt.UI;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.administrator.adcpt.Base.ActivityCollector;
import com.example.administrator.adcpt.Base.BaseActivity;
import com.example.administrator.adcpt.R;

public class MainActivity extends BaseActivity {

    //包含的四个页面
    private FragmentManager mFragmentManager;

    private Fragment homeFragment;

    private Fragment zergFragment;

    private Fragment terranFragment;

    private Fragment protossFragment;

    //页面标题
    private TextView title;

    //底部导航栏
    private BottomNavigationBar mNavigationBar;

    private int lastSelectedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        //设置抽屉适配屏幕占比
        setDrawerScale(this, navView, 0.65);
        title = (TextView)findViewById(R.id.title);
        //设置底部导航栏
        init();

    }

    private void init() {
        mNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        //设置模式
        mNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        //添加导航栏的图标、文字
        mNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //设置点击前后的图片变换
        mNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.bottom_icon4, "主页")
                        .setInactiveIconResource(R.drawable.bottom_icon4_1))
                .addItem(new BottomNavigationItem(R.drawable.bottom_icon1, "泰伦")
                        .setInactiveIconResource(R.drawable.bottom_icon1_1))
                .addItem(new BottomNavigationItem(R.drawable.bottom_icon2, "异虫")
                        .setInactiveIconResource(R.drawable.bottom_icon2_1))
                .addItem(new BottomNavigationItem(R.drawable.bottom_icon3, "星灵")
                        .setInactiveIconResource(R.drawable.bottom_icon3_1))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();

        //设置导航栏的各项操作监听
        mNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                //选中页面设置页面和标题
                initFragment(position);
                initTitle(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                //重新选中界面只需要重新设置标题
                initTitle(position);
            }
        });
        //默认出现的界面
    }

    //初始化相应界面，实现了fragment的懒加载
    private void initFragment(int position) {
        mFragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        //先隐藏再加载
        hideFragment(fragmentTransaction);
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.main_content, homeFragment);
                } else {
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case 1:
                if (zergFragment == null) {
                    zergFragment = new ZergFragment();
                    fragmentTransaction.add(R.id.main_content, zergFragment);
                } else {
                    fragmentTransaction.show(zergFragment);
                }
                break;
            case 2:
                if (terranFragment == null) {
                    terranFragment = new TerranFragment();
                    fragmentTransaction.add(R.id.main_content, terranFragment);
                } else {
                    fragmentTransaction.show(terranFragment);
                }
                break;
            case 3:
                if (protossFragment == null) {
                    protossFragment = new ProtossFragment();
                    fragmentTransaction.add(R.id.main_content, protossFragment);
                } else {
                    fragmentTransaction.show(protossFragment);
                }
                break;
            default:
        }
    }

    //隐藏未点开页面
    private void hideFragment(FragmentTransaction transaction) {
        Fragment[] fragments = new Fragment[]{
                homeFragment, zergFragment, terranFragment, protossFragment};
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                transaction.hide(fragment);
            }
        }
    }

    //初始化相应标题
    private void initTitle(int position) {
        switch (position) {
            case 0:
                title.setText(R.string.home);
                break;
            case 1:
                title.setText(R.string.zerg);
                break;
            case 2:
                title.setText(R.string.terran);
                break;
            case 3:
                title.setText(R.string.protoss);
                break;
            default:
                title.setText(R.string.home);
        }
    }

    public static void setDrawerScale(Activity activity, View navView, double displayWidthPercentage) {
        if (activity == null || navView == null) return;
        try {
            Display display = activity.getWindowManager().getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT < 17) {
                display.getMetrics(metrics);
            } else {
                display.getRealMetrics(metrics);
            }
            ViewGroup.LayoutParams params = navView.getLayoutParams();
            params.width = (int) (metrics.widthPixels * displayWidthPercentage);
            params.height = metrics.heightPixels;
            navView.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.finishAll();
    }
}
