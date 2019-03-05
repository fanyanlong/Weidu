package com.weidu.weidudianshang.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.adapter.MyFootAdapter;
import com.weidu.weidudianshang.bean.MyFootBean;
import com.weidu.weidudianshang.myfoot.presenter.MyFootPresenter;
import com.weidu.weidudianshang.myfoot.view.IMyFootView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FootActivity extends AppCompatActivity implements IMyFootView {

    @BindView(R.id.myFootRecyc)
    RecyclerView myFootRecyc;
    private SharedPreferences sp;
    private String userId;
    private String sessionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot);
        ButterKnife.bind(this);
        sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        userId = sp.getString("userId","");
        sessionId = sp.getString("sessionId","");
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        myFootRecyc.setLayoutManager(staggeredGridLayoutManager);
        MyFootPresenter myFootPresenter = new MyFootPresenter(this);
        myFootPresenter.getMyFootModel(userId,sessionId,1,7);
    }

    @Override
    public void getMyFootViewData(Object o) {
        MyFootBean myFootBean = (MyFootBean) o;
        List<MyFootBean.ResultBean> result = myFootBean.getResult();
        MyFootAdapter myFootAdapter = new MyFootAdapter(FootActivity.this, result);
        myFootRecyc.setAdapter(myFootAdapter);
    }
}
