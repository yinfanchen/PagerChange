package com.fairy.riven.pagerchange;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ContentFragment.MyListener {
    private ViewPager viewPager;
    private LinearLayout iindicator;
    private PagerAdapter adapter;
    private List<Fragment> listFragments=new ArrayList<Fragment>();
    LinearLayout.LayoutParams param;
    View view;
    protected boolean useThemestatusBarColor = false;//是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean withoutUseStatusBarColor = false;//是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBar();
        viewPager= (ViewPager) findViewById(R.id.viewPage);
        iindicator= (LinearLayout) findViewById(R.id.ll_indicator);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                for(int i=0;i<listFragments.size();i++){
////                    iindicator.getChildAt(i).setBackgroundResource(position==i?R.drawable.dot_press:R.drawable.dot_normal);
//                }
            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //jiaru fragment
        for (int i=0;i<2;i++){
            ContentFragment fragment=new ContentFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("index",i);
            fragment.setArguments(bundle);
            listFragments.add(fragment);
        }
        adapter=new ViewGroupFragment(getSupportFragmentManager(),listFragments);
        viewPager.setAdapter(adapter);

        //圆点的实现
//        initIndicator();
    }

    private void initIndicator() {
//        int width= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10f,getResources().getDisplayMetrics());
//        param=new LinearLayout.LayoutParams(width,width);
//        param.rightMargin=width;
//        for(int i=0;i<listFragments.size();i++){
//            View view=new View(MainActivity.this);
//            view.setId(i);
//            view.setLayoutParams(param);
//            view.setBackgroundResource(i==0?R.drawable.dot_press:R.drawable.dot_normal);
//            //把点加入到布局中
//            iindicator.addView(view,i);
//
//        }
    }

    @Override
    public void sendMessage(String str) {
//        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }

    /**
     * 设置状态栏的属性
     */
    protected void setStatusBar() {
        //来自 http://blog.csdn.net/smileiam/article/details/73603840
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色 设置了false,用通明的形式
            if (useThemestatusBarColor) {
                getWindow().setStatusBarColor(Color.WHITE);
            } else {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        //如果是6.0以上 设置文字颜色和图标进行亮色处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !withoutUseStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}

