package com.weidu.weidudianshang.shouaddress.model;

public interface IShouAddressModel {
    void setAddressModelData(String url, String userid,String sessionid,ICallBack iCallBack);
    interface ICallBack{
        void onSuccess(Object data);

    }
}
