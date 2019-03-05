package com.weidu.weidudianshang.myfoot.presenter;

import com.weidu.weidudianshang.activity.FootActivity;
import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.myfoot.model.IMyFootModel;
import com.weidu.weidudianshang.myfoot.model.MyFootModel;

public class MyFootPresenter implements IMyFootPresenter{
    FootActivity footActivity;
    private final MyFootModel myFootModel;

    public MyFootPresenter(FootActivity footActivity) {
        this.footActivity = footActivity;
        myFootModel = new MyFootModel();
    }

    @Override
    public void getMyFootModel(String userid, String sessionid, int page, int count) {
        myFootModel.setFootModelData(Api.MYFOOT_URL, userid, sessionid, page, count, new IMyFootModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                footActivity.getMyFootViewData(data);
            }
        });
    }
}
