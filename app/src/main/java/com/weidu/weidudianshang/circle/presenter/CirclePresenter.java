package com.weidu.weidudianshang.circle.presenter;

import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.circle.model.CircleModel;
import com.weidu.weidudianshang.circle.model.ICircleModel;
import com.weidu.weidudianshang.fragment.CircleFragment;

public class CirclePresenter implements ICirclePresenter{
    CircleFragment circleFragment;
    private final CircleModel circleModel;

    public CirclePresenter(CircleFragment circleFragment) {
        this.circleFragment=circleFragment;
        circleModel = new CircleModel();
    }

    @Override
    public void getModelData(int page,int count) {
        circleModel.setModelData(Api.CIRCLE_URL, page, count, new ICircleModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                circleFragment.getViewData(data);
            }

            @Override
            public void onFaild(Exception e) {

            }
        });
    }

    public void Destroy(){
        if (circleFragment!=null){
            circleFragment=null;
            System.gc();
        }
    }
}
