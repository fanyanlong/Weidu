package com.weidu.weidudianshang.list.presenter;

import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.list.AllListFragment;
import com.weidu.weidudianshang.list.DaiFkuanFragment;
import com.weidu.weidudianshang.list.model.IOrderModel;
import com.weidu.weidudianshang.list.model.OrderModel;

public class DaifkuanOrderPresenter implements IOrderPersenter{
    DaiFkuanFragment daiFkuanFragment;
    private final OrderModel orderModel;

    public DaifkuanOrderPresenter(DaiFkuanFragment daiFkuanFragment) {
        this.daiFkuanFragment = daiFkuanFragment;
        orderModel = new OrderModel();
    }


    @Override
    public void getOrderModel(String userid, String sessionid, int status, int page, int count) {
        orderModel.setOrderModel(Api.ORDER_URL, userid, sessionid, status, page, count, new IOrderModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                daiFkuanFragment.getViewData(data);
            }
        });
    }

    public void Destroy(){
        if (daiFkuanFragment!=null){
            daiFkuanFragment=null;
        }
    }
}
