package com.weidu.weidudianshang.search.model;

import java.util.HashMap;

public interface IHomeSearchModel {
    void setModelData(String url, String keyword,String page,String count, ICallBack iCallBack);
    interface ICallBack{
        void onSuccess(Object data);
        void onFaild(Exception e);
    }
}
