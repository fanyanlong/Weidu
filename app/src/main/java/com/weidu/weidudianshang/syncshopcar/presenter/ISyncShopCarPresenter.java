package com.weidu.weidudianshang.syncshopcar.presenter;

public interface ISyncShopCarPresenter {
    void getSearchModelData(String userid, String sessionid);
    void getSyncModelData(String userid, String sessionid, String data);
}
