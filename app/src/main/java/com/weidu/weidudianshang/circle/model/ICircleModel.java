package com.weidu.weidudianshang.circle.model;

public interface ICircleModel {

    void setModelData(String url,int page,int count,ICallBack iCallBack);
    interface ICallBack{
        void onSuccess(Object data);
        void onFaild(Exception e);
    }
}
