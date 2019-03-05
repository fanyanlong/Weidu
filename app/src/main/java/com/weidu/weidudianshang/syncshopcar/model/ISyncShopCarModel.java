package com.weidu.weidudianshang.syncshopcar.model;

public interface ISyncShopCarModel {
    void setSearchModelData(String url, String userid, String sessionid,ICallBack iCallBack);
    void setSyncModelData(String url, String userid, String sessionid, String data, ICallBack iCallBack);
    interface ICallBack{
        void onSuccess(Object data);
        void onFaild(Exception e);
    }
}
