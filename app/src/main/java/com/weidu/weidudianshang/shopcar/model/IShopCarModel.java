package com.weidu.weidudianshang.shopcar.model;

import java.util.HashMap;

public interface IShopCarModel {
    void setModelData(String url, String userid,String sessionid, ICallBack iCallBack);
    interface ICallBack{
        void onSuccess(Object data);
        void onFaild(Exception e);
    }
}
