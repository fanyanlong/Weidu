package com.weidu.weidudianshang.home.presenter;

import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.fragment.HomeFragment;
import com.weidu.weidudianshang.home.model.HomeModel;
import com.weidu.weidudianshang.home.model.IHomeModel;

public class HomePresenter implements IHomePresenter{
    HomeFragment homeFragment;
    private  HomeModel homeModel;
    public HomePresenter(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
        homeModel = new HomeModel();
    }
    @Override
    public void getModelData() {
        homeModel.setModelData(Api.PRODUCT_URL, new IHomeModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                homeFragment.getViewData(data);
            }

            @Override
            public void onFaild(Exception e) {

            }
        });
    }

    @Override
    public void getTopModelData() {
        homeModel.setTopModelData(Api.TOP_URL, new IHomeModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                homeFragment.getTopViewData(data);
            }

            @Override
            public void onFaild(Exception e) {

            }
        });
    }

    @Override
    public void getDownModelData(int firstCategoryId) {
        homeModel.setDownModelData(Api.DOWN_URL, firstCategoryId, new IHomeModel.ICallBack() {
            @Override
            public void onSuccess(Object data) {
                homeFragment.getDownViewData(data);
            }

            @Override
            public void onFaild(Exception e) {

            }
        });
    }

    public void Destroy(){
        if (homeFragment!=null){
            homeFragment=null;
            System.gc();
        }
    }
}
