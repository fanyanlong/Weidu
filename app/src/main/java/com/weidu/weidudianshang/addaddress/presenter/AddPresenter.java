package com.weidu.weidudianshang.addaddress.presenter;

import com.weidu.weidudianshang.activity.AddaddressActivity;
import com.weidu.weidudianshang.addaddress.model.AddModel;
import com.weidu.weidudianshang.addaddress.model.IAddModel;
import com.weidu.weidudianshang.api.Api;

import java.util.Map;

public class AddPresenter implements IAddPresenter{
    AddaddressActivity addaddressActivity;
    private final AddModel addModel;

    public AddPresenter(AddaddressActivity addaddressActivity) {
        this.addaddressActivity = addaddressActivity;
        addModel = new AddModel();
    }

    @Override
    public void getAddressModel(String userid, String sessionid, Map<String, String> map) {
        addModel.setAddModelData(Api.ADDADDRESS_URL, userid, sessionid, map, new IAddModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                addaddressActivity.getAddViewData(data);
            }
        });
    }
}
