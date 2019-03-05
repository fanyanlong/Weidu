package com.weidu.weidudianshang.shouaddress.presenter;

import com.weidu.weidudianshang.activity.ShouaddressActivity;
import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.shouaddress.model.IShouAddressModel;
import com.weidu.weidudianshang.shouaddress.model.ShouAddressModel;

public class ShouAddressPresenter implements IShouAddressPresenter{
    ShouaddressActivity shouaddressActivity;
    private final ShouAddressModel shouAddressModel;

    public ShouAddressPresenter(ShouaddressActivity shouaddressActivity) {
        this.shouaddressActivity = shouaddressActivity;
        shouAddressModel = new ShouAddressModel();
    }

    @Override
    public void getAddressModel(String userid, String sessionid) {
        shouAddressModel.setAddressModelData(Api.SHOUADDRESS_URL, userid, sessionid, new IShouAddressModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                shouaddressActivity.getAddressViewData(data);
            }
        });
    }
}
