package com.weidu.weidudianshang.shouaddress.model;

import com.weidu.weidudianshang.api.ApiService;
import com.weidu.weidudianshang.bean.MyFootBean;
import com.weidu.weidudianshang.bean.ShouAddressBean;
import com.weidu.weidudianshang.httputils.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShouAddressModel implements IShouAddressModel{
    @Override
    public void setAddressModelData(String url, String userid, String sessionid, final ICallBack iCallBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getShouAddress(url,userid,sessionid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ShouAddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShouAddressBean value) {
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
