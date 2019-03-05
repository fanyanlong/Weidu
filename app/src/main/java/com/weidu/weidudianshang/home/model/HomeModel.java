package com.weidu.weidudianshang.home.model;

import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.api.ApiService;
import com.weidu.weidudianshang.bean.DownBean;
import com.weidu.weidudianshang.bean.GoodsBeen;
import com.weidu.weidudianshang.bean.TopBean;
import com.weidu.weidudianshang.httputils.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements IHomeModel{
    @Override
    public void setModelData(String url, final ICallBack iCallBack) {
        ApiService apiService=RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getGoods(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<GoodsBeen>() {
                    @Override
                    public void onNext(GoodsBeen value) {
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
    //一级类目
    @Override
    public void setTopModelData(String url, final ICallBack iCallBack) {
        ApiService apiService=RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getTop(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<TopBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TopBean value) {
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

    //二级类目
    @Override
    public void setDownModelData(String url, int firstCategoryId, final ICallBack iCallBack) {
        ApiService apiService=RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getDown(url,firstCategoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DownBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DownBean value) {
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
