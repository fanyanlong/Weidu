package com.weidu.weidudianshang.myfoot.model;

import com.weidu.weidudianshang.api.ApiService;
import com.weidu.weidudianshang.bean.MyFootBean;
import com.weidu.weidudianshang.httputils.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyFootModel implements IMyFootModel{
    @Override
    public void setFootModelData(String url, String userid, String sessionid, int page, int count, final ICallBack iCallBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getMyfoot(url,userid,sessionid,page,count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<MyFootBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyFootBean value) {
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
