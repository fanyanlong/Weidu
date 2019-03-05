package com.weidu.weidudianshang.fragment;

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
import android.widget.CheckBox;
import android.widget.TextView;

import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.adapter.ShopCarAdapter;
import com.weidu.weidudianshang.bean.ShopCarBean;
import com.weidu.weidudianshang.shopcar.presenter.ShopCarPresenter;
import com.weidu.weidudianshang.shopcar.view.IShopCarView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ShopCarFragment extends Fragment implements IShopCarView {
    @BindView(R.id.mShopCar)
    RecyclerView mShopCar;
    Unbinder unbinder;
    @BindView(R.id.checkAll)
    CheckBox checkAll;
    @BindView(R.id.totalPrice)
    TextView totalPrice;
    private String userId;
    private String sessionId;
    private ShopCarPresenter shopCarPresenter;
    private ShopCarAdapter shopCarAdapter;
    private List<ShopCarBean.ResultBean> result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_shopcar, null);
        unbinder = ButterKnife.bind(this, view);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mShopCar.setLayoutManager(layoutManager);
        //获取凭证
        userId = sharedPreferences.getString("userId", "");
        sessionId = sharedPreferences.getString("sessionId", "");
        //引用P层
        shopCarPresenter = new ShopCarPresenter(this);
        shopCarPresenter.getModelData(userId, sessionId);
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                issetCheck(checkAll.isChecked());

            }
        });
        return view;
    }

    @Override
    public void getViewData(Object viewData) {
        ShopCarBean shopCarBean = (ShopCarBean) viewData;
        result = shopCarBean.getResult();
        shopCarAdapter = new ShopCarAdapter(getActivity(), result);
        mShopCar.setAdapter(shopCarAdapter);
        shopCarAdapter.setCheckAllListenner(new ShopCarAdapter.setOnClick() {
            @Override
            public void CallBack(List<ShopCarBean.ResultBean> list) {
                int num = 0;
                int goodsnum = 0;
                double price = 0;
                double totalprice = 0;
                for (int i = 0; i < list.size(); i++) {
                    num += list.get(i).getCount();
                    if (list.get(i).isCheck()) {
                        price += result.get(i).getPrice() * result.get(i).getCount();
                        totalprice += list.get(i).getCount() * list.get(i).getPrice();
                        goodsnum += list.get(i).getCount();
                    }

                }
                totalPrice.setText(price+"");
                if(num==goodsnum){
                    checkAll.setChecked(true);
                }else
                {
                    checkAll.setChecked(false);
                }

            }
        });
    }

    //全选
    private void issetCheck(boolean b) {
        int price = 0;
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setCheck(b);
            price += result.get(i).getPrice() * result.get(i).getCount();

        }
        shopCarAdapter.setList(result);
        if (b) {
            totalPrice.setText("¥:" + price);
        } else {
            totalPrice.setText("¥:" + 0.0);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        shopCarPresenter.getModelData(userId, sessionId);
    }
}
