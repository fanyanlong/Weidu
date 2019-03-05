package com.weidu.weidudianshang.register.model;

import java.util.HashMap;

public interface IRegisterModel {
    void setModelData(String url, HashMap<String,String> hashMap, ICallBack iCallBack);
    interface ICallBack{
        void onSuccess(Object data);
        void onFaild(Exception e);
    }
}
