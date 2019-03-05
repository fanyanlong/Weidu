package com.weidu.weidudianshang.register.model;

import com.weidu.weidudianshang.api.ApiService;
import com.weidu.weidudianshang.bean.LoginBean;
import com.weidu.weidudianshang.bean.RegisterBean;
import com.weidu.weidudianshang.httputils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import io.reactivex.schedulers.Schedulers;

public class RegisterModel implements IRegisterModel{
    @Override
    public void setModelData(String url, HashMap<String, String> hashMap, final ICallBack iCallBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getRegister(url,hashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean value) {
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
