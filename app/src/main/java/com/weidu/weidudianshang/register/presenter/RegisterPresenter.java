package com.weidu.weidudianshang.register.presenter;

import com.weidu.weidudianshang.RegisterActivity;
import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.register.model.IRegisterModel;
import com.weidu.weidudianshang.register.model.RegisterModel;

import java.util.HashMap;

public class RegisterPresenter implements IRegisterPresenter{
    RegisterActivity registerActivity;
    private final RegisterModel registerModel;

    public RegisterPresenter(RegisterActivity registerActivity) {
        this.registerActivity=registerActivity;
        registerModel = new RegisterModel();
    }

    @Override
    public void getModelData(HashMap<String, String> map) {
        registerModel.setModelData(Api.REGISTER_URL, map, new IRegisterModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                registerActivity.getViewData(data);
            }

            @Override
            public void onFaild(Exception e) {

            }
        });
    }
}
