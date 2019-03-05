package com.weidu.weidudianshang.goods.model;

import android.content.Context;

import com.weidu.weidudianshang.api.Api;

import com.weidu.weidudianshang.api.ApiService;
import com.weidu.weidudianshang.bean.GoodsBeen;
import com.weidu.weidudianshang.bean.GoodsShowBean;
import com.weidu.weidudianshang.httputils.RetrofitUtils;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class Model implements IModel {
    Context context;
    @Override
    public void getModel(String url,String commodityId,final SetModel setModel) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.shopshows(url,commodityId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GoodsShowBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GoodsShowBean value) {
                        setModel.getModelData(value);
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
