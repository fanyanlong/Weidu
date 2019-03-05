package com.weidu.weidudianshang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hjm.bottomtabbar.BottomTabBar;
import com.weidu.weidudianshang.fragment.CircleFragment;
import com.weidu.weidudianshang.fragment.HomeFragment;
import com.weidu.weidudianshang.fragment.ListFragment;
import com.weidu.weidudianshang.fragment.MyFragment;
import com.weidu.weidudianshang.fragment.ShopCarFragment;
import com.weidu.weidudianshang.httputils.NetWorkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mBottomTabBar)
    BottomTabBar mBottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        NetWorkUtils netWorkUtils=new NetWorkUtils();

        netWorkUtils.getConnectivityManager(this);
        if (netWorkUtils.isNetworkConnected(this)){
            netWorkUtils.getConnectivityManager(this);
            mBottomTabBar.init(getSupportFragmentManager())
                    .setFontSize(0)
                    .setTabPadding(80,6,0)
                    .setImgSize(80,80)
                    .addTabItem("",R.mipmap.home_true,R.mipmap.home_false, HomeFragment.class)
                    .addTabItem("",R.mipmap.circle_true,R.mipmap.circle_false,CircleFragment.class)
                    .setTabPadding(30,6,0)
                    .setImgSize(150,150)
                    .addTabItem("",R.mipmap.shopcar,R.mipmap.shopcar,ShopCarFragment.class)
                    .setTabPadding(80,6,0)
                    .setImgSize(80,80)
                    .addTabItem("",R.mipmap.list_true,R.mipmap.list_false,ListFragment.class)
                    .addTabItem("",R.mipmap.mine_true,R.mipmap.mine_false,MyFragment.class)
                    .isShowDivider(false)
                    .setTabBarBackgroundResource(R.mipmap.background)
                    .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                        @Override
                        public void onTabChange(int position, String name, View view) {

                        }
                    }).setCurrentTab(0);
        }else{
            netWorkUtils.checkNetwork(MainActivity.this);
        }

    }
}
