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
import com.weidu.weidudianshang.adapter.OrderAdapter;
import com.weidu.weidudianshang.bean.OrderBean;
import com.weidu.weidudianshang.list.presenter.OrderPresenter;
import com.weidu.weidudianshang.list.view.IOrderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AllListFragment extends Fragment implements IOrderView {
    @BindView(R.id.allOrderRecyc)
    RecyclerView allOrderRecyc;
    Unbinder unbinder;
    private OrderPresenter orderPresenter;
    private SharedPreferences sp;
    private String userId;
    private String sessionId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_alllist, container, false);
        unbinder = ButterKnife.bind(this, view);
        sp = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        userId = sp.getString("userId","");
        sessionId = sp.getString("sessionId","");
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        allOrderRecyc.setLayoutManager(layoutManager);
        orderPresenter = new OrderPresenter(this);
        orderPresenter.getOrderModel(userId,sessionId,0,1,5);
        return view;
    }

    @Override
    public void getViewData(Object viewData) {
        OrderBean orderBean = (OrderBean) viewData;
        List<OrderBean.OrderListBean.DetailListBean> detailList = orderBean.getOrderList().get(0).getDetailList();
        OrderAdapter orderAdapter = new OrderAdapter(getActivity(), detailList);
        allOrderRecyc.setAdapter(orderAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (orderPresenter!=null){
            orderPresenter.Destroy();
            System.gc();
        }

    }
}
