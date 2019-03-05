package com.weidu.weidudianshang.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.list.AllListFragment;
import com.weidu.weidudianshang.list.DaiFkuanFragment;
import com.weidu.weidudianshang.list.DaiPjiaFragment;
import com.weidu.weidudianshang.list.DaiShuoFragment;
import com.weidu.weidudianshang.list.WanchengFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ListFragment extends Fragment {
    @BindView(R.id.mAllList)
    RadioButton mAllList;
    @BindView(R.id.mDaifokuan)
    RadioButton mDaifokuan;
    @BindView(R.id.mDaishouhuo)
    RadioButton mDaishouhuo;
    @BindView(R.id.mDaipingjia)
    RadioButton mDaipingjia;
    @BindView(R.id.mWancheng)
    RadioButton mWancheng;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.frame)
    FrameLayout frame;
    Unbinder unbinder;
    private FragmentManager manager;
    private AllListFragment allListFragment;
    private DaiFkuanFragment daiFkuanFragment;
    private DaiPjiaFragment daiPjiaFragment;
    private DaiShuoFragment daiShuoFragment;
    private WanchengFragment wanchengFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_list, null);
        unbinder = ButterKnife.bind(this, view);
        manager=getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        allListFragment = new AllListFragment();
        daiFkuanFragment = new DaiFkuanFragment();
        daiPjiaFragment = new DaiPjiaFragment();
        daiShuoFragment = new DaiShuoFragment();
        wanchengFragment = new WanchengFragment();

        transaction.add(R.id.frame, allListFragment);
        transaction.add(R.id.frame, daiFkuanFragment);
        transaction.add(R.id.frame, daiPjiaFragment);
        transaction.add(R.id.frame, daiShuoFragment);
        transaction.add(R.id.frame, wanchengFragment);

        transaction.show(allListFragment).hide(daiFkuanFragment).hide(daiPjiaFragment).hide(daiShuoFragment).hide(wanchengFragment);
        transaction.commit();
        //设置第一个按钮选中
        radioGroup.check(radioGroup.getChildAt(0).getId());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction begin2 = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.mAllList:
                        begin2.show(allListFragment).hide(daiFkuanFragment).hide(daiPjiaFragment).hide(daiShuoFragment).hide(wanchengFragment);
                        break;
                    case R.id.mDaifokuan:
                        begin2.show(daiFkuanFragment).hide(allListFragment).hide(daiPjiaFragment).hide(daiShuoFragment).hide(wanchengFragment);
                        break;
                    case R.id.mDaishouhuo:
                        begin2.show(daiShuoFragment).hide(daiFkuanFragment).hide(allListFragment).hide(daiPjiaFragment).hide(wanchengFragment);
                        break;
                    case R.id.mDaipingjia:
                        begin2.show(daiPjiaFragment).hide(daiShuoFragment).hide(daiFkuanFragment).hide(allListFragment).hide(wanchengFragment);
                        break;
                    case R.id.mWancheng:
                        begin2.show(wanchengFragment).hide(daiPjiaFragment).hide(daiShuoFragment).hide(daiFkuanFragment).hide(allListFragment);
                        break;
                }
                begin2.commit();

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
