package com.weidu.weidudianshang.list;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.adapter.DaifkuanOrderAdapter;
import com.weidu.weidudianshang.adapter.OrderAdapter;
import com.weidu.weidudianshang.bean.OrderBean;
import com.weidu.weidudianshang.list.presenter.DaifkuanOrderPresenter;
import com.weidu.weidudianshang.list.presenter.OrderPresenter;
import com.weidu.weidudianshang.list.view.IOrderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DaiFkuanFragment extends Fragment implements IOrderView {
    @BindView(R.id.daifkuanOrderRecyc)
    RecyclerView daifkuanOrderRecyc;
    Unbinder unbinder;
    private SharedPreferences sp;
    private String userId;
    private String sessionId;
    private DaifkuanOrderPresenter daifkuanOrderPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_daifukuan, container, false);
        unbinder = ButterKnife.bind(this, view);
        sp = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        userId = sp.getString("userId","");
        sessionId = sp.getString("sessionId","");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        daifkuanOrderRecyc.setLayoutManager(layoutManager);

        daifkuanOrderPresenter = new DaifkuanOrderPresenter(this);
        daifkuanOrderPresenter.getOrderModel(userId,sessionId,1,1,6);
        return view;
    }

    @Override
    public void getViewData(Object viewData) {
        OrderBean orderBean = (OrderBean) viewData;
        List<OrderBean.OrderListBean.DetailListBean> detailList = orderBean.getOrderList().get(0).getDetailList();
        DaifkuanOrderAdapter daifkuanOrderAdapter = new DaifkuanOrderAdapter(getActivity(), detailList);
        daifkuanOrderRecyc.setAdapter(daifkuanOrderAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
