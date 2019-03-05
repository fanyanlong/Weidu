package com.weidu.weidudianshang.login.model;

import java.util.HashMap;

public interface ILoginModel {

    void setModelData(String url, HashMap<String,String> hashMap,ICallBack iCallBack);
    interface ICallBack{
        void onSuccess(Object data);
        void onFaild(Exception e);
    }
}
