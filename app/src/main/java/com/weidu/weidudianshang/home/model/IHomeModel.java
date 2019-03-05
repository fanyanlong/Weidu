package com.weidu.weidudianshang.home.model;

public interface IHomeModel {
    void setModelData(String url,ICallBack iCallBack);
    //一级类目
    void setTopModelData(String url,ICallBack iCallBack);
    //二级类目
    void setDownModelData(String url,int firstCategoryId,ICallBack iCallBack);
    interface ICallBack{
        void onSuccess(Object data);
        void onFaild(Exception e);
    }
}
