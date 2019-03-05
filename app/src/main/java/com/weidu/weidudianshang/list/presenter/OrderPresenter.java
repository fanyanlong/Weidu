package com.weidu.weidudianshang.list.presenter;

import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.list.AllListFragment;
import com.weidu.weidudianshang.list.model.IOrderModel;
import com.weidu.weidudianshang.list.model.OrderModel;

public class OrderPresenter implements IOrderPersenter{
    AllListFragment allListFragment;
    private final OrderModel orderModel;

    public OrderPresenter(AllListFragment allListFragment) {
        this.allListFragment = allListFragment;
        orderModel = new OrderModel();
    }


    @Override
    public void getOrderModel(String userid, String sessionid, int status, int page, int count) {
        orderModel.setOrderModel(Api.ORDER_URL, userid, sessionid, status, page, count, new IOrderModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                allListFragment.getViewData(data);
            }
        });
    }

    public void Destroy(){
        if (allListFragment!=null){
            allListFragment=null;
        }
    }
}
