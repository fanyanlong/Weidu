package com.weidu.weidudianshang.circle.model;

import com.weidu.weidudianshang.api.ApiService;
import com.weidu.weidudianshang.bean.CircleBean;
import com.weidu.weidudianshang.httputils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CircleModel implements ICircleModel{
    @Override
    public void setModelData(String url,int page,int count, final ICallBack iCallBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getCircle(url,page,count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<CircleBean>() {
                    @Override
                    public void onNext(CircleBean value) {
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
