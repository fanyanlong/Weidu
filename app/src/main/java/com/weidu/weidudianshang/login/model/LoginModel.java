package com.weidu.weidudianshang.login.model;

import com.weidu.weidudianshang.api.ApiService;
import com.weidu.weidudianshang.bean.LoginBean;
import com.weidu.weidudianshang.httputils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements ILoginModel{
    @Override
    public void setModelData(String url, HashMap<String, String> hashMap, final ICallBack iCallBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getLogin(url,hashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean value) {
                        iCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
