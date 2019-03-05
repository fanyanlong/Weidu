package com.weidu.weidudianshang.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.adapter.ShouAddressAdapter;
import com.weidu.weidudianshang.bean.ShouAddressBean;
import com.weidu.weidudianshang.shouaddress.presenter.ShouAddressPresenter;
import com.weidu.weidudianshang.shouaddress.view.IShouAddressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShouaddressActivity extends AppCompatActivity implements IShouAddressView {

    @BindView(R.id.shouAddressRecyc)
    RecyclerView shouAddressRecyc;
    @BindView(R.id.addShouhuo)
    Button addShouhuo;
    private SharedPreferences sp;
    private String userId;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouaddress);
        ButterKnife.bind(this);
        sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        userId = sp.getString("userId", "");
        sessionId = sp.getString("sessionId", "");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        shouAddressRecyc.setLayoutManager(layoutManager);
        ShouAddressPresenter shouAddressPresenter = new ShouAddressPresenter(this);
        shouAddressPresenter.getAddressModel(userId, sessionId);
        addShouhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShouaddressActivity.this,AddaddressActivity.class));
                finish();
            }
        });
    }

    @Override
    public void getAddressViewData(Object o) {
        ShouAddressBean shouAddressBean = (ShouAddressBean) o;
        List<ShouAddressBean.ResultBean> result = shouAddressBean.getResult();
        ShouAddressAdapter shouAddressAdapter = new ShouAddressAdapter(this, result);
        shouAddressRecyc.setAdapter(shouAddressAdapter);
    }
}
