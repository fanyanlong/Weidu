package com.weidu.weidudianshang.login.presenter;

import com.weidu.weidudianshang.LoginActivity;
import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.login.model.ILoginModel;
import com.weidu.weidudianshang.login.model.LoginModel;

import java.util.HashMap;
import java.util.Map;

public class LoginPresenter implements ILoginPresenter{
    LoginActivity loginActivity;
    private final LoginModel loginModel;

    public LoginPresenter(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        loginModel = new LoginModel();
    }

    @Override
    public void getModelData(HashMap<String,String> map) {

        loginModel.setModelData(Api.LOGIN_URL, map, new ILoginModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                loginActivity.getViewData(data);
            }

            @Override
            public void onFaild(Exception e) {

            }
        });
    }
}
