package com.example.administrator.adcpt.ui;

import android.app.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.administrator.adcpt.base.ActivityCollector;
import com.example.administrator.adcpt.base.BaseActivity;
import com.example.administrator.adcpt.R;

import java.lang.reflect.Field;

public class MainActivity extends BaseActivity {

    //包含的四个页面


    private Fragment homeFragment;

    private Fragment zergFragment;

    private Fragment terranFragment;

    private Fragment protossFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        //设置抽屉适配屏幕占比
        setDrawerScale(this, navView, 0.75);
        //设置底部导航栏
        init();
    }

    private void init() {

        //底部导航栏
        BottomNavigationBar mNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        //设置模式
        mNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        //添加导航栏的图标、文字
        mNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //设置点击前后的图片变换
        mNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.bottom_icon4_1, "主页")
                        .setInactiveIconResource(R.drawable.bottom_icon4))
                .addItem(new BottomNavigationItem(R.drawable.bottom_icon1_1, "泰伦")
                        .setInactiveIconResource(R.drawable.bottom_icon1))
                .addItem(new BottomNavigationItem(R.drawable.bottom_icon2_1, "异虫")
                        .setInactiveIconResource(R.drawable.bottom_icon2))
                .addItem(new BottomNavigationItem(R.drawable.bottom_icon3_1, "星灵")
                        .setInactiveIconResource(R.drawable.bottom_icon3))
                .setFirstSelectedPosition(0)
                .initialise();
        setBottomNavigationItem(mNavigationBar, 6,26, 10);
        //默认出现的界面
        setDefaultView();

        //设置导航栏的各项操作监听
        mNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                //选中页面设置页面和标题
                initFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });

    }

    private void setBottomNavigationItem(BottomNavigationBar bottomNavigationBar, int space, int imgLen, int textSize){
        Class barClass = bottomNavigationBar.getClass();
        Field[] fields = barClass.getDeclaredFields();
        for(int i = 0; i < fields.length; i++){
            Field field = fields[i];
            field.setAccessible(true);
            if(field.getName().equals("mTabContainer")){
                try{
                    //反射得到 mTabContainer
                    LinearLayout mTabContainer = (LinearLayout) field.get(bottomNavigationBar);
                    for(int j = 0; j < mTabContainer.getChildCount(); j++){
                        //获取到容器内的各个Tab
                        View view = mTabContainer.getChildAt(j);
                        //获取到Tab内的各个显示控件
                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(56));
                        FrameLayout container = (FrameLayout) view.findViewById(R.id.fixed_bottom_navigation_container);
                        container.setLayoutParams(params);
                        container.setPadding(dip2px(12), dip2px(0), dip2px(12), dip2px(0));

                        //获取到Tab内的文字控件
                        TextView labelView = (TextView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_title);
                        //计算文字的高度DP值并设置，setTextSize为设置文字正方形的对角线长度，所以：文字高度（总内容高度减去间距和图片高度）*根号2即为对角线长度，此处用DP值，设置该值即可。
                        labelView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
                        labelView.setIncludeFontPadding(false);
                        labelView.setPadding(0,0,0,dip2px(20-textSize - space/2));

                        //获取到Tab内的图像控件
                        ImageView iconView = (ImageView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_icon);
                        //设置图片参数，其中，MethodUtils.dip2px()：换算dp值
                        params = new FrameLayout.LayoutParams(dip2px(imgLen), dip2px(imgLen));
                        params.setMargins(0,0,0,space/2);
                        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                        iconView.setLayoutParams(params);
                    }
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public int dip2px(float dpValue) {
        final float scale = getApplication().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //初始化相应界面，实现了fragment的懒加载
    private void initFragment(int position) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
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
        fragmentTransaction.commit();
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


    private void setDefaultView() {
        initFragment(0);
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
