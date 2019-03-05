package com.weidu.weidudianshang.addaddress.model;

import java.util.Map;

public interface IAddModel {
    void setAddModelData(String url, String userid, String sessionid, Map<String,String> map,ICallBack iCallBack);
    interface ICallBack{
        void onSuccess(Object data);
    }
}
