package com.weidu.weidudianshang.list.model;

public interface IOrderModel {
    void setOrderModel(String url,String userid,String sessionid,int status,int page,int count,ICallBack iCallBack);
    interface ICallBack{
        void onSuccess(Object data);
    }
}
