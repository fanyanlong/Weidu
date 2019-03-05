package com.weidu.weidudianshang.shopcar.presenter;

import android.content.Context;

import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.fragment.ShopCarFragment;
import com.weidu.weidudianshang.shopcar.model.IShopCarModel;
import com.weidu.weidudianshang.shopcar.model.ShopCarModel;

public class ShopCarPresenter implements IShopCarPresenter{

    ShopCarFragment shopCarFragment;
    private final ShopCarModel shopCarModel;

    public ShopCarPresenter(ShopCarFragment shopCarFragment) {
        this.shopCarFragment = shopCarFragment;
        shopCarModel = new ShopCarModel();
    }

    @Override
    public void getModelData(String userid, String sessionid) {
        shopCarModel.setModelData(Api.SHOPCAR_URL, userid, sessionid, new IShopCarModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                shopCarFragment.getViewData(data);
            }

            @Override
            public void onFaild(Exception e) {

            }
        });
    }


}
