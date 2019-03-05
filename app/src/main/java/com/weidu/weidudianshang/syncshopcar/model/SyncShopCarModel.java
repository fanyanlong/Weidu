package com.weidu.weidudianshang.syncshopcar.model;

import com.weidu.weidudianshang.api.ApiService;
import com.weidu.weidudianshang.bean.ShopCarBean;
import com.weidu.weidudianshang.bean.SyncShopCarBean;
import com.weidu.weidudianshang.httputils.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SyncShopCarModel implements ISyncShopCarModel {

    @Override
    public void setSearchModelData(String url, String userid, String sessionid, final ICallBack iCallBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getShopCar(url,userid,sessionid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ShopCarBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShopCarBean value) {
                        iCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void setSyncModelData(String url, String userid, String sessionid, String data, final ICallBack iCallBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getSyncshopcar(url,userid,sessionid,data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SyncShopCarBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SyncShopCarBean value) {
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
