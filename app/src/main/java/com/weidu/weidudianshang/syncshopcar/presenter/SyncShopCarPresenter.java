package com.weidu.weidudianshang.syncshopcar.presenter;

import com.weidu.weidudianshang.GoodsActivity;
import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.fragment.ShopCarFragment;
import com.weidu.weidudianshang.shopcar.model.ShopCarModel;
import com.weidu.weidudianshang.syncshopcar.model.ISyncShopCarModel;
import com.weidu.weidudianshang.syncshopcar.model.SyncShopCarModel;

public class SyncShopCarPresenter implements ISyncShopCarPresenter {

    GoodsActivity goodsActivity;
    private final SyncShopCarModel syncShopCarModel;


    public SyncShopCarPresenter(GoodsActivity goodsActivity) {
        this.goodsActivity = goodsActivity;
        syncShopCarModel = new SyncShopCarModel();
    }


    @Override
    public void getSearchModelData(String userid, String sessionid) {
        syncShopCarModel.setSearchModelData(Api.SHOPCAR_URL, userid, sessionid, new ISyncShopCarModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                goodsActivity.getSearchViewData(data);
            }

            @Override
            public void onFaild(Exception e) {

            }
        });
    }

    @Override
    public void getSyncModelData(String userid, String sessionid, String data) {
        syncShopCarModel.setSyncModelData(Api.SYNCSHOPCAR_URL, userid, sessionid, data, new ISyncShopCarModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                goodsActivity.getSyncViewData(data);
            }

            @Override
            public void onFaild(Exception e) {

            }
        });
    }
}
