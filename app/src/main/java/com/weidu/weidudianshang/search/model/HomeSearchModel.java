package com.weidu.weidudianshang.search.model;

import com.weidu.weidudianshang.api.ApiService;
import com.weidu.weidudianshang.bean.SearchBean;
import com.weidu.weidudianshang.httputils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeSearchModel implements IHomeSearchModel{

    @Override
    public void setModelData(String url, String keyword, String page, String count, final ICallBack iCallBack) {
        ApiService apiService=RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getSearch(url,keyword,page,count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<SearchBean>() {
                    @Override
                    public void onNext(SearchBean value) {
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
