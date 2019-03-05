package com.weidu.weidudianshang.myfoot.model;

import java.util.HashMap;

public interface IMyFootModel {
    void setFootModelData(String url, String userid,String sessionid,int page,int count, ICallBack iCallBack);
    interface ICallBack{
        void onSuccess(Object data);

    }
}
