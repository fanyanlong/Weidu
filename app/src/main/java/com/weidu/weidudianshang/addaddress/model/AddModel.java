package com.weidu.weidudianshang.addaddress.model;

import com.weidu.weidudianshang.api.ApiService;
import com.weidu.weidudianshang.bean.AddaddressBean;
import com.weidu.weidudianshang.httputils.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddModel implements IAddModel{
    @Override
    public void setAddModelData(String url, String userid, String sessionid, Map<String, String> map, final ICallBack iCallBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getAddAddress(url,userid,sessionid,map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<AddaddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddaddressBean value) {
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
